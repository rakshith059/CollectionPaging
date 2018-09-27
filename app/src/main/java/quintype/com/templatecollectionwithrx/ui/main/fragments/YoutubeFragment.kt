package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.Card
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.models.story.StoryElement

/**
 * Created TemplateCollectionWithRx by rakshith on 9/26/18.
 */

class YoutubeFragment : BaseFragment(), YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener, YouTubePlayer.OnFullscreenListener {
    private var mStoryElement: StoryElement? = null
    private var mStory: Story? = null

    private var mDecorView: View? = null
    private var mYoutubePlayer: YouTubePlayer? = null
    private var mCard: Card? = null
    private var mYoutubeUri: Uri? = null
    private var mIsResumed: Boolean = false
    private var mAutoRotation = false

    companion object {
        private val PORTRAIT_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
        private val LANDSCAPE_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        fun newInstance(element: StoryElement, mStory: Story?): YoutubeFragment {
            val youtubeFragment = YoutubeFragment()
            val args = Bundle()
            youtubeFragment.arguments = args

            youtubeFragment.mStoryElement = element
            youtubeFragment.mStory = mStory
            return youtubeFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_youtube_player, container, false)

        mAutoRotation = Settings.System.getInt(activity?.contentResolver,
                Settings.System.ACCELEROMETER_ROTATION, 0) === 1
        mDecorView = activity?.window?.decorView
//        retrieveCard()
        mYoutubeUri = Uri.parse(mStoryElement?.url())


        val youTubePlayerFragment = childFragmentManager.findFragmentById(R.id.activity_youtube_player_youtube_fragment)

        if (youTubePlayerFragment != null) {
            youTubePlayerFragment as YouTubePlayerSupportFragment
            youTubePlayerFragment.initialize(getString(R.string.youtube_key), this)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        mIsResumed = true
    }

    override fun onPause() {
        mIsResumed = false
        super.onPause()
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer,
                                         wasRestored: Boolean) {

        if (!wasRestored) {
            var s = mYoutubeUri?.getQueryParameter("v")
            s = s!!.split("\\?".toRegex(), 2).toTypedArray()[0]
            Log.d("Rakshith", "Youtube id = $s")
            youTubePlayer.loadVideo(s)
        }
        youTubePlayer.setPlaybackEventListener(this)
        youTubePlayer.setPlayerStateChangeListener(this)


        mYoutubePlayer = youTubePlayer
        mYoutubePlayer?.setOnFullscreenListener(this)

        /*if (mAutoRotation) {
            mYoutubePlayer.addFullscreenControlFlag(YouTubePlayer
                    .FULLSCREEN_FLAG_CONTROL_ORIENTATION
                    | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                    | YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE
                    | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        } else {
            mYoutubePlayer.addFullscreenControlFlag(YouTubePlayer
                    .FULLSCREEN_FLAG_CONTROL_ORIENTATION
                    | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                    | YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        }*/
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider,
                                         youTubeInitializationResult: YouTubeInitializationResult) {
        Log.d("Rakshith", "Initialization failed %${youTubeInitializationResult.toString()}")
    }


    override fun onPlaying() {
        val currentMillis = mYoutubePlayer?.currentTimeMillis?.toLong()
//        Log.d("Rakshith", "Playing at - " + currentMillis / 1e3)
//        if (mIsResumed && mStory != null && mCard != null && mStoryElement != null) {
////            Quintype.quintypeAnalyticsService().notifyStoryElementAction(mStory, mCard, mStoryElement,
////                    currentMillis,
////                    StoryElementActionEvent.ACTION.PLAY)
//        }
    }

    override fun onPaused() {
        val currentMillis = mYoutubePlayer?.currentTimeMillis?.toLong()
//        Log.d("Rakshith", "Paused at - " + currentMillis / 1e3)
//        if (mIsResumed && mStory != null && mCard != null && mStoryElement != null /*&& Quintype.status() === Quintype.CONNECTED*/) {
////            Quintype.quintypeAnalyticsService().notifyStoryElementAction(mStory, mCard, mStoryElement,
////                    currentMillis,
////                    StoryElementActionEvent.ACTION.PAUSE)
//        }
    }

    override fun onStopped() {
        val currentMillis = mYoutubePlayer?.currentTimeMillis?.toLong()
//        Log.d("Rakshith", "Paused at - " + currentMillis / 1e3)
    }

    override fun onBuffering(b: Boolean) {

    }

    override fun onSeekTo(i: Int) {

    }

    override fun onLoading() {

    }

    override fun onLoaded(s: String) {

    }

    override fun onAdStarted() {

    }

    override fun onVideoStarted() {
        Log.d("Rakshith", "On video started")
    }

    override fun onVideoEnded() {
        Log.d("Rakshith", "On video ended")
        val currentMillis = mYoutubePlayer?.currentTimeMillis?.toLong()
//        if (mIsResumed && mStory != null && mCard != null && mStoryElement != null/* && Quintype.status() === Quintype.CONNECTED*/) {
////            Quintype.quintypeAnalyticsService().notifyStoryElementAction(mStory, mCard, mStoryElement,
////                    currentMillis,
////                    StoryElementActionEvent.ACTION.COMPLETE)
//        }
    }

    override fun onError(errorReason: YouTubePlayer.ErrorReason) {

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            if (mYoutubePlayer != null)
                mYoutubePlayer?.setFullscreen(true)
        }
        if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            if (mYoutubePlayer != null)
                mYoutubePlayer?.setFullscreen(false)
        }
    }

    override fun onFullscreen(fullsize: Boolean) {
        if (fullsize) {
            activity?.requestedOrientation = LANDSCAPE_ORIENTATION
        } else {
            activity?.requestedOrientation = PORTRAIT_ORIENTATION
        }
    }
}