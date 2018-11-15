package quintype.com.templatecollectionwithrx.ui.main.activities

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks


abstract class BaseActivity : AppCompatActivity(), FragmentCallbacks, FragmentManager.OnBackStackChangedListener {

    var mFragmentList: ArrayList<Fragment> = ArrayList()
    var mFragment: Fragment? = null
    var mContext: AppCompatActivity? = AppCompatActivity()
    //    private val mTracker: Tracker? = null
//    var firebaseAnalytics: FirebaseAnalytics? = null
    var FRAGMENT_TRANSITION_TOP_TO_BOTTOM = "fragment_transition_top_to_bottom"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mContext = this
    }

    override fun addFragment(fragment: Fragment, mBackStack: String?) {
        if (mContext == null) {
            return
        }
        mFragmentList.add(fragment)

        val fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.pop_enter_anim, R.anim.pop_exit_anim)

        fragmentTransaction?.add(R.id.home_container, fragment)

        if (mBackStack != null) {
            fragmentTransaction?.addToBackStack(mBackStack)
        }
        mFragment = fragment
        fragmentTransaction?.commitAllowingStateLoss()
    }

    override fun replaceFragment(fragment: Fragment, mBackStack: String?) {
        if (mContext == null) {
            return
        }
        mFragmentList.add(fragment)
        val fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.pop_enter_anim, R.anim.pop_exit_anim)

        fragmentTransaction?.replace(R.id.home_container, fragment)

        if (mBackStack != null) {
            fragmentTransaction?.addToBackStack(mBackStack)
        }
        mFragment = fragment
        fragmentTransaction?.commit()
    }

    fun getmFragment(): Fragment? {
        return if (mFragmentList?.size > 0) {
            mFragmentList[mFragmentList?.size - 1]
        } else null
    }

    override fun clickAnalyticsEvent(categoryId: String, actionId: String, labelId: String, value: Long) {
//        if (mTracker != null)
//            mTracker.send(HitBuilders.EventBuilder()
//                    .setCategory(categoryId)
//                    .setAction(actionId)
//                    .setLabel(labelId)
//                    .setValue(value)
//                    .build())
//
//        val bundle = Bundle()
//        bundle.putString(Constants.PARAM_SCREEN, categoryId)
//        bundle.putString(Constants.PARAM_LABEL, labelId)
//
//        firebaseAnalytics.logEvent(Constants.PARAM_EVENT, bundle)

        // Log.d("Test Analytics ", categoryId + " " + actionId + " " + labelId + " " + value);
    }

    /**
     * To handle click events from viewholders etc.
     *
     * @param event the data from the click event fired
     */
    override fun propagateEvent(event: Pair<String, Any>) {
        onPropagateEvent(event, this)
    }

    protected fun onPropagateEvent(event: Pair<String, Any>, context: Context) {
    }

}
