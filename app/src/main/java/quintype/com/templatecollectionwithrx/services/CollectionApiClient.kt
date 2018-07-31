package quintype.com.templatecollectionwithrx.services

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import quintype.com.templatecollectionwithrx.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class CollectionApiClient {
    companion object {
        private var retrofit: Retrofit? = null

        fun getCollectonApiClient(): Retrofit {
            retrofit = Retrofit
                    .Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit as Retrofit
        }
    }
}