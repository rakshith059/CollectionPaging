package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_story_pager.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.StoryPagerAdapter
import quintype.com.templatecollectionwithrx.models.story.Story

class StoryPagerFragment : BaseFragment() {
    companion object {
        var mStoryList = ArrayList<Story>()

        fun newInstance(storyList: ArrayList<Story>): StoryPagerFragment {
            val fragment = StoryPagerFragment()
            val args = Bundle()
            fragment.arguments = args
            this.mStoryList = storyList
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_story_pager, container, false)
        return view
    }

    private fun getFragmentList(): List<Fragment> {
        var fragmentList = ArrayList<Fragment>()
        for (index in 0 until mStoryList.size)
            fragmentList.add(index, StoryDetailFragment.newInstance(mStoryList.get(index)))

        return fragmentList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pagerAdapter = StoryPagerAdapter(childFragmentManager, getFragmentList())
        story_pager_vp_pager.adapter = pagerAdapter
    }
}