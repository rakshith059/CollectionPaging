package quintype.com.templatecollectionwithrx.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ConfigLayoutStack() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("show-on-locations")
    @Expose
    var showOnLocations: List<String>? = null
    @SerializedName("background-color")
    @Expose
    var backgroundColor: String? = null
    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("story-group")
    @Expose
    var storyGroup: String? = null
    @SerializedName("deleted-at")
    @Expose
    var deletedAt: Any? = null
    @SerializedName("max-stories")
    @Expose
    var maxStories: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("show-on-all-sections?")
    @Expose
    var showOnAllSections: Boolean? = null
    @SerializedName("heading")
    @Expose
    var heading: String? = null

    constructor(parcel: Parcel) : this() {
        showOnLocations = parcel.createStringArrayList()
        backgroundColor = parcel.readString()
        rank = parcel.readValue(Int::class.java.classLoader) as? Int
        type = parcel.readString()
        storyGroup = parcel.readString()
        maxStories = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        showOnAllSections = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        heading = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(showOnLocations)
        parcel.writeString(backgroundColor)
        parcel.writeValue(rank)
        parcel.writeString(type)
        parcel.writeString(storyGroup)
        parcel.writeString(maxStories)
        parcel.writeValue(id)
        parcel.writeValue(showOnAllSections)
        parcel.writeString(heading)
    }

    companion object CREATOR : Parcelable.Creator<ConfigLayoutStack> {
        override fun createFromParcel(parcel: Parcel): ConfigLayoutStack {
            return ConfigLayoutStack(parcel)
        }

        override fun newArray(size: Int): Array<ConfigLayoutStack?> {
            return arrayOfNulls(size)
        }
    }
}
