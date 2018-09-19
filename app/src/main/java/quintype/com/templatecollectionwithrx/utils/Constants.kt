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

        //        const val BASE_URL = "https://thequint-web.staging.quintype.io"
//        const val BASE_URL = "https://www.thequint.com"
        const val BASE_URL = "https://madrid.quintype.io"
        //        const val BASE_URL = "https://ace-web.staging.quintype.io"
        //        const val BASE_URL = "https://www.samachara.com"
        const val COLLECTION_HOME: String = "home"
        const val PAGE_LIMIT: Int = 20
        const val TYPE_COLLECTION: String = "collection"
        const val PAGE_LIMIT_CHILD: Int = 5
        const val PRE_FETCH_ITEM: Int = 5
        const val DELAY_SEC: Long = 3
        const val TYPE_STORY: String = "story"
        const val WIDGET_TEMPLATE: String = "widget"
        const val STORY_FIELDS: String = "id,hero-image-s3-key,sections,headline,authors,created-at,hero-image-caption,story-content-id,alternative,hero-image-metadata,slug,last-published-at,published-at,first-published-at"

        /**
         * Constants for query params
         */
        const val CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; " + "charset=utf-8"
        const val COLLECTION_SLUG = "collections-slug"
        const val QUERY_PARAM_KEY_LIMIT = "limit"
        const val QUERY_PARAM_KEY_OFFSET = "offset"
        const val QUERY_PARAM_KEY_FIELDS = "story-fields"
        const val QUERY_PARAM_KEY_ITEM_TYPE = "item-type"
        const val QUERY_PARAM_KEY_STORY_SLUG = "slug"

        var ACCEPT_APPLICATION_JSON_CHARSET_UTF_8 = "Accept: application/json; charset=utf-8"
        const val HALF_IMAGE_SLIDER = "HalfImageSlider"
        const val FOUR_COLUMN_GRID = "FourColGrid"
        const val TWO_COLUMN_GRID = "TwoColOneAd"
        const val FULL_SCREEN_SIMPLE_SLIDER = "FullscreenSimpleSlider"
        const val THREE_COLUMN = "ThreeCol"
        const val FULL_SCREEN_LINEAR_GALLERY_SLIDER = "FullscreenLinearGallerySlider"
        const val TWO_COLUMN = "TwoCol"
        const val L_SHAPED_ONE_WIDGET = "LShapeOneWidget"
        const val FULL_IMAGE_SLIDER = "FullImageSlider"
        const val TWO_COLUMN_CAROUSEL = "TwoColCarousel"
        const val TWO_COLUMN_HIGHLIGHT = "TwoColHighlight"
        const val ONE_COLUMN_STORY_LIST = "OneColStoryList"

        const val VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION = 1000
        const val VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD = 1001
        const val VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER = 1002
        const val VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD = 1003
        const val VIEWHOLDER_TYPE_HALF_IMAGE_SLIDER = 1004
        const val VIEWHOLDER_TYPE_FULL_IMAGE_SLIDER = 1005
        const val VIEWHOLDER_TYPE_HALF_SCREEN_CHILD = 1006
        const val VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_UNDERLINE_SECTION = 1007
        const val VIEWHOLDER_TYPE_STORY = 1008
        const val VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_GRID = 1009
        const val VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HORIZONTAL = 1010
        const val VIEWHOLDER_TYPE_FULL_SCREEN_SIMPLE_SLIDER = 1011
        const val TYPE_OUTER_COLLECTION = 1012
        const val TYPE_OUTER_STORY = 1013

        const val ASSOISATED_THEME_DARK: String = "dark"
        const val ASSOISATED_THEME_LIGHT: String = "light"

        /**
         * for storyElementSubTypeMetadata
         */
        const val TYPE_SLIDESHOW = "slideshow"
        const val TYPE_GALLERY = "gallery"
        const val TYPE_INVALID = "invalid"

        const val NAVMENU_GROUP_PARENT_POSITION = -1
        const val NAVMENU_GROUP_NOTIFICATIONS_POSITION = "-2"
        const val NAVMENU_GROUP_DISCLAIMER_POSITION = "-3"
    }
}