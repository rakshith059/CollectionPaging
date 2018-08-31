package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class AssociatedMetadata() : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("layout")
    @Expose
    var associatedMetadataLayout: String? = null
    @SerializedName("show_arrows")
    @Expose
    var associatedMetadataShowArrow: Boolean = false
    @SerializedName("show_author_name")
    @Expose
    var associatedMetadataShowAuthorName: Boolean = true
    @SerializedName("slider_type_dots")
    @Expose
    var associatedMetadataSliderTypeDots: Boolean = false
    @SerializedName("show_section_tag")
    @Expose
    var associatedMetadataShowSectionTag: Boolean = false
    @SerializedName("show_time_of_publish")
    @Expose
    var associatedMetadataShowTimeToPublish: Boolean = false
    @SerializedName("show_collection_name")
    @Expose
    var associatedMetadataShowCollectionName: Boolean = true
    @SerializedName("scroll_speed_ms")
    @Expose
    var associatedMetadataScrollSpeedMs: Int = 0
    @SerializedName("number_of_stories_to_show")
    @Expose
    var associatedMetadataNumberOfStoriesToShow: Int = 0
    @SerializedName("theme")
    @Expose
    var associatedMetadataTheme: String? = null
    @SerializedName("slider_type_dashes")
    @Expose
    var associatedMetadataSliderTypeDashes: Boolean = false
    @SerializedName("enable_auto_play")
    @Expose
    var associatedMetadataEnableAutoPlay: Boolean = false

    constructor(parcel: Parcel) : this() {
        associatedMetadataLayout = parcel.readString()
        associatedMetadataShowArrow = parcel.readByte() != 0.toByte()
        associatedMetadataShowAuthorName = parcel.readByte() != 0.toByte()
        associatedMetadataSliderTypeDots = parcel.readByte() != 0.toByte()
        associatedMetadataShowSectionTag = parcel.readByte() != 0.toByte()
        associatedMetadataShowTimeToPublish = parcel.readByte() != 0.toByte()
        associatedMetadataShowCollectionName = parcel.readByte() != 0.toByte()
        associatedMetadataScrollSpeedMs = parcel.readInt()
        associatedMetadataNumberOfStoriesToShow = parcel.readInt()
        associatedMetadataTheme = parcel.readString()
        associatedMetadataSliderTypeDashes = parcel.readByte() != 0.toByte()
        associatedMetadataEnableAutoPlay = parcel.readByte() != 0.toByte()
    }

    companion object CREATOR : Parcelable.Creator<AssociatedMetadata> {
        override fun createFromParcel(parcel: Parcel): AssociatedMetadata {
            return AssociatedMetadata(parcel)
        }

        override fun newArray(size: Int): Array<AssociatedMetadata?> {
            return arrayOfNulls(size)
        }
    }
}