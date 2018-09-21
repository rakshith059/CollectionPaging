package quintype.com.templatecollectionwithrx.utils

import android.annotation.TargetApi
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Html

/**
 * Created TemplateCollectionWithRx by rakshith on 9/21/18.
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
    }
}