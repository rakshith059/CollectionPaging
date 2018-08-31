package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class CoverImageMetadata : Parcelable {
    @SerializedName("width")
    @Expose
    private val width: Int
    @SerializedName("height")
    @Expose
    private val height: Int
    @SerializedName("mime-type")
    @Expose
    private val mimeType: String

    fun width(): Int {
        return width
    }

    fun height(): Int {
        return height
    }

    fun mimeType(): String {
        return mimeType
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.width)
        dest.writeInt(this.height)
        dest.writeString(this.mimeType)
    }

    protected constructor(parcel: Parcel) {
        this.width = parcel.readInt()
        this.height = parcel.readInt()
        this.mimeType = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<CoverImageMetadata> {
        override fun createFromParcel(parcel: Parcel): CoverImageMetadata {
            return CoverImageMetadata(parcel)
        }

        override fun newArray(size: Int): Array<CoverImageMetadata?> {
            return arrayOfNulls(size)
        }
    }

}
