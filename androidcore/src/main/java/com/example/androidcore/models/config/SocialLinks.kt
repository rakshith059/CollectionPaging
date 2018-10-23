package com.example.androidcore.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SocialLinks() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("facebook-url")
    @Expose
    var facebookUrl: String? = null
    @SerializedName("twitter-url")
    @Expose
    var twitterUrl: String? = null

    constructor(parcel: Parcel) : this() {
        facebookUrl = parcel.readString()
        twitterUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(facebookUrl)
        parcel.writeString(twitterUrl)
    }

    companion object CREATOR : Parcelable.Creator<SocialLinks> {
        override fun createFromParcel(parcel: Parcel): SocialLinks {
            return SocialLinks(parcel)
        }

        override fun newArray(size: Int): Array<SocialLinks?> {
            return arrayOfNulls(size)
        }
    }
}
