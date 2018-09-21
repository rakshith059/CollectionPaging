package quintype.com.templatecollectionwithrx.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import quintype.com.templatecollectionwithrx.models.NavMenu

class ConfigLayout() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("stories-between-stacks")
    @Expose
    var storiesBetweenStacks: Int? = null
    @SerializedName("menu")
    @Expose
    var menu: List<NavMenu>? = null
    @SerializedName("stacks")
    @Expose
    var stacks: List<ConfigLayoutStack>? = null

    constructor(parcel: Parcel) : this() {
        storiesBetweenStacks = parcel.readValue(Int::class.java.classLoader) as? Int
        menu = parcel.createTypedArrayList(NavMenu.CREATOR)
        stacks = parcel.createTypedArrayList(ConfigLayoutStack)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(storiesBetweenStacks)
        parcel.writeTypedList(menu)
        parcel.writeTypedList(stacks)
    }

    companion object CREATOR : Parcelable.Creator<ConfigLayout> {
        override fun createFromParcel(parcel: Parcel): ConfigLayout {
            return ConfigLayout(parcel)
        }

        override fun newArray(size: Int): Array<ConfigLayout?> {
            return arrayOfNulls(size)
        }
    }
}
