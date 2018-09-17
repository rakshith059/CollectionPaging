package quintype.com.templatecollectionwithrx.utils

import android.support.v4.app.Fragment

/**
 * Created TemplateCollectionWithRx by rakshith on 9/5/18.
 */

interface FragmentCallbacks {
    fun addFragment(fragment: Fragment, mBackStack: String?)

    fun replaceFragment(fragment: Fragment, mBackStack: String?)

    fun clickAnalyticsEvent(categoryId: String, actionId: String, labelId: String, value: Long)

    fun propagateEvent(event: Pair<String, Any>)
}