package quintype.com.templatecollectionwithrx.services

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import quintype.com.templatecollectionwithrx.models.config.PublisherConfig
import quintype.com.templatecollectionwithrx.ui.main.activities.MainActivity
import quintype.com.templatecollectionwithrx.ui.main.activities.SplashActivity
import quintype.com.templatecollectionwithrx.utils.Constants

class PublisherConfigService {
    companion object {
        var appServiceApi: PublisherConfigServiceApi = RetrofitApiClient.getRetrofitApiClient().create(PublisherConfigServiceApi::class.java)
        var mCompositeDisposable: CompositeDisposable? = null
        var publisherConfigService: PublisherConfigService? = null
        var publisherConfigLiveData: MutableLiveData<PublisherConfig> = MutableLiveData()

        @Synchronized
        fun getInstance(compositeDisposable: CompositeDisposable): PublisherConfigService {
            if (publisherConfigService == null)
                publisherConfigService = PublisherConfigService()

            if (compositeDisposable == null)
                mCompositeDisposable = CompositeDisposable()

            return publisherConfigService as PublisherConfigService
        }
    }

    fun getPublisherConfig(mContext: Context)/*: LiveData<PublisherConfig> */ {
        mCompositeDisposable?.add(appServiceApi.getPublisherConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ResourceSubscriber<PublisherConfig>() {
                    override fun onComplete() {
                        Log.d("Rakshith", "publisher config api completed")
                    }

                    override fun onNext(publisherConfig: PublisherConfig) {
//                        publisherConfigLiveData.value = publisherConfig

                        var cdnImageName = publisherConfig?.cdnName
                        Constants.setSharedPreferences(mContext, Constants.CDN_IMAGE_NAME, cdnImageName as String)
                        Log.d("Rakshith", "cdnImageName == $cdnImageName")

                        mContext.startActivity(Intent(mContext, MainActivity::class.java))
                        (mContext as Activity).finish()
                    }

                    override fun onError(t: Throwable?) {
                        Log.d("Rakshith", "publisher config error message $t")
                    }
                }))

//        return publisherConfigLiveData
    }
}
