package quintype.com.templatecollectionwithrx.ui.main.activities

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.app_bar_main.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.DrawerSectionsAdapter
import quintype.com.templatecollectionwithrx.models.config.ConfigLayout
import quintype.com.templatecollectionwithrx.ui.main.fragments.HomePagerFragment
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.models.NavMenu
import quintype.com.templatecollectionwithrx.models.NavMenuGroup
import java.util.*

class MainActivity : BaseActivity(), DrawerSectionsAdapter.OnDrawerItemSelectedListener {
    val TAG = MainActivity::class.java.simpleName
    private var mDrawerLayout: DrawerLayout? = null
    private var navMenuRecyclerview: RecyclerView? = null
    private var drawerAdapter: DrawerSectionsAdapter? = null
    private var toolBar: Toolbar? = null
    private var currentSection: NavMenu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        navMenuRecyclerview = findViewById(R.id.nav_menu_recyclerview)
        toolBar = findViewById(R.id.toolbar)

        setSupportActionBar(toolBar)

        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close)
        mDrawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

        setupHomeScreen()
        setUpNavDrawer()

    }

    private fun setUpNavDrawer() {
        val layoutAsString = Constants.getSharedPreferences(this, Constants.SP_LAYOUT)
        val layoutMenu = Gson().fromJson(layoutAsString, ConfigLayout::class.java)
        val menuList: List<NavMenu>? = layoutMenu.menu

        //two maps are created to avoid ConcurrentModificationException
        val initialMenuMap = HashMap<String, NavMenuGroup>()
        val finalMenuMap = HashMap<String, NavMenuGroup>()

        if (menuList != null)
            for (NavMenu in menuList) {
                val parentId = NavMenu.parentId()
                // If the section doesn't have a parent ID, check if its ID is already there in the map.
                // If it is, set the parent section of that entry as this menu item. Else, add it to
                // the hashMap as a new parent section
                if (parentId == null) {
                    if (initialMenuMap.containsKey(NavMenu.id())) {
                        initialMenuMap.get(NavMenu.id())?.menuItem = NavMenu
                    } else {
                        val parentSection = NavMenuGroup()
                        parentSection.menuItem = NavMenu
                        initialMenuMap.put(NavMenu.id()!!, parentSection)
                    }
                }
                //if the parentID of this section is already in the hashMap, add it to the hashMap
                //as a child of the existing parent section
                else if (initialMenuMap.containsKey(parentId)) {
                    initialMenuMap[parentId]?.addSubsection(NavMenu)
                }
                //if the section contains a parentId, but that id is not in the hashMap yet, keep the
                //section as a child section but add the parent ID to the hashMap as the ID of a new
                //blank parent section.
                else {
                    val parentSection = NavMenuGroup()
                    parentSection.addSubsection(NavMenu)
                    initialMenuMap.put(parentId, parentSection)
                }
            }

        //if the hashMap has any blank parent section with child sections, remove that parent
        //section and add all the children as separate parent sections.
        for ((_, currentSection) in initialMenuMap) {
            if (currentSection.menuItem == null && !currentSection.childItemList
                            .isEmpty()) {
                for (childItem in currentSection.childItemList) {
                    val parentSection = NavMenuGroup()
                    parentSection.menuItem = childItem
                    childItem?.id()?.let { finalMenuMap.put(it, parentSection) };
                }
            } else {
                finalMenuMap.put(currentSection.menuItem?.id()!!, currentSection);
            }
        }

        //Let's sort the final list of sections so that they appear in the order in which
        //they appear in the website
        val list: MutableList<ParentListItem> = ArrayList(finalMenuMap.values)
        list.sortWith(Comparator { t1, t2 -> ((t1 as NavMenuGroup).menuItem?.rank() as Long).compareTo((t2 as NavMenuGroup).menuItem?.rank()!!) })

        //finally, populate the adapter with the sorted list and set the adapter to the navMenu
        drawerAdapter = DrawerSectionsAdapter(this, list)
        navMenuRecyclerview?.adapter = drawerAdapter
    }

    private fun setupHomeScreen() {
        //if the backstack is empty/the app has just been launched
        if (getmFragment() == null) {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.home_container, HomePagerFragment.newInstance())
//            fragmentTransaction.commit()
            addFragment(HomePagerFragment.newInstance(null), null)
        }
//        displayDetailScreen()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onDrawerItemSelected(menuGroup: NavMenuGroup?) {
        if (menuGroup != null) {
            if (menuGroup.menuItem?.type().equals(NavMenu.TYPE_SECTION, true)) {

                val submenuPosition = menuGroup.position
                val parentMenuId = menuGroup.menuItem.id()

                //if the parent section is currently open, but at a different subsection
                //right now, simply move the viewPager to the correct position
                val fragment = supportFragmentManager.findFragmentById(R.id.home_container)

                if (parentMenuId.equals(currentSection?.id(), true) && fragment is HomePagerFragment) {
                    fragment.setCurrentItem(submenuPosition + 1)
                } else {
                    currentSection = menuGroup.menuItem
                    replaceFragment(HomePagerFragment.newInstance(menuGroup), TAG)
                }
            } else if (menuGroup.menuItem?.type().equals(NavMenu.TYPE_LINK, true)) {
                Toast.makeText(this, "Menu type LINK not yet handled", LENGTH_SHORT).show()
            } else if (menuGroup.menuItem?.type().equals(NavMenu.TYPE_TAG, true)) {
                Toast.makeText(this, "Menu type TAG not yet handled", LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Unknown menu type", LENGTH_SHORT).show()
            }
        }
        mDrawerLayout?.closeDrawer(GravityCompat.START)
    }
}
