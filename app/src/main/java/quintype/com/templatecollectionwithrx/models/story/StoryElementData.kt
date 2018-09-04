package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */
class StoryElementData : Parcelable {

    @SerializedName("content")
    internal var content: String
    @SerializedName("content-type")
    internal var contentType: String

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.content)
        dest.writeString(this.contentType)
    }

    protected constructor(`in`: Parcel) {
        this.content = `in`.readString()
        this.contentType = `in`.readString()
    }

    companion object CREATOR : Parcelable.Creator<StoryElementData> {
        override fun createFromParcel(source: Parcel): StoryElementData {
            return StoryElementData(source)
        }

        override fun newArray(size: Int): Array<StoryElementData?> {
            return arrayOfNulls(size)
        }
    }
}
