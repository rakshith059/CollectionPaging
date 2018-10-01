package quintype.com.templatecollectionwithrx.ui.main.fragments


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_story_detail.*
import kotlinx.android.synthetic.main.retry_layout.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.StoryDetailAdapter
import quintype.com.templatecollectionwithrx.models.story.SlugStory
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.services.RetrofitApiClient
import quintype.com.templatecollectionwithrx.services.StoryServiceApi
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
    val compositeDisposable = CompositeDisposable()
    private var storyServiceApi: StoryServiceApi = RetrofitApiClient.getRetrofitApiClient().create(StoryServiceApi::class.java)

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

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        fragment_story_detail_rv_recycler_view?.layoutManager = layoutManager

        loadStoryDetailIfInternetPresent()

        retry_button.setOnClickListener {
            loadStoryDetailIfInternetPresent()
        }
    }

    private fun loadStoryDetailIfInternetPresent() {
        if (NetworkUtils.isConnected(activity?.applicationContext as Context)) {
            loadStoryDetailBySlug(mStory.slug)

            fragment_story_detail_pb_progress?.visibility = View.VISIBLE
            ll_no_internet?.visibility = View.GONE
            retry_button?.visibility = View.GONE
        } else {
//            fragment_story_detail_swipe_refresh_layout?.isRefreshing = false
            fragment_story_detail_fl_main_container?.visibility = View.GONE
            ll_no_internet?.visibility = View.VISIBLE
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

                                ll_no_internet?.visibility = View.GONE
                                retry_button?.visibility = View.GONE
                            }

                            override fun onError(e: Throwable) {
                                Log.d("Rakshith", "error ${e.message}")
                                fragment_story_detail_pb_progress?.visibility = View.GONE

                                ll_no_internet?.visibility = View.VISIBLE
                                retry_button?.visibility = View.VISIBLE
                            }
                        })
    }

    fun setStoryDetail(mStory: Story) {
        app_bar_layout.visibility = View.VISIBLE
        fragment_story_detail_pb_progress.visibility = View.GONE
//            fragment_story_detail_swipe_refresh_layout.visibility = View.GONE

        val heroImageURL = Utilities.getSharedPreferences(activity?.applicationContext as Context, Constants.SP_CDN_IMAGE_NAME) + mStory.heroImageS3Key
        Glide.with(activity?.applicationContext as Context)
                .load(heroImageURL)
                .into(fragment_story_detail_iv_hero_image)

//            fragment_story_detail_tv_title?.text = mStory?.headline

        /**
         * checking for sections display name if it's null then checking for section's name
         */
        var sectionName: String? = null
        val storySection = mStory.sections?.first()
        if (storySection?.displayName != null)
            sectionName = storySection.displayName
        else if (storySection?.name != null)
            sectionName = storySection.name
        if (!TextUtils.isEmpty(sectionName)) {
            fragment_story_detail_tv_section_name.text = sectionName
            fragment_story_detail_ll_section?.visibility = View.VISIBLE
        }

        /**
         * checking for story headline
         */
        val mStoryTitle: String? = mStory.headline
        if (!TextUtils.isEmpty(mStoryTitle)) {
            fragment_story_detail_tv_title?.visibility = View.VISIBLE
            fragment_story_detail_tv_title.minLines = 2
            fragment_story_detail_tv_title.text = mStory.headline
        }

        val storyDetailAdapter = StoryDetailAdapter(mStory, fragmentCallbacks)
        fragment_story_detail_rv_recycler_view?.adapter = storyDetailAdapter
    }
}