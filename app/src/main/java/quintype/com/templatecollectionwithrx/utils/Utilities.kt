package quintype.com.templatecollectionwithrx.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.preference.PreferenceManager
import android.support.constraint.ConstraintLayout
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.support.annotation.DimenRes
import android.util.TypedValue
import android.view.WindowManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import quintype.com.templatecollectionwithrx.models.sections.SectionMeta
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*


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


        fun dpToPx(dp: Float, res: Resources): Int {
            return TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dp,
                    res.getDisplayMetrics()).toInt()
        }

        fun dpToPx(@DimenRes resId: Int, res: Resources): Int {
            return dpToPx(res.getDimension(resId), res)
        }

        /**
         * function for getting screen width
         */
        fun getScreenWidth(context: Context?): Int {
            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            val width = size.x
            return width
        }

        /**
         * function for getting screen height
         */
        fun getScreenHeight(context: Context?): Int {
            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            val height = size.y
            return height
        }

        /**
         * function for setting shared preference value
         */
        fun setSharedPreferences(mContext: Context, mSharedPreferencesKey: String, mSharedPreferencesValue: String) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
            var editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(mSharedPreferencesKey, mSharedPreferencesValue)
            editor.apply()
        }

        /**
         * function for getting shared preference value
         */
        fun getSharedPreferences(mContext: Context, mSharedPreferencesKey: String): String {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
            return sharedPreferences.getString(mSharedPreferencesKey, "")
        }

        /**
         * function for formatting the date
         */
        fun formatDate(dateFormat: String): String {
            var formatter = SimpleDateFormat("dd MMM,yyyy HH:mm a")
            var dateString: String = formatter.format(Date(dateFormat.toLong()))
            return dateString
        }

        fun getCollectionSlug(context: Context, sectionName: String): String? {
            val sectionsAsString = Utilities.getSharedPreferences(context, Constants.SP_SECTIONS)

            val listType: Type = object : TypeToken<ArrayList<SectionMeta>>() {}.type

            val sections = Gson().fromJson<List<SectionMeta>>(sectionsAsString, listType)
            var collectionSlug: String? = null

            for (section: SectionMeta in sections) {
                if (sectionName.equals(section.name)) {
                    if (section.collectionMeta?.slug != null) {
                        collectionSlug = section?.collectionMeta?.slug!!
                    }
                    break
                }
            }
            return collectionSlug
        }
    }
}