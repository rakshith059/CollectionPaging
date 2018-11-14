package quintype.com.templatecollectionwithrx.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.text.TextUtils
import quintype.com.templatecollectionwithrx.models.MenuItemModel
import quintype.com.templatecollectionwithrx.ui.main.fragments.MainFragment
import quintype.com.templatecollectionwithrx.ui.main.fragments.SectionFragment
import quintype.com.templatecollectionwithrx.utils.Constants

class HomePagerAdapter(fm: FragmentManager?, private val menuItemModelList: List<MenuItemModel>, private val isMenuGroupNull: Boolean) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return if (!isMenuGroupNull)
            SectionFragment().newInstance(menuItemModelList.get(position).sectionSlug, menuItemModelList.get(position).mTitle)
        else
            MainFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val mPageTitle = menuItemModelList.get(position).mTitle
        return if (!TextUtils.isEmpty(mPageTitle))
            mPageTitle
        else Constants.COLLECTION_HOME
    }

    override fun getCount(): Int {
        return menuItemModelList.size
    }
}
