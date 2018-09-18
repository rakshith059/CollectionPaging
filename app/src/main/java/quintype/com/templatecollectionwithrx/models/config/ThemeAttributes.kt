package quintype.com.templatecollectionwithrx.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ThemeAttributes() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("secondary_color")
    @Expose
    var secondaryColor: String? = null
    @SerializedName("amp_enabled")
    @Expose
    var ampEnabled: Boolean? = null
    @SerializedName("logo")
    @Expose
    var logo: String? = null
    @SerializedName("header_text_color")
    @Expose
    var headerTextColor: String? = null
    @SerializedName("header_background_color")
    @Expose
    var headerBackgroundColor: String? = null
    @SerializedName("footer_background_color")
    @Expose
    var footerBackgroundColor: String? = null
    @SerializedName("header_icon_color")
    @Expose
    var headerIconColor: String? = null
    @SerializedName("lang")
    @Expose
    var lang: String? = null
    @SerializedName("monogram")
    @Expose
    var monogram: String? = null
    @SerializedName("cache-burst")
    @Expose
    var cacheBurst: Long? = null
    @SerializedName("footer_text_color")
    @Expose
    var footerTextColor: String? = null
    @SerializedName("header_theme")
    @Expose
    var headerTheme: String? = null
    @SerializedName("google-site-verification")
    @Expose
    var googleSiteVerification: String? = null
    @SerializedName("structured_data_news_article")
    @Expose
    var structuredDataNewsArticle: String? = null
    @SerializedName("menu_count")
    @Expose
    var menuCount: String? = null
    @SerializedName("manifest_logo")
    @Expose
    var manifestLogo: String? = null
    @SerializedName("primary_color")
    @Expose
    var primaryColor: String? = null

    constructor(parcel: Parcel) : this() {
        secondaryColor = parcel.readString()
        ampEnabled = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        logo = parcel.readString()
        headerTextColor = parcel.readString()
        headerBackgroundColor = parcel.readString()
        footerBackgroundColor = parcel.readString()
        headerIconColor = parcel.readString()
        lang = parcel.readString()
        monogram = parcel.readString()
        cacheBurst = parcel.readValue(Long::class.java.classLoader) as? Long
        footerTextColor = parcel.readString()
        headerTheme = parcel.readString()
        googleSiteVerification = parcel.readString()
        structuredDataNewsArticle = parcel.readString()
        menuCount = parcel.readString()
        manifestLogo = parcel.readString()
        primaryColor = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(secondaryColor)
        parcel.writeValue(ampEnabled)
        parcel.writeString(logo)
        parcel.writeString(headerTextColor)
        parcel.writeString(headerBackgroundColor)
        parcel.writeString(footerBackgroundColor)
        parcel.writeString(headerIconColor)
        parcel.writeString(lang)
        parcel.writeString(monogram)
        parcel.writeValue(cacheBurst)
        parcel.writeString(footerTextColor)
        parcel.writeString(headerTheme)
        parcel.writeString(googleSiteVerification)
        parcel.writeString(structuredDataNewsArticle)
        parcel.writeString(menuCount)
        parcel.writeString(manifestLogo)
        parcel.writeString(primaryColor)
    }

    companion object CREATOR : Parcelable.Creator<ThemeAttributes> {
        override fun createFromParcel(parcel: Parcel): ThemeAttributes {
            return ThemeAttributes(parcel)
        }

        override fun newArray(size: Int): Array<ThemeAttributes?> {
            return arrayOfNulls(size)
        }
    }
}