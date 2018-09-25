package quintype.com.templatecollectionwithrx.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import quintype.com.templatecollectionwithrx.utils.Constants

class HomePagerAdapter(fm: FragmentManager?, fragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {
    var fragmentList = fragmentList

    override fun getItem(position: Int): Fragment {
        var mFragment: Fragment = fragmentList.get(position)
        return mFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList.get(position).arguments?.getString(Constants.PAGE_TITLE)
    }

    override fun getCount(): Int {
        return fragmentList?.size
    }
}
