package quintype.com.templatecollectionwithrx.ui.main.fragments


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.custom_tool_bar.*
import kotlinx.android.synthetic.main.fragment_story_detail.*
import kotlinx.android.synthetic.main.retry_layout.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.StoryDetailAdapter
import quintype.com.templatecollectionwithrx.models.story.SlugStory
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
import quintype.com.templatecollectionwithrx.viewmodels.StoryViewModel


/**
 * A simple [Fragment] subclass.
 *
 */
class StoryDetailFragment : BaseFragment() {

    private var storyViewModel: StoryViewModel? = null

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

        fragment_story_detail_pb_progress?.visibility = View.VISIBLE

        (activity as AppCompatActivity).setSupportActionBar(fragment_story_detail_toolbar)
//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).title = null
        fragment_story_detail_collapsing_toolbar_layout?.setContentScrimColor(resources.getColor(R.color.colorPrimary))

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        fragment_story_detail_rv_recycler_view?.layoutManager = layoutManager

        loadStoryDetailIfInternetPresent()

        retry_button.setOnClickListener {
            loadStoryDetailIfInternetPresent()
        }

        custom_tool_bar_iv_back_image.visibility = View.VISIBLE
        custom_tool_bar_iv_share_image.visibility = View.VISIBLE
        custom_tool_bar_tv_title.visibility = View.INVISIBLE

        custom_tool_bar_iv_back_image.setOnClickListener {
            fragmentManager?.popBackStack()
            fragmentManager?.executePendingTransactions()
        }
        custom_tool_bar_iv_share_image.setOnClickListener {
            ShareCurrentStory(mStory.slug)
        }
    }

    private fun loadStoryDetailIfInternetPresent() {
        if (NetworkUtils.isConnected(activity?.applicationContext as Context)) {
            loadStoryDetailBySlug(mStory.slug)

            fragment_story_detail_pb_progress?.visibility = View.VISIBLE
            retry_container?.visibility = View.GONE
            retry_button?.visibility = View.GONE
        } else {
//            fragment_story_detail_swipe_refresh_layout?.isRefreshing = false
            fragment_story_detail_fl_main_container?.visibility = View.GONE
            retry_container?.visibility = View.VISIBLE
            retry_button?.visibility = View.VISIBLE
        }
    }

    private fun loadStoryDetailBySlug(mStorySlug: String) {
        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)
        storyViewModel?.getStoryDetailBySlug(mStorySlug)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        object : DisposableSingleObserver<SlugStory>() {
                            override fun onSuccess(mStory: SlugStory) {
                                setStoryDetail(mStory.story)

                                retry_container?.visibility = View.GONE
                                retry_button?.visibility = View.GONE
                            }

                            override fun onError(e: Throwable) {
                                Log.d("Rakshith", "error ${e.message}")
                                fragment_story_detail_pb_progress?.visibility = View.GONE

                                retry_container?.visibility = View.VISIBLE
                                retry_button?.visibility = View.VISIBLE
                            }
                        })
    }

    fun setStoryDetail(mStory: Story) {
        if (app_bar_layout != null)
            app_bar_layout?.visibility = View.VISIBLE
        fragment_story_detail_pb_progress?.visibility = View.GONE
//            fragment_story_detail_swipe_refresh_layout.visibility = View.GONE

        val heroImageURL = Utilities.getSharedPreferences(activity?.applicationContext as Context, Constants.SP_CDN_IMAGE_NAME) + mStory.heroImageS3Key
        fragment_story_detail_iv_hero_image?.setImageURI(heroImageURL)


//        Glide.with(activity?.applicationContext as Context)
//                .load(heroImageURL)
//                .into(fragment_story_detail_iv_hero_image)

//            fragment_story_detail_tv_title?.text = mStory?.headline

        val storyDetailAdapter = StoryDetailAdapter(mStory, fragmentCallbacks)
        fragment_story_detail_rv_recycler_view?.adapter = storyDetailAdapter
    }
}