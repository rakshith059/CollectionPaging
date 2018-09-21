package quintype.com.templatecollectionwithrx.ui.main.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.config.PublisherConfig
import quintype.com.templatecollectionwithrx.services.PublisherConfigServiceApi
import quintype.com.templatecollectionwithrx.services.RetrofitApiClient
import quintype.com.templatecollectionwithrx.utils.Constants
import com.google.gson.Gson


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getPublisherConfig()
    }

    private fun getPublisherConfig() {
        val mCompositeDisposable = CompositeDisposable()
        var appServiceApi: PublisherConfigServiceApi = RetrofitApiClient.getRetrofitApiClient().create(PublisherConfigServiceApi::class.java)

        mCompositeDisposable.add(appServiceApi.getPublisherConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<PublisherConfig>() {
                    override fun onComplete() {
                        Log.d("Rakshith", "publisher config onCompleted")
                    }

                    override fun onNext(publisherConfig: PublisherConfig) {
                        val cdnImageName = publisherConfig.cdnName
                        val publisherCopyRight = publisherConfig.publisherSettings?.copyright
                        val publisherName = publisherConfig.publisherName
                        val shrubberyHost = publisherConfig.shrubberyHost
                        val pollTypeHost = publisherConfig.polltypeHost
                        val staticPageUrls = publisherConfig.staticPageUrls


                        if (staticPageUrls != null) {
                            staticPageUrls.forEach { staticPageUrl ->
                                if (staticPageUrl.contains(Constants.TERMS_AND_CONDITION))
                                    Constants.setSharedPreferences(this@SplashActivity, Constants.SP_TERMS_AND_CONDITION, staticPageUrl)
                                else if (staticPageUrl.contains(Constants.PRIVACY_POLICY))
                                    Constants.setSharedPreferences(this@SplashActivity, Constants.SP_PRIVACY_POLICY, staticPageUrl)
                                else if (staticPageUrl.contains(Constants.ABOUT_US))
                                    Constants.setSharedPreferences(this@SplashActivity, Constants.SP_ABOUT_US, staticPageUrl)
                            }
                        }

                        Constants.setSharedPreferences(this@SplashActivity, Constants.SP_CDN_IMAGE_NAME, cdnImageName as String)
                        Constants.setSharedPreferences(this@SplashActivity, Constants.SP_PUBLISHER_COPYRIGHT, publisherCopyRight as String)
                        Constants.setSharedPreferences(this@SplashActivity, Constants.SP_PUBLISHER_NAME, publisherName as String)
                        Constants.setSharedPreferences(this@SplashActivity, Constants.SP_SHRUBBERY_HOST, shrubberyHost as String)
                        Constants.setSharedPreferences(this@SplashActivity, Constants.SP_POLLTYPE_HOST, pollTypeHost as String)

                        if (publisherConfig.layout != null) {
                            val layoutAsString = Gson().toJson(publisherConfig.layout)
                            Constants.setSharedPreferences(this@SplashActivity, Constants.SP_LAYOUT, layoutAsString as String)
                        }

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
}
