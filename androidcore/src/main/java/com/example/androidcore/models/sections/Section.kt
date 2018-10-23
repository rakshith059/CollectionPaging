package com.example.androidcore.models.sections

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class Section : Parcelable {

    @SerializedName("name")
    var name: String
    @SerializedName("id")
    var id: String = ""
    @SerializedName("display-name")
    var displayName: String = ""
    @SerializedName("trending-stories-story-order")
    var trendingStories = ArrayList<String>()
    @SerializedName("featured-stories-story-order")
    var featuredStories = ArrayList<String>()
    @SerializedName("updated-at")
    var updateAt: Long = 0
    @SerializedName("story-order")
    var stories = ArrayList<String>()
    @SerializedName("publisher-id")
    var publisherId: String = ""

    override fun toString(): String {
        return "Section{" +
                "name='" + name + '\''.toString() +
                ", id='" + id + '\''.toString() +
                ", displayName='" + displayName + '\''.toString() +
                ", trendingStories=" + trendingStories +
                ", featuredStories=" + featuredStories +
                ", updatedAt=" + updateAt +
                ", stories=" + stories +
                ", publisherId='" + publisherId + '\''.toString() +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.name)
        dest.writeString(this.id)
        dest.writeString(this.displayName)
        dest.writeStringList(this.trendingStories)
        dest.writeStringList(this.featuredStories)
        dest.writeLong(this.updateAt)
        dest.writeStringList(this.stories)
        dest.writeString(this.publisherId)
    }

    protected constructor(parcel: Parcel) {
        this.name = parcel.readString()
        this.id = parcel.readString()
        this.displayName = parcel.readString()
        this.trendingStories = parcel.createStringArrayList()
        this.featuredStories = parcel.createStringArrayList()
        this.updateAt = parcel.readLong()
        this.stories = parcel.createStringArrayList()
        this.publisherId = parcel.readString()
    }

    constructor(mSectionName: String) {
        this.name = mSectionName
    }

    companion object CREATOR : Parcelable.Creator<Section> {
        override fun createFromParcel(parcel: Parcel): Section {
            return Section(parcel)
        }

        override fun newArray(size: Int): Array<Section?> {
            return arrayOfNulls(size)
        }
    }
}