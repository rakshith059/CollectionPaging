package com.example.androidcore.services

import com.example.androidcore.Constants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class RetrofitApiClient {
    companion object {
        private var retrofit: Retrofit? = null
        /**
         * interceptor and builder is used to print the log
         */
        var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        //                .setLevel(HttpLoggingInterceptor.Level.BODY)
//                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        var builder: OkHttpClient.Builder = OkHttpClient().newBuilder().addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)

        fun getRetrofitApiClient(): Retrofit {
            retrofit = Retrofit
                    .Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit as Retrofit
        }
    }
}