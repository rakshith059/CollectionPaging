package quintype.com.templatecollectionwithrx.ui.main.activities

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.util.Log
import android.view.MenuItem
import android.view.View
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.ui.main.fragments.HomePagerFragment

class MainActivity : BaseActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mDrawerLayout = findViewById(R.id.drawer_layout)

        mDrawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                Log.d("##NavMenu", "onDrawerSlide");
            }

            override fun onDrawerOpened(drawerView: View) {
                Log.d("##NavMenu", "onDrawerOpened");
            }

            override fun onDrawerClosed(drawerView: View) {
                Log.d("##NavMenu", "onDrawerClosed");
            }

            override fun onDrawerStateChanged(newState: Int) {
                Log.d("##NavMenu", "onDrawerStateChanged");
            }
        }
        )

        setupHomeScreen()
    }

    private fun setupHomeScreen() {
        //if the backstack is empty/the app has just been launched
        if (getmFragment() == null) {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.home_container, HomePagerFragment.newInstance())
//            fragmentTransaction.commit()
            addFragment(HomePagerFragment.newInstance(), null)
        }
//        displayDetailScreen()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
