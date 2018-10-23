package com.example.androidcore.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AndroidConfig() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("notification-style")
    @Expose
    var notificationStyle: String? = null

    constructor(parcel: Parcel) : this() {
        notificationStyle = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(notificationStyle)
    }

    companion object CREATOR : Parcelable.Creator<AndroidConfig> {
        override fun createFromParcel(parcel: Parcel): AndroidConfig {
            return AndroidConfig(parcel)
        }

        override fun newArray(size: Int): Array<AndroidConfig?> {
            return arrayOfNulls(size)
        }
    }
}
