package quintype.com.templatecollectionwithrx.utils

import android.annotation.TargetApi
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout


/**
 * Created TemplateCollectionWithRx by rakshith on 9/25/18.
 */
class Utilities {
    companion object {
        /**
         * Parsing html text and converting the same to string and trimming the extra space
         */
        @TargetApi(Build.VERSION_CODES.N)
        fun parseHtml(mSource: String): String? {
            return Html.fromHtml(mSource, Html.FROM_HTML_MODE_LEGACY).toString().trim()
        }

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
    }
}