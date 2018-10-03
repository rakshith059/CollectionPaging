package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */
class CollectionMeta : Parcelable {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("slug")
    var slug: String? = null
    @SerializedName("name")
    var name: String? = null

    fun id(): String? {
        return id
    }

    fun id(id: String) {
        this.id = id
    }

    fun slug(): String? {
        return slug
    }

    fun slug(slug: String) {
        this.slug = slug
    }

    fun name(): String? {
        return name
    }

    fun name(name: String) {
        this.name = name
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeString(this.slug)
        dest.writeString(this.name)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()
        this.slug = `in`.readString()
        this.name = `in`.readString()
    }

    companion object CREATOR : Parcelable.Creator<CollectionMeta> {
        override fun createFromParcel(parcel: Parcel): CollectionMeta {
            return CollectionMeta(parcel)
        }

        override fun newArray(size: Int): Array<CollectionMeta?> {
            return arrayOfNulls(size)
        }
    }
}
