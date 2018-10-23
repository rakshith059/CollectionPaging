package com.example.androidcore.services

import io.reactivex.Flowable
import com.example.androidcore.models.config.PublisherConfig
import com.example.androidcore.Constants
import retrofit2.http.GET
import retrofit2.http.Headers

interface PublisherConfigServiceApi {

    @Headers(Constants.CONTENT_TYPE_APPLICATION_JSON_CHARSET_UTF_8)
    @GET("/api/v1/config")
    fun getPublisherConfig(): Flowable<PublisherConfig>
}
