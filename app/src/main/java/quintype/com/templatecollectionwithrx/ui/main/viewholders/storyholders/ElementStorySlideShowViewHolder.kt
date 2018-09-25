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
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Utilities


class ElementStorySlideShowViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    private var vpSlideShowPager: ViewPager? = null

    private var ivSlideLeft: ImageView? = null
    private var ivSlideRight: ImageView? = null

    private var mImageWidth: Int = 0

    companion object {
        fun create(parent: ViewGroup): ElementStorySlideShowViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_slide_show_view_holder, parent, false)
            val elementStorySlideShowViewHolder = ElementStorySlideShowViewHolder(view)
            val dimen = (parent.width * 0.75).toInt()

            elementStorySlideShowViewHolder.vpSlideShowPager = view.findViewById(R.id.story_slide_show_view_holder_view_pager)
            elementStorySlideShowViewHolder.mImageWidth = parent.width
            elementStorySlideShowViewHolder.vpSlideShowPager?.layoutParams = Utilities.createLayoutParams(elementStorySlideShowViewHolder.vpSlideShowPager as ViewPager, parent.width, dimen)
            elementStorySlideShowViewHolder.ivSlideLeft = view.findViewById(R.id.story_slide_show_view_holder_iv_left_arrow)
            elementStorySlideShowViewHolder.ivSlideRight = view.findViewById(R.id.story_slide_show_view_holder_iv_right_arrow)

            return elementStorySlideShowViewHolder
        }
    }

    fun bind(elem: StoryElement) {
        vpSlideShowPager?.adapter = ElementStorySlideShowAdapter(elem.storyElements(), mImageWidth, vpSlideShowPager?.height)
        val position = vpSlideShowPager?.currentItem

        // setCount(1, elem.storyElements().size());
        vpSlideShowPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                Log.d("Rakshith", "position -- $position")
                Log.d("rakshith", "Element size -- " + elem.storyElements().size)
                if (position == 0) {
                    ivSlideLeft?.setVisibility(View.INVISIBLE)
                } else {
                    ivSlideLeft?.setVisibility(View.VISIBLE)
                }

                if (position == elem.storyElements().size - 1) {
                    ivSlideRight?.setVisibility(View.INVISIBLE)
                } else {
                    ivSlideRight?.setVisibility(View.VISIBLE)
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