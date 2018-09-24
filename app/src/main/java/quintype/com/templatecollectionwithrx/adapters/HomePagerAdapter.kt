package quintype.com.templatecollectionwithrx.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class HomePagerAdapter(fm: FragmentManager?, fragmentList: List<Fragment>, title: String) : FragmentPagerAdapter(fm) {
    var fragmentList = fragmentList
    var mTitle = title

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
//        return fragmentList.get(position).arguments?.getString()
        return mTitle
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}
