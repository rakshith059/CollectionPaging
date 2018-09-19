package quintype.com.templatecollectionwithrx.models

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import quintype.com.templatecollectionwithrx.R

class NavMenuGroup() : ParentListItem, Parcelable {
    var position: Int = 0
    var selected: Boolean = false
    public var menuItem: NavMenu? = null
    private var mSubsections = arrayListOf(NavMenu())
    public var dummyValue: String? = null

    constructor(parcel: Parcel) : this() {
        position = parcel.readInt()
        menuItem = parcel.readParcelable(NavMenu::class.java.classLoader)
        selected = !parcel.readByte().equals(0)
        mSubsections = parcel.createTypedArrayList(NavMenu.CREATOR)
        dummyValue = parcel.readString()
    }

    fun getName(context: Context): String? {
        return if (menuItem?.id().equals(NavMenu.HOME.id())) {
            context.getResources().getString(R.string.home_title)
        } else {
            menuItem?.title()
        }
    }

    override fun getChildItemList(): MutableList<NavMenu> {
        return mSubsections
    }

    override fun isInitiallyExpanded(): Boolean {
        return false
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(this.position)
        parcel.writeParcelable(this.menuItem, flags)
        parcel.writeByte(if (this.selected) 1.toByte() else 0.toByte())
        parcel.writeTypedList(this.mSubsections)
        parcel.writeString(this.dummyValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NavMenuGroup> {
        override fun createFromParcel(parcel: Parcel): NavMenuGroup {
            return NavMenuGroup(parcel)
        }

        override fun newArray(size: Int): Array<NavMenuGroup?> {
            return arrayOfNulls(size)
        }
    }
}