package quintype.com.templatecollectionwithrx.adapters

import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.StoryElementList
import com.example.androidcore.models.story.StoryElement
import quintype.com.templatecollectionwithrx.ui.main.activities.ImagePreviewActivity
import quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders.SlideShowImageHolder
import quintype.com.templatecollectionwithrx.Constants


class ElementStorySlideShowAdapter(private val storyElements: List<StoryElement>, val mImageWidth: Int, val height: Int?) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return storyElements.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_story_element_slideshow_image, container, false)

        val imageHolder = SlideShowImageHolder.create(view, mImageWidth, height)
        val elem = storyElements[position]
        imageHolder.bind(elem)

        imageHolder.ivSlideShowImage?.setOnClickListener({
            val photoList = StoryElementList()
            photoList.mItems = ArrayList(storyElements)
            photoList.mSelectedItem = position

            val intent = Intent(container.context, ImagePreviewActivity::class.java)
            intent.putExtra(Constants.PHOTOS_LIST, photoList)
            container.context.startActivity(intent)
        })
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}