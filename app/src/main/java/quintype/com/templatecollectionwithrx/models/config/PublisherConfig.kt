package quintype.com.templatecollectionwithrx.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import quintype.com.templatecollectionwithrx.models.sections.Section
import quintype.com.templatecollectionwithrx.models.sections.SectionMeta


/**
 * Created TemplateCollectionWithRx by rakshith on 9/18/18.
 *
 * Contains details specific to publisher
 */
class PublisherConfig() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("stripe-publishable-key")
    @Expose
    var stripePublishableKey: Any? = null
    @SerializedName("sketches-host")
    @Expose
    var sketchesHost: String? = null
    @SerializedName("theme-attributes")
    @Expose
    var themeAttributes: ThemeAttributes? = null
    @SerializedName("facebook")
    @Expose
    var facebook: FacebookConfig? = null
    @SerializedName("sections")
    @Expose
    var sections: List<SectionMeta>? = null
    @SerializedName("social-links")
    @Expose
    var socialLinks: SocialLinks? = null
    @SerializedName("layout")
    @Expose
    var layout: ConfigLayout? = null
    @SerializedName("cdn-name")
    @Expose
    var cdnName: String? = null
    @SerializedName("publisher-id")
    @Expose
    var publisherId: Long? = null
    @SerializedName("publisher-settings")
    @Expose
    var publisherSettings: PublisherSettings? = null
    @SerializedName("num-headlines")
    @Expose
    var numHeadlines: Int? = null
    @SerializedName("publisher-name")
    @Expose
    var publisherName: String? = null
    @SerializedName("env")
    @Expose
    var env: String? = null
    @SerializedName("initial-stories-per-page")
    @Expose
    var initialStoriesPerPage: Int? = null
    @SerializedName("seo-metadata")
    @Expose
    var seoMetadata: List<SeoMetadatum>? = null
    @SerializedName("typekit-id")
    @Expose
    var typekitId: String? = null
    @SerializedName("cdn-image")
    @Expose
    var cdnImage: String? = null
    @SerializedName("story-slug-format")
    @Expose
    var storySlugFormat: String? = null
    @SerializedName("android")
    @Expose
    var android: AndroidConfig? = null
    @SerializedName("shrubbery-host")
    @Expose
    var shrubberyHost: String? = null
    @SerializedName("static-page-urls")
    @Expose
    var staticPageUrls: List<String>? = null
    @SerializedName("nudge-host")
    @Expose
    var nudgeHost: String? = null
    @SerializedName("num-more-stories")
    @Expose
    var numMoreStories: Int? = null
    @SerializedName("polltype-host")
    @Expose
    var polltypeHost: String? = null
    //    @SerializedName("apps-data")
//    @Expose
//    var appsData: AppsData? = null
    @SerializedName("razorpay-gateway-key")
    @Expose
    var razorpayGatewayKey: Any? = null
    @SerializedName("story-attributes")
    @Expose
    var storyAttributes: List<StoryAttribute>? = null
    @SerializedName("mins-between-refreshes")
    @Expose
    var minsBetweenRefreshes: Int? = null

    constructor(parcel: Parcel) : this() {
        sketchesHost = parcel.readString()
        sections = parcel.createTypedArrayList(SectionMeta)
        cdnName = parcel.readString()
        publisherId = parcel.readValue(Long::class.java.classLoader) as? Long
        numHeadlines = parcel.readValue(Int::class.java.classLoader) as? Int
        publisherName = parcel.readString()
        env = parcel.readString()
        initialStoriesPerPage = parcel.readValue(Int::class.java.classLoader) as? Int
        typekitId = parcel.readString()
        cdnImage = parcel.readString()
        storySlugFormat = parcel.readString()
        shrubberyHost = parcel.readString()
        staticPageUrls = parcel.createStringArrayList()
        nudgeHost = parcel.readString()
        numMoreStories = parcel.readValue(Int::class.java.classLoader) as? Int
        polltypeHost = parcel.readString()
        minsBetweenRefreshes = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sketchesHost)
        parcel.writeParcelable(themeAttributes, flags)
        parcel.writeParcelable(facebook, flags)
        parcel.writeTypedList(sections)
        parcel.writeParcelable(socialLinks, flags)
        parcel.writeString(cdnName)
        parcel.writeValue(publisherId)
        parcel.writeParcelable(publisherSettings, flags)
        parcel.writeValue(numHeadlines)
        parcel.writeString(publisherName)
        parcel.writeString(env)
        parcel.writeValue(initialStoriesPerPage)
        parcel.writeTypedList(seoMetadata)
        parcel.writeString(typekitId)
        parcel.writeString(cdnImage)
        parcel.writeString(storySlugFormat)
        parcel.writeParcelable(android, flags)
        parcel.writeString(shrubberyHost)
        parcel.writeStringList(staticPageUrls)
        parcel.writeString(nudgeHost)
        parcel.writeValue(numMoreStories)
        parcel.writeString(polltypeHost)
//        parcel.writeParcelable(appsData, flags)
        parcel.writeTypedList(storyAttributes)
        parcel.writeValue(minsBetweenRefreshes)
    }

    companion object CREATOR : Parcelable.Creator<PublisherConfig> {
        override fun createFromParcel(parcel: Parcel): PublisherConfig {
            return PublisherConfig(parcel)
        }

        override fun newArray(size: Int): Array<PublisherConfig?> {
            return arrayOfNulls(size)
        }
    }
}
