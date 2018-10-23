package com.example.androidcore.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class Tag : Parcelable {

    @SerializedName("name")
    public var name: String? = null
    @SerializedName("id")
    private var id: String = ""

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.id)
    }

    protected constructor(parcel: Parcel) {
        this.name = parcel.readString()
        this.id = parcel.readString()
    }

    constructor(tagName: String) {
        this.name = tagName
    }

    companion object CREATOR : Parcelable.Creator<Tag> {
        override fun createFromParcel(source: Parcel): Tag {
            return Tag(source)
        }

        override fun newArray(size: Int): Array<Tag?> {
            return arrayOfNulls(size)
        }
    }
}