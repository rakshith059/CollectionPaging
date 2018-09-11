package quintype.com.templatecollectionwithrx.ui.main.fragments


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_story_detail.*
import kotlinx.android.synthetic.main.retry_layout.*

import quintype.com.templatecollectionwithrx.R
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
        var mStoryList = ArrayList<Story>()

        fun newInstance(mStoryList: ArrayList<Story>): StoryDetailFragment {
            val fragment = StoryDetailFragment()
            val args = Bundle()
            fragment.arguments = args
            this.mStoryList = mStoryList
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
        loadStoryDetailIfInternetPresent(mStoryList.get(0).slug())
    }

    private fun loadStoryDetailIfInternetPresent(storySlug: String) {
        if (NetworkUtils.isConnected(activity?.applicationContext as Context)) {
            loadStoryDetailBySlug(storySlug)
        } else {
            swipeContainer.isRefreshing = false
            fragment_story_detail_fl_main_container.visibility = View.GONE
            retry_button.visibility = View.VISIBLE
        }
    }

    private fun loadStoryDetailBySlug(storySlug: String) {
        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)

        storyViewModel?.getStoryDetailBySlug(storySlug)
    }
}