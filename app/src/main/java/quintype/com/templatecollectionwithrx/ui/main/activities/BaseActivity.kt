package quintype.com.templatecollectionwithrx.ui.main.activities

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks


abstract class BaseActivity : AppCompatActivity(), FragmentCallbacks, FragmentManager.OnBackStackChangedListener {

    var mFragmentList: ArrayList<Fragment> = ArrayList()
    var mFragment: Fragment? = null
    var mContext: AppCompatActivity? = AppCompatActivity()
    //    private val mTracker: Tracker? = null
//    var firebaseAnalytics: FirebaseAnalytics? = null
    var FRAGMENT_TRANSITION_TOP_TO_BOTTOM = "fragment_transition_top_to_bottom"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mContext = this
    }

    override fun addFragment(fragment: Fragment, mBackStack: String?) {
        if (mContext == null) {
            return
        }
        mFragmentList.add(fragment)

        val fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.pop_enter_anim, R.anim.pop_exit_anim)

        fragmentTransaction?.add(R.id.home_container, fragment)

        if (mBackStack != null) {
            fragmentTransaction?.addToBackStack(mBackStack)
        }
        mFragment = fragment
        fragmentTransaction?.commitAllowingStateLoss()
    }

    override fun replaceFragment(fragment: Fragment, mBackStack: String?) {
        if (mContext == null) {
            return
        }
        mFragmentList.add(fragment)
        val fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(this)
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.pop_enter_anim, R.anim.pop_exit_anim)

        fragmentTransaction?.replace(R.id.home_container, fragment)

        if (mBackStack != null) {
            fragmentTransaction?.addToBackStack(mBackStack)
        }
        mFragment = fragment
        fragmentTransaction?.commit()
    }

    fun getmFragment(): Fragment? {
        return if (mFragmentList?.size > 0) {
            mFragmentList[mFragmentList?.size - 1]
        } else null
    }

    override fun clickAnalyticsEvent(categoryId: String, actionId: String, labelId: String, value: Long) {
//        if (mTracker != null)
//            mTracker.send(HitBuilders.EventBuilder()
//                    .setCategory(categoryId)
//                    .setAction(actionId)
//                    .setLabel(labelId)
//                    .setValue(value)
//                    .build())
//
//        val bundle = Bundle()
//        bundle.putString(Constants.PARAM_SCREEN, categoryId)
//        bundle.putString(Constants.PARAM_LABEL, labelId)
//
//        firebaseAnalytics.logEvent(Constants.PARAM_EVENT, bundle)

        // Log.d("Test Analytics ", categoryId + " " + actionId + " " + labelId + " " + value);
    }

    /**
     * To handle click events from viewholders etc.
     *
     * @param event the data from the click event fired
     */
    override fun propagateEvent(event: Pair<String, Any>) {
        onPropagateEvent(event, this)
    }

    protected fun onPropagateEvent(event: Pair<String, Any>, context: Context) {
//        /* if (event.first.equals(Constants.DISQUS_URL)) {
//            String url = (String) event.second;
//            Intent disqusIntent = new Intent(context, DisqusActivity.class);
//            disqusIntent.putExtra(DisqusActivity.EXTRA_URL, url);
//            startActivity(disqusIntent);
//        } else*/
//        if (event.first.equals(Constants.METYPE_URL_KEY)) {
//            val url = event.second as String
//            val publisherID = 1// hardcoding publisherID
//            // int primaryColor = MainActivity.this.getResources().getColor(R.color.colorAccent);
//
//            var data = ByteArray(0)
//            try {
//                data = url.toByteArray(charset("UTF-8"))
//                val base64 = Base64.encodeToString(data, Base64.DEFAULT)
//
//                val meTypeURL = getString(R.string.metype_url) + "iframe?account_id=" + publisherID + "&primary_color=IzRkMDg2YQ==&page_url=" + base64
//                val meTypeIntent = Intent(context, MeTypeActivity::class.java)
//                meTypeIntent.putExtra(Constants.METYPE_URL_KEY, meTypeURL)
//                startActivity(meTypeIntent)
//            } catch (e: UnsupportedEncodingException) {
//                e.printStackTrace()
//            }
//
//            //Demo url for testing purpose
//            //String hardCodedURL = "https://metype-api.staging.quintype.com/iframe-test-widget";
//            //            Uri uri = Uri.parse(meTypeURL);
//            //            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//            //            builder.setToolbarColor(getResources().getColor(R.color.colorAccent));
//            //            CustomTabsIntent customTabsIntent = builder.build();
//            //            customTabsIntent.launchUrl(context, uri);
//        } else if (event.first.equals(Constants.EVENT_LINK_CLICKED)) {
//            val uriDetails = event.second as UriDetails
//            val builder = CustomTabsIntent.Builder()
//            builder.setToolbarColor(ContextCompat.getColor(this, R.color
//                    .colorPrimary))
//            val customTabsIntent = builder.build()
//            val uri = uriDetails.getUri()
//            val parsedUri = if (uri.isAbsolute()) uri else Uri.parse("https://" + uri.toString())
//            when (uriDetails.getType()) {
//                RANDOM ->
//                    //                        is a Random url
//                    //                        openElsewhereStory(uriClicked.toString());
//                    customTabsIntent.launchUrl(this, parsedUri)
//                SECTION ->
//
//                    //                        mSection = new Section();
//                    //                        mSection.mName = uriDetails.getSlug();
//                    //                        setUpMode(MODE_SECTION_LIST);
//
//
//                    customTabsIntent.launchUrl(this, parsedUri)
//                STORY -> {
//                    //                        is a Story url
//                    val slug = uri.getPath().substring(1)
//                    val slugStory = Story.getSlugStory(slug)
//                    replaceFragment(StoryDetailFragment.newInstance(slugStory),
//                            "")
//                }
//            }//                    String storyHeadline = storyList.get(pos).headline();
//            //                    fragmentCallbacks.clickAnalyticsEvent(mFrom, Constants.EVENT_STORY_CLICKED,
//            //                            storyHeadline, 0);
//        } else if (event.first.equalsIgnoreCase(Constants.EVENT_STORY_SHARE)) {
//            onShareClicked(event.second as ShareStoryModel)
//        }
    }

}
