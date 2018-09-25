package quintype.com.templatecollectionwithrx.models

import android.os.Parcel
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import android.os.Parcelable

class StoryElementList : Parcelable {
    var mItems: List<StoryElement> = ArrayList()
    var mSelectedItem = 0

    constructor() {}

    protected constructor(`in`: android.os.Parcel) {
        this.mItems = `in`.createTypedArrayList(StoryElement.CREATOR)
        this.mSelectedItem = `in`.readInt()
    }

    override fun toString(): String {
        return "PhotoList{" +
                "mItems=" + mItems +
                ", mSelectedItem=" + mSelectedItem +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: android.os.Parcel, flags: Int) {
        dest.writeTypedList(mItems)
        dest.writeInt(this.mSelectedItem)
    }

    companion object CREATOR : Parcelable.Creator<StoryElementList> {
        override fun createFromParcel(parcel: Parcel): StoryElementList {
            return StoryElementList(parcel)
        }

        override fun newArray(size: Int): Array<StoryElementList?> {
            return arrayOfNulls(size)
        }
    }
}