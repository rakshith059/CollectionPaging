package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class CollectionSnapshot protected constructor(parcel: Parcel) : Parcelable {
    @SerializedName("body")
    @Expose
    private var snapshotBody: String? = null

    init {
        setSnapshotBody(parcel.readString())
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(snapshotBody())
    }

    fun snapshotBody(): String? {
        return snapshotBody
    }

    fun setSnapshotBody(snapshotBody: String) {
        this.snapshotBody = snapshotBody
    }

    init {
        snapshotBody = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<CollectionSnapshot> {
        override fun createFromParcel(parcel: Parcel): CollectionSnapshot {
            return CollectionSnapshot(parcel)
        }

        override fun newArray(size: Int): Array<CollectionSnapshot?> {
            return arrayOfNulls(size)
        }
    }

}
