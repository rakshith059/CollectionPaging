package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home_pager.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.HomePagerAdapter
import quintype.com.templatecollectionwithrx.models.NavMenuGroup
import quintype.com.templatecollectionwithrx.models.sections.SectionMeta
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities
import java.lang.reflect.Type

class HomePagerFragment : BaseFragment() {
    private var navMenuGroup: NavMenuGroup? = null
    val childSectionFragments = java.util.ArrayList<SectionFragment>()

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
                childSectionFragments.add(SectionFragment.newInstance(collectionSlug, navMenuGroup?.menuItem?.title()))

            for (menuItem in navMenuGroup?.childItemList!!) {
                if (menuItem.sectionName() != null) {
                    val childSectionCollectionSlug = Utilities.getCollectionSlug(this.requireContext(), menuItem.sectionName())
                    if (childSectionCollectionSlug != null)
                        childSectionFragments.add(SectionFragment.newInstance(childSectionCollectionSlug, menuItem.title()))
                }
            }

            val pagerAdapter = HomePagerAdapter(childFragmentManager, childSectionFragments)
            home_pager_vp_pager.adapter = pagerAdapter
        } else {
            var homeFragment = ArrayList<Fragment>()
            homeFragment.add(MainFragment.newInstance())

            val pagerAdapter = HomePagerAdapter(childFragmentManager, homeFragment)
            home_pager_vp_pager.adapter = pagerAdapter
        }

        //link the tab layout and viewpager
        fragment_home_pager_tab_layout.setupWithViewPager(home_pager_vp_pager)
    }

    fun setCurrentItem(pos: Int) {
        if (home_pager_vp_pager != null && childSectionFragments.size > pos) {
            home_pager_vp_pager.currentItem = pos
        }
    }
}
