package quintype.com.templatecollectionwithrx.ui.main.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import io.reactivex.subscribers.ResourceSubscriber
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.config.PublisherConfig
import quintype.com.templatecollectionwithrx.services.PublisherConfigServiceApi
import quintype.com.templatecollectionwithrx.services.RetrofitApiClient
import quintype.com.templatecollectionwithrx.utils.Constants

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val mCompositeDisposable = CompositeDisposable()
        var appServiceApi: PublisherConfigServiceApi = RetrofitApiClient.getRetrofitApiClient().create(PublisherConfigServiceApi::class.java)

//        var publisherConfig = PublisherConfigService.getInstance(compositeDisposable).getPublisherConfig(this@SplashActivity)
//        ObservePublisherConfig(publisherConfig)

        mCompositeDisposable.add(appServiceApi.getPublisherConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<PublisherConfig>() {
                    override fun onComplete() {
                        Log.d("Rakshith", "publisher config onCompleted")
                    }

                    override fun onNext(publisherConfig: PublisherConfig?) {
                        var cdnImageName = publisherConfig?.cdnName
                        Constants.setSharedPreferences(this@SplashActivity, Constants.CDN_IMAGE_NAME, cdnImageName as String)

                        var handler = Handler()
                        handler.postDelayed(Runnable {
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                            finish()
                        }, Constants.SPLASH_SCREEN_DELAY_MILLI_SEC)
                    }

                    override fun onError(t: Throwable?) {
                        Log.d("Rakshith", "publisher config error message $t")
                    }
                }))
    }

//    private fun ObservePublisherConfig(publisherConfig: LiveData<PublisherConfig>) {
//        publisherConfig.observe(this, Observer<PublisherConfig> { publisherConfig ->
//
//        })
//    }
}
