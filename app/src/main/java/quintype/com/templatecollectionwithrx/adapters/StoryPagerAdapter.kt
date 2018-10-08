package quintype.com.templatecollectionwithrx.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.fragments.StoryDetailFragment

class StoryPagerAdapter(fm: FragmentManager?, var fragmentList: List<Story>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return StoryDetailFragment.newInstance(fragmentList[position])
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}