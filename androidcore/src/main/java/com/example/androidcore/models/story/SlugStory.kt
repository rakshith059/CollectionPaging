package com.example.androidcore.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class SlugStory : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("story")
    public val story: Story

    override fun toString(): String {
        return "SlugStory{" +
                "story=" + story +
                '}'.toString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.story, 0)
    }

    protected constructor(parcel: Parcel) {
        this.story = parcel.readParcelable(Story::class.java.classLoader)
    }

    companion object CREATOR : Parcelable.Creator<SlugStory> {
        override fun createFromParcel(source: Parcel): SlugStory {
            return SlugStory(source)
        }

        override fun newArray(size: Int): Array<SlugStory?> {
            return arrayOfNulls(size)
        }
    }
}
