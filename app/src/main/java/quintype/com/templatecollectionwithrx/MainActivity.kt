package quintype.com.templatecollectionwithrx

import android.os.Bundle
import quintype.com.templatecollectionwithrx.ui.main.fragments.HomePagerFragment
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupHomeScreen()

        //set pager adapter
//        val pagerAdapter = HomePagerAdapter(supportFragmentManager, getFragmentList(), resources.getString(R.string.app_name))
//        pager.adapter = pagerAdapter
//        //link the tab layout and viewpager
//        main_activity_tab_layout.setupWithViewPager(pager)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//        }
    }

//    private fun getFragmentList(): List<Fragment> {
//        var fragmentList = ArrayList<Fragment>()
//        for (index in 0 until 1)
//            fragmentList.add(MainFragment.newInstance())
//
//        return fragmentList
//    }

    private fun setupHomeScreen() {
        //if the backstack is empty/the app has just been launched
        if (getmFragment() == null) {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.home_container, HomePagerFragment.newInstance())
//            fragmentTransaction.commit()
            addFragment(HomePagerFragment.newInstance(), R.id.home_container, null)
        }
//        displayDetailScreen()
    }

}
