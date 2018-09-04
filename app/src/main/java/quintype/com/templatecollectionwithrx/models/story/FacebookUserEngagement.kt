package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class FacebookUserEngagement() : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("engagement")
    @Expose
    var engagement: Int? = null
    @SerializedName("shares")
    @Expose
    var shares: Int? = null
    @SerializedName("comments")
    @Expose
    var comments: Int? = null
    @SerializedName("reactions")
    @Expose
    var reactions: Int? = null

    constructor(parcel: Parcel) : this() {
        engagement = parcel.readValue(Int::class.java.classLoader) as? Int
        shares = parcel.readValue(Int::class.java.classLoader) as? Int
        comments = parcel.readValue(Int::class.java.classLoader) as? Int
        reactions = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(engagement)
        parcel.writeValue(shares)
        parcel.writeValue(comments)
        parcel.writeValue(reactions)
    }

    companion object CREATOR : Parcelable.Creator<FacebookUserEngagement> {
        override fun createFromParcel(parcel: Parcel): FacebookUserEngagement {
            return FacebookUserEngagement(parcel)
        }

        override fun newArray(size: Int): Array<FacebookUserEngagement?> {
            return arrayOfNulls(size)
        }
    }
}