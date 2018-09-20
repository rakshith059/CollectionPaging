package quintype.com.templatecollectionwithrx.services

import io.reactivex.Flowable
import quintype.com.templatecollectionwithrx.models.config.PublisherConfig
import quintype.com.templatecollectionwithrx.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers

interface PublisherConfigServiceApi {

    @Headers(Constants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/config")
    fun getPublisherConfig(): Flowable<PublisherConfig>
}
