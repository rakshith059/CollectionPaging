package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.Utilities


/**
 * Created TemplateCollectionWithRx by rakshith on 9/5/18.
 */

open class BaseFragment : Fragment() {

    var fragmentCallbacks: FragmentCallbacks? = null
    internal open var TAG = BaseFragment::class.java.simpleName
    //    private var mTracker: Tracker? = null

    /*
     * onAttach(Context) is not called on pre API 23 versions of Android and onAttach(Activity) is deprecated
     * Use onAttachToContext instead
     */
    @TargetApi(23)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachToContext(context)
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity)
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected fun onAttachToContext(context: Context) {
        if (context is FragmentCallbacks) {
            fragmentCallbacks = context
        } else {
            throw RuntimeException(context.toString() + " must implement FragmentCallback methods")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mTracker = (getActivity().getApplication() as TheQuintApplication).getDefaultTracker()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        if (!TextUtils.isEmpty(screenName)) {
//            if (mTracker != null)
//                mTracker!!.setScreenName(screenName)
//        } else {
//            // Log.d(TAG, "Screen Name is empty");
//        }
    }

    override fun onResume() {
        super.onResume()

//        if (!TextUtils.isEmpty(screenName)) {
//            if (mTracker != null)
//                mTracker!!.send(HitBuilders.ScreenViewBuilder()
//                        .build())
//        } else {
//            //Timber.d("Screen name is empty");
//        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentCallbacks = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Constants.freeMemory();
    }

    /**
     * This method is used to pop recently added fragment from stack
     */
    fun OnBackPressed() {
        fragmentManager?.popBackStackImmediate()
    }

    /**
     * This method is used to share story link
     */
    fun ShareCurrentStory(mStorySlug: String) {
        val mShareLink = Utilities.getStoryLink(mStorySlug)

        if (!TextUtils.isEmpty(mShareLink)) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, mShareLink)
            sendIntent.type = "text/plain"
            context?.startActivity(sendIntent)
        }
    }
}
