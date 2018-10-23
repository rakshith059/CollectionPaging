package com.example.androidcore.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class StoryMetaData : Parcelable {

    /**
     * Check if Story is closed
     *
     * @return boolean true is closed else false
     */
    @SerializedName("is-closed")
    var isClosed: Boolean = false
    @SerializedName("review-title")
    var reviewTitle: String
    @SerializedName("review-rating")
    var reviewRating: ExtraData
    @SerializedName("story-attributes")
    var storyAttributes: JsonObject? = null
    @SerializedName("reference-url")
    var referenceUrl: String
    @SerializedName("sponsored-by")
    var sponsoredBy: String

    override fun toString(): String {
        return "StoryMetaData{" +
                "isClosed=" + isClosed +
                "reviewTitle=" + reviewTitle +
                "reviewRating=" + reviewRating +
                "storyAttributes=" + storyAttributes +
                "sponsoredBy=" + sponsoredBy +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (this.isClosed) 1.toByte() else 0.toByte())
        dest.writeString(this.reviewTitle)
        dest.writeParcelable(this.reviewRating, flags)
        dest.writeByte((if (storyAttributes == null) 0 else 1).toByte())
        if (storyAttributes != null)
            dest.writeString(this.storyAttributes!!.toString())
        dest.writeString(this.referenceUrl)
        dest.writeString(this.sponsoredBy)
    }

    protected constructor(parcel: Parcel) {
        this.isClosed = parcel.readByte().toInt() != 0
        this.reviewTitle = parcel.readString()
        this.reviewRating = parcel.readParcelable(ExtraData::class.java.classLoader)
        if (parcel.readByte().toInt() != 0) {
            this.storyAttributes = JsonParser().parse(parcel.readString()) as JsonObject
        }
        this.referenceUrl = parcel.readString()
        this.sponsoredBy = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<StoryMetaData> {
        override fun createFromParcel(parcel: Parcel): StoryMetaData {
            return StoryMetaData(parcel)
        }

        override fun newArray(size: Int): Array<StoryMetaData?> {
            return arrayOfNulls(size)
        }
    }
}