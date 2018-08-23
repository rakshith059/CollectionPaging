package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.relex.circleindicator.CircleIndicator
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.PagerCarouselAdapter
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.CollectionItem
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.widgets.PagerScheduleProxy


class TitleInsideImageSliderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(collectionList: List<CollectionItem>?, collectionAssociatedMetadata: AssociatedMetadata?) {
        mSlideShowPager?.setAdapter(PagerCarouselAdapter(collectionAssociatedMetadata, collectionList))
        pagerScheduleProxy = PagerScheduleProxy(mSlideShowPager as ViewPager, Constants.DELAY_SEC)
        pagerScheduleProxy?.onStart()

        circleIndicator?.setViewPager(mSlideShowPager)
        circleIndicator?.setOnPageChangeListener(pagerScheduleProxy?.onPageChangeListener)

        var position = mSlideShowPager?.currentItem as Int
        slideshowContainer?.setTag(R.string.clicked_position, position)
    }

    companion object {
        private val INDICATOR_WIDTH = 36
        private val INDICATOR_HEIGHT = 36
        private val INDICATOR_MARGIN = 12

        var mSlideShowPager: ViewPager? = null
        var slideshowContainer: ConstraintLayout? = null
        var circleIndicator: CircleIndicator? = null
        var mImageWidth: Int = 0
        var pagerScheduleProxy: PagerScheduleProxy? = null
        var attachStateChangeListener: View.OnAttachStateChangeListener? = null

        fun create(parent: ViewGroup): TitleInsideImageSliderViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_inside_image_slider_row, parent, false)

            mSlideShowPager = view.findViewById<ViewPager>(R.id.title_inside_image_slider_vp_pager)
            slideshowContainer = view.findViewById<ConstraintLayout>(R.id.title_inside_image_slider_cl_slideshow_container)
            mImageWidth = view.getWidth()
            circleIndicator = view.findViewById<CircleIndicator>(R.id.title_inside_image_slider_circle_indicator)

            circleIndicator?.configureIndicator(INDICATOR_WIDTH, INDICATOR_HEIGHT, INDICATOR_MARGIN, R.animator.scale_with_alpha, 0, R.drawable.ic_dot_yellow, R.drawable.ic_dot_black)
            attachStateChangeListener = object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) {
                }

                override fun onViewDetachedFromWindow(v: View) {
                    if (pagerScheduleProxy != null) {
                        pagerScheduleProxy?.onStop()
                        pagerScheduleProxy = null
                    }
                }
            }

            slideshowContainer?.addOnAttachStateChangeListener(attachStateChangeListener)

            return TitleInsideImageSliderViewHolder(view)
        }

    }

}
