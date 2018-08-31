package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item_() : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("headline")
    @Expose
    var headline: List<String>? = null

    constructor(parcel: Parcel) : this() {
        headline = parcel.createStringArrayList()
    }

    companion object CREATOR : Parcelable.Creator<Item_> {
        override fun createFromParcel(parcel: Parcel): Item_ {
            return Item_(parcel)
        }

        override fun newArray(size: Int): Array<Item_?> {
            return arrayOfNulls(size)
        }
    }

}