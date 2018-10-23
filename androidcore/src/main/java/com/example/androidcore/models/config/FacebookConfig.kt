package com.example.androidcore.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FacebookConfig() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("app-id")
    @Expose
    var appId: String? = null

    constructor(parcel: Parcel) : this() {
        appId = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(appId)
    }

    companion object CREATOR : Parcelable.Creator<FacebookConfig> {
        override fun createFromParcel(parcel: Parcel): FacebookConfig {
            return FacebookConfig(parcel)
        }

        override fun newArray(size: Int): Array<FacebookConfig?> {
            return arrayOfNulls(size)
        }
    }
}
