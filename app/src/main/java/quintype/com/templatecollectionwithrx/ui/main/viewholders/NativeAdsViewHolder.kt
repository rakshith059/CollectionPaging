package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.NativeContentAd
import com.google.android.gms.ads.formats.NativeContentAdView
import quintype.com.templatecollectionwithrx.R

class NativeAdsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    companion object {
        var mContext: Context? = null
        var adFrameLayout: FrameLayout? = null

        fun create(parent: ViewGroup): NativeAdsViewHolder {
            mContext = parent.context
            val view = LayoutInflater.from(mContext).inflate(R.layout.native_ad_view_holder, parent, false)
            adFrameLayout = view.findViewById(R.id.native_ad_view_holder_container)
            return NativeAdsViewHolder(view)
        }
    }

    private fun loadNativeAds() {
//        val adLoader = AdLoader.Builder(mContext, mContext?.resources?.getString(R.string.admob_app_id))
//        val adLoader = AdLoader.Builder(mContext, "ca-app-pub-1138107191663281/4355766617")
//        val adLoader = AdLoader.Builder(mContext, "ca-app-pub-1138107191663281/2021402846")
        val adLoader = AdLoader.Builder(mContext, "ca-app-pub-3940256099942544/2247696110")
                .forContentAd { ad: NativeContentAd ->
                    val adView = LayoutInflater.from(mContext)
                            .inflate(R.layout.native_ad_view, null) as NativeContentAdView
                    // This method sets the text, images and the native ad, etc into the ad
                    // view.

                    Toast.makeText(mContext, "ads loading successful == ${ad.headline}", Toast.LENGTH_SHORT).show()

                    populateNativeContentAdView(ad, adView)
                    // Assumes you have a placeholder FrameLayout in your View layout
                    // (with id ad_frame) where the ad is to be placed.
                    adFrameLayout?.removeAllViews()
                    adFrameLayout?.addView(adView)
                }
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(errorCode: Int) {
                        Toast.makeText(mContext, "ads loading failed with error code == $errorCode", Toast.LENGTH_SHORT).show()
                    }
                })
                .build()

        adLoader.loadAd(AdRequest.Builder()/*.addTestDevice("5EC1416AA84BB5D83F58117FDD19D166")*/.build())
    }

    private fun populateNativeContentAdView(ad: NativeContentAd, adView: NativeContentAdView) {
        adView.imageView = adView.findViewById(R.id.native_ad_view_iv_icon)
        adView.headlineView = adView.findViewById(R.id.native_ad_view_tv_title)
        adView.advertiserView = adView.findViewById(R.id.native_ad_view_tv_advertiser)

        (adView.headlineView as TextView).text = ad.headline
        (adView.advertiserView as TextView).text = ad.advertiser

        (adView.imageView as ImageView).setImageDrawable(ad.images.get(0).drawable)
    }

    fun bind(listener: View.OnClickListener) {
        loadNativeAds()
    }
}

