package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class HeroImage : Parcelable {

    /**
     *
     * @return
     * The heroImageUrl
     */
    /**
     *
     * @param heroImageUrl
     * The hero-image-url
     */
    @SerializedName("hero-image-url")
    @Expose
    var heroImageUrl: String? = null
    /**
     *
     * @return
     * The heroImageMetadata
     */
    /**
     *
     * @param heroImageMetadata
     * The hero-image-metadata
     */
    @SerializedName("hero-image-metadata")
    @Expose
    var heroImageMetadata: HeroImageMetadata? = null
    /**
     *
     * @return
     * The heroImageCaption
     */
    /**
     *
     * @param heroImageCaption
     * The hero-image-caption
     */
    @SerializedName("hero-image-caption")
    @Expose
    var heroImageCaption: String? = null
    /**
     *
     * @return
     * The heroImageS3Key
     */
    /**
     *
     * @param heroImageS3Key
     * The hero-image-s3-key
     */
    @SerializedName("hero-image-s3-key")
    @Expose
    var heroImageS3Key: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.heroImageUrl)
        dest.writeParcelable(this.heroImageMetadata, flags)
        dest.writeString(this.heroImageCaption)
        dest.writeString(this.heroImageS3Key)
    }

    protected constructor(parcel: Parcel) {
        this.heroImageUrl = parcel.readString()
        this.heroImageMetadata = parcel.readParcelable(HeroImageMetadata::class.java.classLoader)
        this.heroImageCaption = parcel.readString()
        this.heroImageS3Key = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<HeroImage> {
        override fun createFromParcel(parcel: Parcel): HeroImage {
            return HeroImage(parcel)
        }

        override fun newArray(size: Int): Array<HeroImage?> {
            return arrayOfNulls(size)
        }
    }
}