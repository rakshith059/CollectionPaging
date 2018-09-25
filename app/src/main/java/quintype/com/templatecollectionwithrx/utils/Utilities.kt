package quintype.com.templatecollectionwithrx.utils

import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.LinearLayout
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.view.ViewGroup


/**
 * Created TemplateCollectionWithRx by rakshith on 9/25/18.
 */
class Utilities {

    companion object {
        fun createLayoutParams(view: View, width: Int, height: Int): ViewGroup.LayoutParams {
            val viewGroup = view.getParent() as ViewGroup
            if (viewGroup is FrameLayout) {
                return createFrameParams(width, height)
            } else if (viewGroup is LinearLayout) {
                return createLinearParams(width, height)
            } else if (viewGroup is RelativeLayout) {
                return createRelativeParams(width, height)
            } else if (viewGroup is ConstraintLayout) {
                return createConstraintParams(width, height)
            }
            return ViewGroup.LayoutParams(width, height)
        }

        private fun createRelativeParams(width: Int, height: Int): RelativeLayout.LayoutParams {
            return RelativeLayout.LayoutParams(width, height)
        }

        private fun createConstraintParams(width: Int, height: Int): ConstraintLayout.LayoutParams {
            return ConstraintLayout.LayoutParams(width, height)
        }

        private fun createFrameParams(width: Int, height: Int): FrameLayout.LayoutParams {
            return FrameLayout.LayoutParams(width, height)
        }

        private fun createLinearParams(width: Int, height: Int): LinearLayout.LayoutParams {
            return LinearLayout.LayoutParams(width, height)
        }

        fun getSharedPrefrenceString(context: Context, key: String): String {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return sharedPreferences.getString(key, "")
        }
    }
}