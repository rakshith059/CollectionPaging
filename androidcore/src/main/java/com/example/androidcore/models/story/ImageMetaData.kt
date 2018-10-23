package com.example.androidcore.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * An object instance representing Story ImageMetaData
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class ImageMetaData : Parcelable {

    @SerializedName("width")
    var width: Int
    @SerializedName("height")
    var height: Int
    @SerializedName("focus-point")
    var focusPoints: IntArray? = null

    override fun toString(): String {
        return "ImageMetaData{" +
                "width=" + width +
                ", height=" + height +
                ", focusPoints=" + Arrays.toString(focusPoints) +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.width)
        dest.writeInt(this.height)
        dest.writeIntArray(this.focusPoints)
    }

    protected constructor(parcel: Parcel) {
        this.width = parcel.readInt()
        this.height = parcel.readInt()
        this.focusPoints = parcel.createIntArray()
    }

    companion object CREATOR : Parcelable.Creator<ImageMetaData> {
        override fun createFromParcel(source: Parcel): ImageMetaData {
            return ImageMetaData(source)
        }

        override fun newArray(size: Int): Array<ImageMetaData?> {
            return arrayOfNulls(size)
        }
    }
}
