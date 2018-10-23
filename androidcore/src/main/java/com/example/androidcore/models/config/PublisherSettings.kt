package com.example.androidcore.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PublisherSettings() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        copyright = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(copyright)
    }

    companion object CREATOR : Parcelable.Creator<PublisherSettings> {
        override fun createFromParcel(parcel: Parcel): PublisherSettings {
            return PublisherSettings(parcel)
        }

        override fun newArray(size: Int): Array<PublisherSettings?> {
            return arrayOfNulls(size)
        }
    }
}
