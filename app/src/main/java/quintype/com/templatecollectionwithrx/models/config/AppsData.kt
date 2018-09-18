package quintype.com.templatecollectionwithrx.models.config

import android.os.Parcel
import android.os.Parcelable

class AppsData() : Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<AppsData> {
        override fun createFromParcel(parcel: Parcel): AppsData {
            return AppsData(parcel)
        }

        override fun newArray(size: Int): Array<AppsData?> {
            return arrayOfNulls(size)
        }
    }

}
