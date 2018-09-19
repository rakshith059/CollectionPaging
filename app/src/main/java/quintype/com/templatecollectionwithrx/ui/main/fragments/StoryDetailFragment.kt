package quintype.com.templatecollectionwithrx.ui.main.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_story_detail.*
import kotlinx.android.synthetic.main.retry_layout.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.StoryDetailAdapter
import quintype.com.templatecollectionwithrx.models.story.SlugStory
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
import quintype.com.templatecollectionwithrx.viewmodels.StoryViewModel

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
        fragment_story_detail_pb_progress.visibility = View.VISIBLE
    }

    private fun loadStoryDetailIfInternetPresent() {
        if (NetworkUtils.isConnected(activity?.applicationContext as Context)) {
            loadStoryDetailBySlug(mStory.slug)
        } else {
            fragment_story_detail_swipe_refresh_layout.isRefreshing = false
            fragment_story_detail_fl_main_container.visibility = View.GONE
            retry_button.visibility = View.VISIBLE
        }
    }

    private fun loadStoryDetailBySlug(storySlug: String) {
        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)
        storyViewModel?.getStoryDetailBySlug(storySlug)

        var layoutManager = LinearLayoutManager(getActivity())
        fragment_story_detail_rv_recycler_view.layoutManager = layoutManager

        observableStoryViewHolder(storyViewModel)
    }

    private fun observableStoryViewHolder(storyViewModel: StoryViewModel?) {
        storyViewModel?.getStoryObservable()?.observe(this, Observer<SlugStory> {

            fragment_story_detail_pb_progress.visibility = View.GONE
//            fragment_story_detail_swipe_refresh_layout.visibility = View.GONE

            var mStory = it?.story

            var storyDetailAdapter = StoryDetailAdapter(mStory, fragmentCallbacks)
            fragment_story_detail_rv_recycler_view?.adapter = storyDetailAdapter
        })
    }
}