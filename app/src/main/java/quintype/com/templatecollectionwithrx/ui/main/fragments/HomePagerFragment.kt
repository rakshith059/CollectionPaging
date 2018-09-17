package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home_pager.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.HomePagerAdapter

class HomePagerFragment : BaseFragment() {
    companion object {
        fun newInstance() = HomePagerFragment()
//        {
//            val fragment = HomePagerFragment()
//            val args = Bundle()
//            fragment.arguments = args
//            return fragment
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_pager, container, false)
        return view
    }

    private fun getFragmentList(): List<Fragment> {
        var fragmentList = ArrayList<Fragment>()
        for (index in 0 until 1)
            fragmentList.add(MainFragment.newInstance())

        return fragmentList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pagerAdapter = HomePagerAdapter(childFragmentManager, getFragmentList(), resources.getString(R.string.app_name))
        home_pager_vp_pager.adapter = pagerAdapter
        //link the tab layout and viewpager
        fragment_home_pager_tab_layout.setupWithViewPager(home_pager_vp_pager)
    }
}
