package quintype.com.templatecollectionwithrx

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig


/**
 * Created TemplateCollectionWithRx by rakshith on 9/26/18.
 */

class TemplateApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig(resources.getString(R.string.twitter_consumer_key), resources.getString(R.string.twitter_consumer_secret)))
                .debug(true)
                .build()
        Twitter.initialize(config)
    }
}