package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_story_pager.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.StoryPagerAdapter
import quintype.com.templatecollectionwithrx.models.story.Story

class StoryPagerFragment : BaseFragment() {
    companion object {
        var pagerFragmentStoryList = ArrayList<Story>()
        var mPosition = 0

        fun newInstance(storyList: ArrayList<Story>, itemPosition: Int): StoryPagerFragment {
            val fragment = StoryPagerFragment()
            val args = Bundle()
            fragment.arguments = args
            this.pagerFragmentStoryList = storyList
            this.mPosition = itemPosition
            Log.d("Rakshith", "clicked position is $mPosition")

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_story_pager, container, false)
    }

    private fun setAdapter() {
        if (pagerFragmentStoryList != null) {
            val pagerAdapter = StoryPagerAdapter(childFragmentManager, getFragmentList())
            story_pager_vp_pager.adapter = pagerAdapter
            story_pager_vp_pager.currentItem = mPosition
        }
    }

    private fun getFragmentList(): List<Story> {
        var fragmentList = ArrayList<Story>()
        for (index in 0 until pagerFragmentStoryList.size)
            fragmentList.add(index, pagerFragmentStoryList.get(index))

        return fragmentList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (pagerFragmentStoryList == null) {
            activity?.onBackPressed()
        } else {
            setAdapter();
        }
    }
}