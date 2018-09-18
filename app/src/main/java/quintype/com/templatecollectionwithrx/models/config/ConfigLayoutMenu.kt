package quintype.com.templatecollectionwithrx.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ConfigLayoutMenu() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("updated-at")
    @Expose
    var updatedAt: Long? = null
    @SerializedName("tag-name")
    @Expose
    var tagName: Any? = null
    @SerializedName("publisher-id")
    @Expose
    var publisherId: Int? = null
    @SerializedName("menu-group-slug")
    @Expose
    var menuGroupSlug: String? = null
    @SerializedName("item-id")
    @Expose
    var itemId: Int? = null
    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("item-type")
    @Expose
    var itemType: String? = null
    @SerializedName("section-slug")
    @Expose
    var sectionSlug: String? = null
    @SerializedName("tag-slug")
    @Expose
    var tagSlug: Any? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("parent-id")
    @Expose
    var parentId: Int? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("created-at")
    @Expose
    var createdAt: Long? = null
    @SerializedName("section-name")
    @Expose
    var sectionName: String? = null
    @SerializedName("data")
    @Expose
    var data: ConfigLayoutMenuData? = null

    constructor(parcel: Parcel) : this() {
        updatedAt = parcel.readValue(Long::class.java.classLoader) as? Long
        publisherId = parcel.readValue(Int::class.java.classLoader) as? Int
        menuGroupSlug = parcel.readString()
        itemId = parcel.readValue(Int::class.java.classLoader) as? Int
        rank = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        itemType = parcel.readString()
        sectionSlug = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        parentId = parcel.readValue(Int::class.java.classLoader) as? Int
        url = parcel.readString()
        createdAt = parcel.readValue(Long::class.java.classLoader) as? Long
        sectionName = parcel.readString()
        data = parcel.readParcelable(ConfigLayoutMenuData::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(updatedAt)
        parcel.writeValue(publisherId)
        parcel.writeString(menuGroupSlug)
        parcel.writeValue(itemId)
        parcel.writeValue(rank)
        parcel.writeString(title)
        parcel.writeString(itemType)
        parcel.writeString(sectionSlug)
        parcel.writeValue(id)
        parcel.writeValue(parentId)
        parcel.writeString(url)
        parcel.writeValue(createdAt)
        parcel.writeString(sectionName)
        parcel.writeParcelable(data, flags)
    }

    companion object CREATOR : Parcelable.Creator<ConfigLayoutMenu> {
        override fun createFromParcel(parcel: Parcel): ConfigLayoutMenu {
            return ConfigLayoutMenu(parcel)
        }

        override fun newArray(size: Int): Array<ConfigLayoutMenu?> {
            return arrayOfNulls(size)
        }
    }
}
