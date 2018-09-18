package quintype.com.templatecollectionwithrx.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ConfigLayoutMenuData() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("color")
    @Expose
    var color: String? = null

    constructor(parcel: Parcel) : this() {
        color = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(color)
    }

    companion object CREATOR : Parcelable.Creator<ConfigLayoutMenuData> {
        override fun createFromParcel(parcel: Parcel): ConfigLayoutMenuData {
            return ConfigLayoutMenuData(parcel)
        }

        override fun newArray(size: Int): Array<ConfigLayoutMenuData?> {
            return arrayOfNulls(size)
        }
    }
}
