package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class ShrubberyUserEngagement() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("views")
    @Expose
    var views: Int? = null

    constructor(parcel: Parcel) : this() {
        views = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(views)
    }

    companion object CREATOR : Parcelable.Creator<ShrubberyUserEngagement> {
        override fun createFromParcel(parcel: Parcel): ShrubberyUserEngagement {
            return ShrubberyUserEngagement(parcel)
        }

        override fun newArray(size: Int): Array<ShrubberyUserEngagement?> {
            return arrayOfNulls(size)
        }
    }

}