package quintype.com.templatecollectionwithrx.services

import io.reactivex.Flowable
import quintype.com.templatecollectionwithrx.models.BulkCollectionRequest
import quintype.com.templatecollectionwithrx.models.collection.CollectionResponse
import quintype.com.templatecollectionwithrx.utils.Constants.Companion.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8
import retrofit2.http.*

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

interface CollectionApiService {
    @Headers(CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/collections/{mCollectionName}")
    fun getCollectionApiService(@Path("mCollectionName") mCollectionName: String, @Query("limit") limit: Int, @Query("offset") offset: Int, @Query("item-type") itemType: String): Flowable<CollectionResponse>

    @Headers(CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @POST("/api/v1/bulk")
    fun getCollectionBulk(@QueryMap bulkCollectionRequest: BulkCollectionRequest): Flowable<CollectionResponse>
}