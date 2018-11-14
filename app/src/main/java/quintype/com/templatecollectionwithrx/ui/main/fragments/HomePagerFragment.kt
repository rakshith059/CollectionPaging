package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.HomePagerAdapter
import quintype.com.templatecollectionwithrx.models.MenuItemModel
import quintype.com.templatecollectionwithrx.models.NavMenuGroup
import quintype.com.templatecollectionwithrx.utils.Constants.Companion.NAV_MENU_GROUP
import quintype.com.templatecollectionwithrx.utils.Utilities

class HomePagerFragment : BaseFragment() {
    private var navMenuGroup: NavMenuGroup? = null
    private var childSectionList = ArrayList<MenuItemModel>()
    private var homePagerAdapter: HomePagerAdapter? = null

    private lateinit var homeViewPager: ViewPager
    private lateinit var homeTabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_pager, container, false)
        homeViewPager = view.findViewById(R.id.home_view_pager)
        homeTabLayout = view.findViewById(R.id.home_tab_layout)

        if (homePagerAdapter != null) {
            /*Set adapter again for recreated view*/
            homeViewPager.adapter = homePagerAdapter
            homeTabLayout.setupWithViewPager(homeViewPager)
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navMenuGroup = arguments?.getParcelable(NAV_MENU_GROUP)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (homePagerAdapter == null) {
            if (navMenuGroup != null && navMenuGroup?.getMenuItem()?.section()?.name != null) {
                //Let's create the list of fragments that this viewPager should display
                // since the parent menu item is the first element in the viewPager, create a
                //next, add fragments for all the child items in the navMenuGroup

                /*Add parent menu item first*/
                val collectionSlug = Utilities.getCollectionSlug(this.requireContext(), navMenuGroup?.getMenuItem()?.section()?.name!!)
                if (collectionSlug != null)
                    childSectionList.add(MenuItemModel(collectionSlug, navMenuGroup?.menuItem?.title()))

                /*Add child menu items now*/
                for (menuItem in navMenuGroup?.childItemList!!) {
                    if (menuItem.sectionName() != null) {
                        val childSectionCollectionSlug = Utilities.getCollectionSlug(this.requireContext(), menuItem.sectionName())
                        if (childSectionCollectionSlug != null)
                            childSectionList.add(MenuItemModel(childSectionCollectionSlug, menuItem.title()))
                    }
                }

                homePagerAdapter = HomePagerAdapter(childFragmentManager, childSectionList, false)
                homeViewPager.adapter = homePagerAdapter
            } else {
                var homeFragment = ArrayList<MenuItemModel>()
                homeFragment.add(MenuItemModel("", ""))

                homePagerAdapter = HomePagerAdapter(childFragmentManager, homeFragment, true)
                homeViewPager.adapter = homePagerAdapter
            }
        }

        //link the tab layout and viewpager
        homeTabLayout.setupWithViewPager(homeViewPager)
    }

    fun setCurrentItem(pos: Int) {
        if (childSectionList.size > pos) {
            homeViewPager?.currentItem = pos
        }
    }
}
