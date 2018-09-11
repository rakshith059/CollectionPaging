package quintype.com.templatecollectionwithrx.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter

class StoryPagerAdapter(fm: FragmentManager?, fragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {
    var fragmentList = fragmentList

    override fun getItem(position: Int): Fragment {
        var mFragment: Fragment = fragmentList.get(position)
        return mFragment
    }

    override fun getCount(): Int {
        return fragmentList?.size
    }
}
