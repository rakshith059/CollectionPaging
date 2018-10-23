package com.example.androidcore.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class HeroImageMetadata : Parcelable {

    /**
     * @return The width
     */
    /**
     * @param width The width
     */
    @SerializedName("width")
    @Expose
    var width: Int = 0
    /**
     * @return The height
     */
    /**
     * @param height The height
     */
    @SerializedName("height")
    @Expose
    var height: Int = 0
    /**
     * @return The mimeType
     */
    /**
     * @param mimeType The mime-type
     */
    @SerializedName("mime-type")
    @Expose
    var mimeType: String? = null
    /**
     * @return The focusPoint
     */
    /**
     * @param focusPoint The focus-point
     */
    @SerializedName("focus-point")
    @Expose
    var focusPoint: List<Int>? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.width)
        dest.writeInt(this.height)
        dest.writeString(this.mimeType)
        dest.writeList(this.focusPoint)
    }

    constructor() {}

    protected constructor(parcel: Parcel) {
        this.width = parcel.readInt()
        this.height = parcel.readInt()
        this.mimeType = parcel.readString()
        this.focusPoint = ArrayList()
        parcel.readList(this.focusPoint, Int::class.java.classLoader)
    }

    companion object CREATOR : Parcelable.Creator<HeroImageMetadata> {
        override fun createFromParcel(parcel: Parcel): HeroImageMetadata {
            return HeroImageMetadata(parcel)
        }

        override fun newArray(size: Int): Array<HeroImageMetadata?> {
            return arrayOfNulls(size)
        }
    }
}