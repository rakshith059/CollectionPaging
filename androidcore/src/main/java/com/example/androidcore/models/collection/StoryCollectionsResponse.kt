package com.example.androidcore.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class StoryCollectionsResponse() : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeTypedList(this.storyCollections)
    }

    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("story-collections")
    @Expose
    private var storyCollections: List<StoryCollection> = ArrayList()

    constructor(parcel: Parcel) : this() {
        storyCollections = parcel.createTypedArrayList(StoryCollection)
    }

    fun storyCollections(): List<StoryCollection> {
        return storyCollections
    }

    fun storyCollections(storyCollections: List<StoryCollection>) {
        this.storyCollections = storyCollections
    }

    companion object CREATOR : Parcelable.Creator<StoryCollectionsResponse> {
        override fun createFromParcel(parcel: Parcel): StoryCollectionsResponse {
            return StoryCollectionsResponse(parcel)
        }

        override fun newArray(size: Int): Array<StoryCollectionsResponse?> {
            return arrayOfNulls(size)
        }
    }
}