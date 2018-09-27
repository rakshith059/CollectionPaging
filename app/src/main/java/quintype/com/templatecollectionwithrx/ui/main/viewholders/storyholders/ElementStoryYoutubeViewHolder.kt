package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.ui.main.fragments.YoutubeFragment
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.Utilities


class ElementStoryYoutubeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), YouTubeThumbnailView.OnInitializedListener {
    var thumbView: YouTubeThumbnailView? = null
    var youTubeKey: String? = null
    var thumbLoader: YouTubeThumbnailLoader? = null
    var mStory: Story? = null
    var mFragmentCallbacks: FragmentCallbacks? = null

    companion object {
        val TAG = ElementStoryYoutubeViewHolder.javaClass.simpleName

        fun create(parent: ViewGroup, mStory: Story?, mFragmentCallbacks: FragmentCallbacks?): ElementStoryYoutubeViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_layout_youtube_view_holder, parent, false)
            val elementStoryYoutubeViewHolder = ElementStoryYoutubeViewHolder(view)
            elementStoryYoutubeViewHolder.thumbView = view.findViewById(R.id.qs_youtube_thumbnail) as YouTubeThumbnailView
            elementStoryYoutubeViewHolder.youTubeKey = parent.getContext().getString(R.string.youtube_key)

            elementStoryYoutubeViewHolder.thumbView?.setPadding(Utilities.dpToPx(R.dimen.size_8dp, view.resources),
                    Utilities.dpToPx(R.dimen.size_2dp, view.resources), Utilities.dpToPx(R.dimen.size_8dp, view.resources),
                    Utilities.dpToPx(R.dimen.size_2dp, view.resources))

            elementStoryYoutubeViewHolder.mStory = mStory
            elementStoryYoutubeViewHolder.mFragmentCallbacks = mFragmentCallbacks
            return elementStoryYoutubeViewHolder
        }
    }

    fun bind(element: StoryElement) {
        if (element.url() == null) {
            return
        }
        val uri = Uri.parse(element.url())
        val videoId = uri.getQueryParameter("v")
        thumbView?.tag = videoId
        thumbView?.initialize(youTubeKey, this)
        thumbView?.setImageResource(R.drawable.scrim)

        itemView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(view: View) {

            }

            override fun onViewDetachedFromWindow(view: View) {
                itemView.removeOnAttachStateChangeListener(this)
                if (thumbLoader != null) {
                    try {
                        thumbLoader?.setOnThumbnailLoadedListener(null)
                        thumbLoader?.release()
                    } catch (ile: IllegalStateException) {
                        //May be this youtube thumbnail loader already is released, so catch the
                        // exception here
                    }

                }
            }
        })
        itemView.setOnClickListener {
            mFragmentCallbacks?.addFragment(YoutubeFragment.newInstance(element, mStory), TAG)
        }
    }

    override fun onInitializationSuccess(youTubeThumbnailView: YouTubeThumbnailView,
                                         youTubeThumbnailLoader: YouTubeThumbnailLoader) {
        val videoId = youTubeThumbnailView.tag as String
        youTubeThumbnailLoader.setVideo(videoId)
        thumbLoader = youTubeThumbnailLoader
    }

    override fun onInitializationFailure(youTubeThumbnailView: YouTubeThumbnailView,
                                         youTubeInitializationResult: YouTubeInitializationResult) {
        thumbLoader = null
    }

}
