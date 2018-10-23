package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.ElementStorySlideShowAdapter
import com.example.androidcore.models.story.StoryElement
import com.example.androidcore.models.storypresenter.ElementViewType
import quintype.com.templatecollectionwithrx.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities


class ElementStorySlideShowViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    private var vpSlideShowPager: ViewPager? = null

    private var ivSlideLeft: ImageView? = null
    private var ivSlideRight: ImageView? = null

    private var mImageWidth: Int = 0
    private var mImageHeight: Int = 0

    companion object {
        var isGalleryElement: Boolean = false

        fun create(parent: ViewGroup, viewType: Int): ElementStorySlideShowViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_slide_show_view_holder, parent, false)
            val elementStorySlideShowViewHolder = ElementStorySlideShowViewHolder(view)

            elementStorySlideShowViewHolder.ivSlideLeft = view.findViewById(R.id.story_slide_show_view_holder_iv_left_arrow)
            elementStorySlideShowViewHolder.ivSlideRight = view.findViewById(R.id.story_slide_show_view_holder_iv_right_arrow)

            var mWidth = parent.width
            if (viewType == ElementViewType.GALLERY) {
//                mWidth = parent.width - 100

                isGalleryElement = true

                elementStorySlideShowViewHolder.ivSlideRight?.visibility = View.INVISIBLE
            }
            val mHeight = (parent.width * Constants.DIMEN_16_TO_9).toInt()

            elementStorySlideShowViewHolder.vpSlideShowPager = view.findViewById(R.id.story_slide_show_view_holder_view_pager)
            elementStorySlideShowViewHolder.mImageWidth = mWidth
            elementStorySlideShowViewHolder.mImageHeight = mHeight
            elementStorySlideShowViewHolder.vpSlideShowPager?.layoutParams = Utilities.createLayoutParams(elementStorySlideShowViewHolder.vpSlideShowPager as ViewPager, parent.width, mHeight)

            return elementStorySlideShowViewHolder
        }
    }

    fun bind(elem: StoryElement) {
        vpSlideShowPager?.adapter = ElementStorySlideShowAdapter(elem.storyElements(), mImageWidth, mImageHeight)
        val position = vpSlideShowPager?.currentItem

        // setCount(1, elem.storyElements().size());
        vpSlideShowPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                Log.d("Rakshith", "position -- $position")
                Log.d("rakshith", "Element size -- " + elem.storyElements().size)
                if (!isGalleryElement) {
                    if (position == 0) {
                        ivSlideLeft?.visibility = View.INVISIBLE
                    } else {
                        ivSlideLeft?.visibility = View.VISIBLE
                    }

                    if (position == elem.storyElements().size - 1) {
                        ivSlideRight?.visibility = View.INVISIBLE
                    } else {
                        ivSlideRight?.visibility = View.VISIBLE
                    }
                }
                //setCount(position + 1, elem.storyElements().size());
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        ivSlideLeft?.setOnClickListener({
            if (vpSlideShowPager?.currentItem as Int - 1 >= 0)
                vpSlideShowPager?.currentItem = vpSlideShowPager?.currentItem as Int - 1
        })
        var storyElementSize = elem.storyElements().size
        ivSlideRight?.setOnClickListener(View.OnClickListener {
            if ((position as Int) < storyElementSize)
                vpSlideShowPager?.currentItem = vpSlideShowPager?.currentItem as Int + 1
        })
    }
}