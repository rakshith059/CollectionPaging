package quintype.com.templatecollectionwithrx.ui.main.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.util.Log
import quintype.com.templatecollectionwithrx.R
import android.view.MotionEvent
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.models.StoryElementList
import me.relex.circleindicator.CircleIndicator
import android.view.MenuItem
import quintype.com.templatecollectionwithrx.adapters.GalleryAdapter
import quintype.com.templatecollectionwithrx.utils.Constants


class ImagePreviewActivity : BaseActivity() {

    internal var viewPager: ViewPager? = null
    internal var indicator: CircleIndicator? = null
    internal var position: Int = 0
    internal var photoList: StoryElementList? = null
    internal var elements: List<StoryElement>? = null
    internal var mActionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)

        //setting the screen name for analytics purposes

        // set the action bar to have an up button, but no title, so that
        // it does not interfere with the images
        mActionBar = supportActionBar
        mActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActionBar!!.setDisplayShowTitleEnabled(false)

        //get the list of images and the image position from the intent
        photoList = intent.getParcelableExtra(Constants.PHOTOS_LIST)
        position = photoList?.mSelectedItem as Int
        elements = photoList?.mItems

        viewPager = findViewById(R.id.activity_image_preview_slider)
        indicator = findViewById(R.id.activity_image_preview_indicator)

        //create an adapter for the viewPager using the list of images
        val adapter = GalleryAdapter(elements as List<StoryElement>)
        viewPager?.adapter = adapter
        viewPager?.currentItem = position
        indicator?.setViewPager(viewPager)
    }

    /**
     * Make sure that when the up button in the action bar is pressed, the app goes
     * back to the screen it was previously on.
     *
     * @param item The menu item that was clicked
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    /**
     * If used inside a viewPager, the PhotoView library used to pinch zoom these images
     * throws an exception and crashes the app. This method handles that exception and prevents
     * the app from crashing.
     *
     * @param ev The touch motion event that triggered this method
     * @return
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        try {
            return super.dispatchTouchEvent(ev)
        } catch (e: IllegalArgumentException) {
            //Log the exception
            Log.d("Rakshith", e.message)
            return false
        }

    }
}

