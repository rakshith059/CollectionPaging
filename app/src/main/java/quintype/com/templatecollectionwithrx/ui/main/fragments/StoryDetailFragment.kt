package quintype.com.templatecollectionwithrx.ui.main.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_story_detail.*
import kotlinx.android.synthetic.main.retry_layout.*
import kotlinx.android.synthetic.main.story_hero_image_author_view_holder.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.StoryDetailAdapter
import quintype.com.templatecollectionwithrx.models.story.SlugStory
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
import quintype.com.templatecollectionwithrx.viewmodels.StoryViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.ZoneOffset.UTC
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class StoryDetailFragment : BaseFragment() {

    var storyViewModel: StoryViewModel? = null

    companion object {
        var mStory = Story()

        private const val ARG_STORY_ITEM: String = "ARG_STORY_ITEM"

        fun newInstance(mStory: Story): StoryDetailFragment {
            val fragment = StoryDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_STORY_ITEM, mStory)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mStory = arguments?.getParcelable(ARG_STORY_ITEM) as Story

        loadStoryDetailIfInternetPresent()
        fragment_story_detail_pb_progress?.visibility = View.VISIBLE
    }

    private fun loadStoryDetailIfInternetPresent() {
        if (NetworkUtils.isConnected(activity?.applicationContext as Context)) {
            loadStoryDetailBySlug(mStory.slug)
        } else {
//            fragment_story_detail_swipe_refresh_layout?.isRefreshing = false
            fragment_story_detail_fl_main_container?.visibility = View.GONE
            retry_button?.visibility = View.VISIBLE
        }
    }

    private fun loadStoryDetailBySlug(storySlug: String) {
        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)
        storyViewModel?.getStoryDetailBySlug(storySlug)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        fragment_story_detail_rv_recycler_view?.layoutManager = layoutManager

        observableStoryViewHolder(storyViewModel)
    }

    private fun observableStoryViewHolder(storyViewModel: StoryViewModel?) {
        storyViewModel?.getStoryObservable()?.observe(this, Observer<SlugStory> {

            fragment_story_detail_pb_progress.visibility = View.GONE
//            fragment_story_detail_swipe_refresh_layout.visibility = View.GONE

            val mStory = it?.story

            val heroImageURL = Constants.getSharedPreferences(activity?.applicationContext as Context, Constants.SP_CDN_IMAGE_NAME) + mStory?.heroImageS3Key
            Glide.with(activity?.applicationContext as Context)
                    .load(heroImageURL)
                    .into(fragment_story_detail_iv_hero_image)

//            fragment_story_detail_tv_title?.text = mStory?.headline


            /**
             * checking for sections display name if it's null then checking for section's name
             */
            var sectionName: String? = null
            var storySection = mStory?.sections?.first()
            if (storySection?.displayName != null)
                sectionName = storySection?.displayName
            else if (storySection?.name != null)
                sectionName = storySection?.name
            if (!TextUtils.isEmpty(sectionName)) {
                story_hero_image_author_view_holder_tv_section_name.text = sectionName
                story_hero_image_author_view_holder_ll_section?.visibility = View.VISIBLE
            }

            /**
             * checking for story headline
             */
            var mStoryTitle: String? = mStory?.headline
            if (!TextUtils.isEmpty(mStoryTitle)) {
                story_hero_image_author_view_holder_tv_title?.visibility = View.VISIBLE
                story_hero_image_author_view_holder_tv_title.minLines = 2
                story_hero_image_author_view_holder_tv_title.text = mStory?.headline
            }

            /**
             * Checking for rating bar
             */
            var reviewRatingValue: Float? = mStory?.storyMetaData?.reviewRating?.value()?.toFloat()
            if (reviewRatingValue != null && reviewRatingValue > 0f) {
                story_hero_image_author_view_holder_item_rating_bar?.visibility = View.VISIBLE
                story_hero_image_author_view_holder_item_rating_bar?.score = reviewRatingValue
            } else {
                story_hero_image_author_view_holder_item_rating_bar?.visibility = View.GONE
            }

            /**
             * Checking for Author name and Author icon
             */
            val authorName = mStory?.authors?.first()?.name
            if (authorName != null) {
                story_hero_image_author_view_holder_tv_author_name?.text = authorName
                story_hero_image_author_view_holder_tv_author_name?.visibility = View.VISIBLE

                val heroImageURL = mStory.authors?.first()?.avatarUrl
                if (heroImageURL != null) {
                    story_hero_image_author_view_holder_iv_author_icon?.visibility = View.VISIBLE
                    Glide.with(story_hero_image_author_view_holder_iv_author_icon?.context as Context)
                            .load(heroImageURL)
                            .into(story_hero_image_author_view_holder_iv_author_icon)
                }
            }

            /**
             * Checking for Published date
             */
            val publishedDate = mStory?.publishedAt.toString()
            if (publishedDate != null) {
                story_hero_image_author_view_holder_tv_published_date?.text = Constants.formatDate(publishedDate)
                story_hero_image_author_view_holder_tv_published_date?.visibility = View.VISIBLE
            }

//            toolbar?.title = mStory?.headline
//            toolbar?.setTitleTextColor(resources.getColor(R.color.black_opacity_75))
//            collapsing_toolbar_layout?.setCollapsedTitleTextColor(resources.getColor(R.color.white_transparency_75))

            var storyDetailAdapter = StoryDetailAdapter(mStory, fragmentCallbacks)
            fragment_story_detail_rv_recycler_view?.adapter = storyDetailAdapter
        })
    }
}