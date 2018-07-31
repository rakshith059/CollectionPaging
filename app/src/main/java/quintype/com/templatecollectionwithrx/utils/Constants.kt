package quintype.com.templatecollectionwithrx.utils

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class Constants {
    companion object {
        //        val BASE_URL = "https://thequint-web.staging.quintype.io"
        val BASE_URL = "https://www.thequint.com"
        val COLLECTION_HOME: String = "home"
        val PAGE_LIMIT: Int = 25
        val TYPE_COLLECTION: String = "collection"
        val PAGE_LIMIT_CHILD: Int = 5
        val TYPE_STORY: String = "story"
        val WIDGET_TEMPLATE: String = "widget"
        val BROADCAST_HASHTABLE: String? = "BROADCAST_HASHTABLE"
        val STORY_FILEDS: String? = "id,hero-image-s3-key, sections,headline,author-name, created-at,hero-image-caption,story-content-id,tags,hero-image-metadata,story-template,slug,metadata"

        const val CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8 = "Content-Type: application/json; " + "charset=utf-8"
    }
}