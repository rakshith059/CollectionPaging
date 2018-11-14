package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home_pager.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.HomePagerAdapter
import quintype.com.templatecollectionwithrx.models.MenuItemModel
import quintype.com.templatecollectionwithrx.models.NavMenuGroup
import quintype.com.templatecollectionwithrx.utils.Utilities

class HomePagerFragment : BaseFragment() {
    private var navMenuGroup: NavMenuGroup? = null
    val childSectionList = ArrayList<MenuItemModel>()

    companion object {
        private val NAV_MENU_GROUP = "navMenuGroup"

        fun newInstance(menuGroup: NavMenuGroup?): HomePagerFragment {
            val fragment = HomePagerFragment()
            val args = Bundle()
            args.putParcelable(NAV_MENU_GROUP, menuGroup)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_pager, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navMenuGroup = arguments?.getParcelable(NAV_MENU_GROUP)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (navMenuGroup != null && navMenuGroup?.getMenuItem()?.section()?.name != null) {
            //Let's create the list of fragments that this viewPager should display
            // since the parent menu item is the first element in the viewPager, create a
            //next, add fragments for all the child items in the navMenuGroup


            // fragment for that first and add it to the list of fragments
            val collectionSlug = Utilities.getCollectionSlug(this.requireContext(), navMenuGroup?.getMenuItem()?.section()?.name!!)
            if (collectionSlug != null)
                childSectionList.add(MenuItemModel(collectionSlug, navMenuGroup?.menuItem?.title()))

            for (menuItem in navMenuGroup?.childItemList!!) {
                if (menuItem.sectionName() != null) {
                    val childSectionCollectionSlug = Utilities.getCollectionSlug(this.requireContext(), menuItem.sectionName())
                    if (childSectionCollectionSlug != null)
                        childSectionList.add(MenuItemModel(menuItem.sectionSlug(), menuItem.title()))
                }
            }

            /*TODO Hardcoded the tabs for testing.*//*
            childSectionList.add(MenuItemModel("news", "News"))
            childSectionList.add(MenuItemModel("politics", "Politics"))
            childSectionList.add(MenuItemModel("sports", "Sports"))
            childSectionList.add(MenuItemModel("business1", "Business"))*/

            val pagerAdapter = HomePagerAdapter(childFragmentManager, childSectionList, false)
            home_pager_vp_pager.adapter = pagerAdapter
        } else {
            var homeFragment = ArrayList<MenuItemModel>()
            homeFragment.add(MenuItemModel("", ""))

            val pagerAdapter = HomePagerAdapter(childFragmentManager, homeFragment, true)
            home_pager_vp_pager.adapter = pagerAdapter
        }

        //link the tab layout and viewpager
        fragment_home_pager_tab_layout.setupWithViewPager(home_pager_vp_pager)
    }

    fun setCurrentItem(pos: Int) {
        if (home_pager_vp_pager != null && childSectionList.size > pos) {
            home_pager_vp_pager.currentItem = pos
        }
    }
}
