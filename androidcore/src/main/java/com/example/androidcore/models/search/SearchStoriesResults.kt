package com.example.androidcore.models.search

import android.os.Parcel
import android.os.Parcelable
import com.example.androidcore.models.story.Story
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SearchStoriesResults() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("from")
    @Expose
    public var from: Int? = null
    @SerializedName("size")
    @Expose
    public var size: Int? = null
    @SerializedName("total")
    @Expose
    public var total: Int? = null
    @SerializedName("fallback")
    @Expose
    public var fallback: Boolean? = null
    @SerializedName("stories")
    @Expose
    public var stories: List<Story>? = null
    @SerializedName("term")
    @Expose
    public var term: String? = null

    constructor(parcel: Parcel) : this() {
        from = parcel.readValue(Int::class.java.classLoader) as? Int
        size = parcel.readValue(Int::class.java.classLoader) as? Int
        total = parcel.readValue(Int::class.java.classLoader) as? Int
        fallback = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        stories = parcel.createTypedArrayList(Story.CREATOR)
        term = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(from)
        parcel.writeValue(size)
        parcel.writeValue(total)
        parcel.writeValue(fallback)
        parcel.writeTypedList(stories)
        parcel.writeString(term)
    }

    companion object CREATOR : Parcelable.Creator<SearchStoriesResults> {
        override fun createFromParcel(parcel: Parcel): SearchStoriesResults {
            return SearchStoriesResults(parcel)
        }

        override fun newArray(size: Int): Array<SearchStoriesResults?> {
            return arrayOfNulls(size)
        }
    }
}