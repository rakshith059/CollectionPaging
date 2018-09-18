package quintype.com.templatecollectionwithrx.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class StoryAttribute() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("display-name")
    @Expose
    var displayName: String? = null
    @SerializedName("values")
    @Expose
    var values: List<String>? = null
    @SerializedName("data-type")
    @Expose
    var dataType: String? = null
    @SerializedName("attribute-level")
    @Expose
    var attributeLevel: String? = null

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()
        name = parcel.readString()
        displayName = parcel.readString()
        values = parcel.createStringArrayList()
        dataType = parcel.readString()
        attributeLevel = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(name)
        parcel.writeString(displayName)
        parcel.writeStringList(values)
        parcel.writeString(dataType)
        parcel.writeString(attributeLevel)
    }

    companion object CREATOR : Parcelable.Creator<StoryAttribute> {
        override fun createFromParcel(parcel: Parcel): StoryAttribute {
            return StoryAttribute(parcel)
        }

        override fun newArray(size: Int): Array<StoryAttribute?> {
            return arrayOfNulls(size)
        }
    }
}