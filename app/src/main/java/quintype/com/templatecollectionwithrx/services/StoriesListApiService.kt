package quintype.com.templatecollectionwithrx.services

import io.reactivex.Flowable
import quintype.com.templatecollectionwithrx.models.TagListResponse
import quintype.com.templatecollectionwithrx.models.search.SearchStoryList
import quintype.com.templatecollectionwithrx.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created TemplateCollectionWithRx by rakshith on 10/1/18.
 */

interface StoriesListApiService {

    @Headers(Constants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("api/v1/stories")
    fun getTagStoriesList(@Query(Constants.QUERY_PARAM_KEY_TAG_NAME) tagName: String,
                          @Query(Constants.QUERY_PARAM_KEY_LIMIT) limit: Int,
                          @Query(Constants.QUERY_PARAM_KEY_OFFSET) offset: Int): Flowable<TagListResponse>

    @Headers(Constants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("api/v1/search")
    fun getSearchStoriesList(@Query(Constants.QUERY_PARAM_KEY_SEARCH_TERM) tagName: String,
                             @Query(Constants.QUERY_PARAM_KEY_LIMIT) limit: Int,
                             @Query(Constants.QUERY_PARAM_KEY_OFFSET) offset: Int): Flowable<SearchStoryList>

}