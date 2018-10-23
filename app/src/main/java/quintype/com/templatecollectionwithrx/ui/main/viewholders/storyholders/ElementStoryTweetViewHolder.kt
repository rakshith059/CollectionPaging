package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.TweetView
import com.twitter.sdk.android.core.TwitterException
import android.R.attr.data
import android.util.Log
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.tweetui.TweetUtils
import com.example.androidcore.models.story.StoryElement


class ElementStoryTweetViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    private var mTweetView: TweetView? = null
    private var mBoundedTweet: Long = 0

    companion object {
        fun create(parent: ViewGroup): ElementStoryTweetViewHolder {
            val tweetView = TweetView(parent.context, null as Tweet?)
            tweetView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            val padding = parent.context.resources.getDimensionPixelSize(R.dimen.size_2dp)
            tweetView.setPadding(padding, padding, padding, padding)
            tweetView.setBackgroundColor(Color.WHITE)
            val tweetViewHolder = ElementStoryTweetViewHolder(tweetView)
            tweetViewHolder.mTweetView = tweetView
            return tweetViewHolder
        }
    }

    fun bind(element: StoryElement) {
        val tweetId = element.tweetId() as Long
        if (mBoundedTweet !== tweetId) {
            TweetUtils.loadTweet(java.lang.Long.valueOf(tweetId), object : Callback<Tweet>() {
                override fun success(result: Result<Tweet>) {
                    mTweetView?.tweet = result.data
                }

                override fun failure(e: TwitterException) {
                    Log.d("Rakshith", "Failed loading tweet with id $tweetId")
                }
            })
            mBoundedTweet = tweetId
        } else {
            Log.d("Rakshith", "Skip tweet load")
        }
    }
}
