package quintype.com.templatecollectionwithrx.adapters

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.support.v4.view.PagerAdapter
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities

class GalleryAdapter
/**
 * Load all the images elements in the story into the adapter
 *
 * @param imageElements a list of all the images in the story
 */
(var imageElements: List<StoryElement>) : PagerAdapter() {
    var ivPhoto: PhotoView? = null
    var tvCaption: TextView? = null

    /**
     * A method required to inflate each item in the viewPager
     *
     * @param container the viewPager in which each of these items are to be inflated
     * @param position  the viewPager position at which to inflate the item
     * @return the inflated view
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        //Inflate the layout into the container and find the views in the layout
        val context = container.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.gallery_item_view, container, false)
        ivPhoto = itemView.findViewById(R.id.gallery_item_view_photo)
        tvCaption = itemView.findViewById(R.id.gallery_item_view_photo_title)

        //Next, get the display width of the device in which the app is running. This is required
        //for Glide, the image loader library being used.
        val display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x

        val cdnHostName = Utilities.getSharedPreferences(ivPhoto?.context as Context, Constants.SP_CDN_IMAGE_NAME)
        val heroImageURL = cdnHostName + imageElements[position].imageS3Key()

        Glide.with(ivPhoto?.context as Context)
                .load(heroImageURL)
                .into(ivPhoto as ImageView)

        //And load the caption, if the image has one.
        if (!TextUtils.isEmpty(imageElements[position].title())) {
            tvCaption?.visibility = View.VISIBLE
            Utilities.parseHtml(imageElements[position].title())
        } else
            tvCaption?.visibility = View.GONE

        //Finally, tell the viewPager to include this view and return the view to the system.
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    //return the number of elements that the viewPager is supposed to hold
    override fun getCount(): Int {
        return imageElements.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }
}