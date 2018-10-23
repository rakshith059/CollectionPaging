package com.example.androidcore.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.androidcore.models.story.Story

class CollectionItem() : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.id)
        dest?.writeString(this.type)
        dest?.writeParcelable(this.story, flags)
        dest?.writeString(this.name)
        dest?.writeString(this.slug)
        dest?.writeString(this.template)
        dest?.writeParcelable(this.metadata, flags)
        dest?.writeParcelable(this.associatedMetadata, flags)
        dest?.writeParcelable(this.item, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("associated-metadata")
    @Expose
    var associatedMetadata: AssociatedMetadata? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("template")
    @Expose
    var template: String? = null
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? = null
    @SerializedName("score")
    @Expose
    var score: Any? = null
    @SerializedName("item")
    @Expose
    var item: Item_? = null
    @SerializedName("story")
    @Expose
    var story: Story? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        type = parcel.readString()
        associatedMetadata = parcel.readParcelable(AssociatedMetadata::class.java.classLoader)
        name = parcel.readString()
        slug = parcel.readString()
        template = parcel.readString()
        metadata = parcel.readParcelable(Metadata::class.java.classLoader)
        story = parcel.readParcelable(Story::class.java.classLoader)
    }

    companion object CREATOR : Parcelable.Creator<CollectionItem> {
        override fun createFromParcel(parcel: Parcel): CollectionItem {
            return CollectionItem(parcel)
        }

        override fun newArray(size: Int): Array<CollectionItem?> {
            return arrayOfNulls(size)
        }
    }
}