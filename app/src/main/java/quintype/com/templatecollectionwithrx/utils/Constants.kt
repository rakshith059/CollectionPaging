package quintype.com.templatecollectionwithrx.utils

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class Constants {
    companion object {
        //        val BASE_URL = "https://thequint-web.staging.quintype.io"
//        val BASE_URL = "https://www.thequint.com"
//        val BASE_URL = "https://madrid.quintype.io"
        val BASE_URL = "https://www.samachara.com"
        val COLLECTION_HOME: String = "home"
        val PAGE_LIMIT: Int = 20
        val TYPE_COLLECTION: String = "collection"
        val PAGE_LIMIT_CHILD: Int = 5
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
    }
}