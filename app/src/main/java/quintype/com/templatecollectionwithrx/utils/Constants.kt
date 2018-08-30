package quintype.com.templatecollectionwithrx.utils

import android.content.Context
import android.view.Display
import android.view.WindowManager
import android.R.attr.y
import android.R.attr.x
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point


/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class Constants {
    companion object {
        fun getScreenWidth(context: Context?): Int {
            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            val width = size.x
            return width
        }

        fun getScreenHeight(context: Context?): Int {
            val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            val height = size.y
            return height
        }

        //        val BASE_URL = "https://thequint-web.staging.quintype.io"
//        val BASE_URL = "https://www.thequint.com"
        val BASE_URL = "https://madrid.quintype.io"
        //        val BASE_URL = "https://ace-web.staging.quintype.io"
        //        val BASE_URL = "https://www.samachara.com"
        val COLLECTION_HOME: String = "home"
        val PAGE_LIMIT: Int = 20
        val TYPE_COLLECTION: String = "collection"
        val PAGE_LIMIT_CHILD: Int = 5
        val DELAY_SEC: Long = 3
        val TYPE_STORY: String = "story"
        val WIDGET_TEMPLATE: String = "widget"
        val STORY_FILEDS: String? = "id,hero-image-s3-key, sections,headline,author-name, created-at,hero-image-caption,story-content-id,tags,hero-image-metadata,story-template,slug,metadata"

        const val CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; " + "charset=utf-8"


        val HALF_IMAGE_SLIDER = "HalfImageSlider"
        val FOUR_COLUMN_GRID = "FourColGrid"
        val TWO_COLUMN_GRID = "TwoColOneAd"
        val FULL_SCREEN_SIMPLE_SLIDER = "FullscreenSimpleSlider"
        val THREE_COLUMN = "ThreeCol"
        val FULL_SCREEN_LINEAR_GALLERY_SLIDER = "FullscreenLinearGallerySlider"
        val TWO_COLUMN = "TwoCol"
        val L_SHAPED_ONE_WIDGET = "LShapeOneWidget"
        val FULL_IMAGE_SLIDER = "FullImageSlider"
        val TWO_COLUMN_CAROUSEL = "TwoColCarousel"
        val TWO_COLUMN_HIGHLIGHT = "TwoColHighlight"
        val ONE_COLUMN_STORY_LIST = "OneColStoryList"


        val VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION = 1000
        val VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD = 1001
        val VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER = 1002
        val VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD = 1003
        val VIEWHOLDER_TYPE_HALF_IMAGE_SLIDER = 1004
        val VIEWHOLDER_TYPE_FULL_IMAGE_SLIDER = 1005
        val VIEWHOLDER_TYPE_HALF_SCREEN_CHILD = 1006
        val VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_UNDERLINE_SECTION = 1007
        val VIEWHOLDER_TYPE_STORY = 1008
        val VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_GRID = 1009
        val VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HORIZONTAL = 1010
        val ASSOISATED_THEME_DARK: String = "dark"
        val ASSOISATED_THEME_LIGHT: String = "light"
    }
}