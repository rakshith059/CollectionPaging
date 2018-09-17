package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class LiveUserEngagement() : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("facebook")
    @Expose
    var facebook: FacebookUserEngagement? = null
        private set
    @SerializedName("shrubbery")
    @Expose
    var shrubbery: ShrubberyUserEngagement? = null
        private set

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    companion object CREATOR : Parcelable.Creator<LiveUserEngagement> {
        override fun createFromParcel(parcel: Parcel): LiveUserEngagement {
            return LiveUserEngagement(parcel)
        }

        override fun newArray(size: Int): Array<LiveUserEngagement?> {
            return arrayOfNulls(size)
        }
    }

}