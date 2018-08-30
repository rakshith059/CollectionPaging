package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import me.relex.circleindicator.CircleIndicator
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.PagerFullCarouselAdapter
import quintype.com.templatecollectionwithrx.adapters.PagerHalfCarouselAdapter
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.CollectionItem
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.widgets.PagerScheduleProxy


class TitleImageSliderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    fun bind(collectionList: List<CollectionItem>?, collectionAssociatedMetadata: AssociatedMetadata?) {
        /**
         * Checking for indicator type
         */
        if (collectionAssociatedMetadata?.associatedMetadataSliderTypeDashes as Boolean)
            circleIndicator?.configureIndicator(INDICATOR_WIDTH, INDICATOR_HEIGHT, INDICATOR_MARGIN, R.animator.scale_with_alpha, 0, R.drawable.ic_dash_primary, R.drawable.ic_dash_secondary)
        else if (collectionAssociatedMetadata.associatedMetadataSliderTypeDots)
            circleIndicator?.configureIndicator(INDICATOR_WIDTH, INDICATOR_HEIGHT, INDICATOR_MARGIN, R.animator.scale_with_alpha, 0, R.drawable.ic_dot_primary, R.drawable.ic_dot_black)
        else {
            circleIndicator?.configureIndicator(INDICATOR_WIDTH, INDICATOR_HEIGHT, INDICATOR_MARGIN, R.animator.scale_with_alpha, 0, R.drawable.ic_dot_primary, R.drawable.ic_dot_black)
            /**
             * if both dots and dash indicators are false then hide the indicator
             */
//            circleIndicator?.visibility = View.GONE
        }

        var associatedMetadataLayout = collectionAssociatedMetadata.associatedMetadataLayout
        when (associatedMetadataLayout) {
            Constants.FULL_IMAGE_SLIDER ->
                mSlideShowPager?.adapter = PagerFullCarouselAdapter(collectionAssociatedMetadata, collectionList)
            Constants.HALF_IMAGE_SLIDER ->
                mSlideShowPager?.adapter = PagerHalfCarouselAdapter(collectionAssociatedMetadata, collectionList)
        }

        /**
         * Checking the response for auto-play the carousel
         */
        if (collectionAssociatedMetadata.associatedMetadataEnableAutoPlay) {
            pagerScheduleProxy = PagerScheduleProxy(mSlideShowPager as ViewPager, Constants.DELAY_SEC)
            pagerScheduleProxy?.onStart()
        } else {
            /**
             * Condition to show arrow
             */
            if (collectionAssociatedMetadata.associatedMetadataShowArrow) {
                ivLeftArrow?.visibility = View.VISIBLE
                ivRightArrow?.visibility = View.VISIBLE
            } else {
                ivLeftArrow?.visibility = View.GONE
                ivRightArrow?.visibility = View.GONE
            }
        }

        circleIndicator?.setViewPager(mSlideShowPager)
        circleIndicator?.setOnPageChangeListener(pagerScheduleProxy?.onPageChangeListener)

        currentPosition = mSlideShowPager?.currentItem as Int
        slideshowContainer?.setTag(R.string.clicked_position, currentPosition)

        var collectionListSize = collectionList?.size as Int - 1

        ivLeftArrow?.setOnClickListener(this)
        ivLeftArrow?.setTag(R.string.collection_list_size, collectionListSize)
        ivRightArrow?.setOnClickListener(this)
        ivRightArrow?.setTag(R.string.collection_list_size, collectionListSize)
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

        var ivLeftArrow: ImageView? = null
        var ivRightArrow: ImageView? = null

        var currentPosition = 0
        fun create(parent: ViewGroup): TitleImageSliderViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_inside_image_slider_row, parent, false)

            mSlideShowPager = view.findViewById<ViewPager>(R.id.title_inside_image_slider_vp_pager)
            slideshowContainer = view.findViewById<ConstraintLayout>(R.id.title_inside_image_slider_cl_slideshow_container)
            mImageWidth = view.width
            circleIndicator = view.findViewById<CircleIndicator>(R.id.title_inside_image_slider_circle_indicator)

            ivLeftArrow = view?.findViewById<ImageView>(R.id.pager_carousel_title_row_iv_left_arrow)
            ivRightArrow = view?.findViewById<ImageView>(R.id.pager_carousel_title_row_iv_right_arrow)

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

            return TitleImageSliderViewHolder(view)
        }
    }

    override fun onClick(v: View?) {
        var collectionListSize = v?.getTag(R.string.collection_list_size) as Int
        when (v.id) {
            R.id.pager_carousel_title_row_iv_left_arrow -> {
                if (currentPosition > 0) {
                    currentPosition -= 1
                    mSlideShowPager?.setCurrentItem(currentPosition, true)
                }
            }
            R.id.pager_carousel_title_row_iv_right_arrow -> {
                if (currentPosition < collectionListSize) {
                    currentPosition += 1
                    mSlideShowPager?.setCurrentItem(currentPosition, true)
                }
            }
        }
    }
}
