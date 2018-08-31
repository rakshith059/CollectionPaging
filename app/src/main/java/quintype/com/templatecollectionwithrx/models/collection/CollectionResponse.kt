package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class CollectionResponse : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("slug")
    @Expose
    var slug: String
    @SerializedName("name")
    @Expose
    var name: String
    @SerializedName("summary")
    @Expose
    var summary: String
    @SerializedName("created-at")
    @Expose
    var createdAt: String
    @SerializedName("updated-at")
    @Expose
    var updatedAt: String
    @SerializedName("items")
    @Expose
    var items: List<CollectionItem>? = null
    @SerializedName("total-count")
    @Expose
    var totalCount: Int = 0
    @SerializedName("metadata")
    @Expose
    var collectionMetadata: Metadata
    @SerializedName("template")
    @Expose
    var template: String

    constructor(parcel: Parcel) {
        id = parcel.readInt()
        slug = parcel.readString()
        name = parcel.readString()
        summary = parcel.readString()
        createdAt = parcel.readString()
        updatedAt = parcel.readString()
        items = parcel.createTypedArrayList(CollectionItem)
        totalCount = parcel.readInt()
        collectionMetadata = parcel.readParcelable(Metadata::class.java.classLoader)
        template = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(slug)
        parcel.writeString(name)
        parcel.writeString(summary)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeTypedList(items)
        parcel.writeInt(totalCount)
        parcel.writeParcelable(collectionMetadata, i)
        parcel.writeString(template)
    }

    override fun toString(): String {
        return "CollectionResponse{" +
                "id=" + id +
                ", slug='" + slug + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", summary='" + summary + '\''.toString() +
                ", createdAt='" + createdAt + '\''.toString() +
                ", updatedAt='" + updatedAt + '\''.toString() +
                ", items=" + items +
                ", totalCount=" + totalCount +
                ", collectionMetadata=" + collectionMetadata +
                ", template='" + template + '\''.toString() +
                '}'.toString()
    }

    fun template(): String {
        return template
    }

    fun template(template: String) {
        this.template = template
    }

    companion object CREATOR : Parcelable.Creator<CollectionResponse> {
        override fun createFromParcel(parcel: Parcel): CollectionResponse {
            return CollectionResponse(parcel)
        }

        override fun newArray(size: Int): Array<CollectionResponse?> {
            return arrayOfNulls(size)
        }
    }
}