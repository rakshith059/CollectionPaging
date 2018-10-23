package com.example.androidcore.models

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

/**
 * Created by Madhu on 22/07/15.
 */
class NavMenuData : Parcelable {
    @SerializedName("color")
    private var colorHex: String = ""
    @SerializedName("link")
    private var url: String = ""

    fun colorHex(): String {
        return colorHex
    }

    fun url(): String {
        return url
    }

    override fun toString(): String {
        return "NavMenuData{" +
                "colorHex='" + colorHex + '\''.toString() +
                ", url='" + url + '\''.toString() +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.colorHex)
        dest.writeString(this.url)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.colorHex = `in`.readString()
        this.url = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<NavMenuData> = object : Parcelable.Creator<NavMenuData> {
            override fun createFromParcel(source: Parcel): NavMenuData {
                return NavMenuData(source)
            }

            override fun newArray(size: Int): Array<NavMenuData?> {
                return arrayOfNulls(size)
            }
        }
    }
}
