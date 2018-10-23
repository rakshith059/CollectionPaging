package com.example.androidcore.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class LinkedStory(parcel: Parcel) : Parcelable {
    @SerializedName("headline")
    private var mHeadline: String? = null
    @SerializedName("story-content-id")
    var storyContentId: String? = null
    @SerializedName("id")
    private var mStoryId: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(this.mHeadline)
        parcel.writeString(this.storyContentId)
        parcel.writeString(this.mStoryId)
    }

    init {
        this.mHeadline = parcel.readString()
        this.storyContentId = parcel.readString()
        this.mStoryId = parcel.readString()
    }

    override fun toString(): String {
        return "LinkedStory{" +
                "mHeadline='" + mHeadline + '\''.toString() +
                ", storyContentId='" + storyContentId + '\''.toString() +
                ", mStoryId='" + mStoryId + '\''.toString() +
                '}'.toString()
    }

    companion object CREATOR : Parcelable.Creator<LinkedStory> {
        override fun createFromParcel(parcel: Parcel): LinkedStory {
            return LinkedStory(parcel)
        }

        override fun newArray(size: Int): Array<LinkedStory?> {
            return arrayOfNulls(size)
        }
    }
}