package quintype.com.templatecollectionwithrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main_activity.*
import quintype.com.templatecollectionwithrx.adapters.HomePagerAdapter
import quintype.com.templatecollectionwithrx.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //set pager adapter
        val pagerAdapter = HomePagerAdapter(supportFragmentManager, getFragmentList(), resources.getString(R.string.app_name))
        pager.adapter = pagerAdapter
        //link the tab layout and viewpager
        main_activity_tab_layout.setupWithViewPager(pager)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//        }
    }

    private fun getFragmentList(): List<Fragment> {
        var fragmentList = ArrayList<Fragment>()
        for (index in 0 until 5)
            fragmentList.add(MainFragment.newInstance())

        return fragmentList
    }

}
