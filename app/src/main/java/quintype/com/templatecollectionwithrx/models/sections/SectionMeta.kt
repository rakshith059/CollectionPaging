package quintype.com.templatecollectionwithrx.models.sections

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import quintype.com.templatecollectionwithrx.models.collection.CollectionMeta

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class SectionMeta() : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(collectionMeta, flags)
        dest?.writeString(this.id)
        dest?.writeString(this.displayName)
        dest?.writeString(this.name)
        dest?.writeString(this.parentId)
        dest?.writeString(this.slug)
    }

    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("id")
    val id: String? = null
    @SerializedName("display-name")
    val displayName: String? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("parent-id")
    val parentId: String? = null
    @SerializedName("slug")
    val slug: String? = null
    @SerializedName("collection")
    var collectionMeta: CollectionMeta? = null

    constructor(parcel: Parcel) : this() {
        collectionMeta = parcel.readParcelable(CollectionMeta::class.java.classLoader)
    }

    override fun toString(): String {
        return "SectionMeta{" +
                "id='" + id + '\''.toString() +
                ", displayName='" + displayName + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", slug='" + slug + '\''.toString() +
                '}'.toString()
    }

    companion object CREATOR : Parcelable.Creator<SectionMeta> {
        override fun createFromParcel(parcel: Parcel): SectionMeta {
            return SectionMeta(parcel)
        }

        override fun newArray(size: Int): Array<SectionMeta?> {
            return arrayOfNulls(size)
        }
    }
}