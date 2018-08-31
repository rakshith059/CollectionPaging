package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class CoverImage : Parcelable {

    @SerializedName("cover-image-url")
    @Expose
    private val coverImageUrl: String
    @SerializedName("cover-image-metadata")
    @Expose
    private val coverImageMetadata: CoverImageMetadata
    @SerializedName("caption")
    @Expose
    private val caption: String
    @SerializedName("cover-image-s3-key")
    @Expose
    private val coverImageS3Key: String

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.coverImageUrl)
        dest.writeParcelable(this.coverImageMetadata, flags)
        dest.writeString(this.caption)
        dest.writeString(this.coverImageS3Key)
    }

    protected constructor(parcel: Parcel) {
        this.coverImageUrl = parcel.readString()
        this.coverImageMetadata = parcel.readParcelable(CoverImageMetadata::class.java.classLoader)
        this.caption = parcel.readString()
        this.coverImageS3Key = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<CoverImage> {
        override fun createFromParcel(parcel: Parcel): CoverImage {
            return CoverImage(parcel)
        }

        override fun newArray(size: Int): Array<CoverImage?> {
            return arrayOfNulls(size)
        }
    }
}
