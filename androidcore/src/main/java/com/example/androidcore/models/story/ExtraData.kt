package com.example.androidcore.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class ExtraData : Parcelable {
    @SerializedName("value")
    private val value: String
    @SerializedName("label")
    private val label: String

    fun value(): String {
        return value
    }

    fun label(): String {
        return label
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.value)
        dest.writeString(this.label)
    }

    protected constructor(`in`: Parcel) {
        this.value = `in`.readString()
        this.label = `in`.readString()
    }

    override fun toString(): String {
        return "ExtraData{" +
                "label=" + label +
                "value=" + value +
                '}'.toString()
    }

    companion object CREATOR : Parcelable.Creator<ExtraData> {
        override fun createFromParcel(parcel: Parcel): ExtraData {
            return ExtraData(parcel)
        }

        override fun newArray(size: Int): Array<ExtraData?> {
            return arrayOfNulls(size)
        }
    }
}