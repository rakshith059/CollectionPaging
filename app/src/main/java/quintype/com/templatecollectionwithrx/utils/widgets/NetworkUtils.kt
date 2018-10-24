package quintype.com.templatecollectionwithrx.utils.widgets

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager


/**
 * Created TemplateCollectionWithRx by rakshith on 9/10/18.
 */

object NetworkUtils {
    val TAG = NetworkUtils::class.java.simpleName

    fun isConnected(context: Context): Boolean {
        try {
            val cm = context.getSystemService(Context
                    .CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo.isConnectedOrConnecting
        } catch (e: Exception) {
            return false
        }
    }
}
