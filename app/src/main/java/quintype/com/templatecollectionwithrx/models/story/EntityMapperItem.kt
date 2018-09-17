package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Just a pojo to hold the id/name mapper items for entities
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class EntityMapperItem : Parcelable {

    @SerializedName("id")
    public var id: String? = null
    @SerializedName("name")
    public var name: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "EntityMapperItem{" +
                "id='" + id + '\''.toString() +
                ", name='" + name + '\''.toString() +
                '}'.toString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeString(this.name)
    }

    protected constructor(parcel: Parcel) {
        this.id = parcel.readString()
        this.name = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<EntityMapperItem> {
        override fun createFromParcel(source: Parcel): EntityMapperItem {
            return EntityMapperItem(source)
        }

        override fun newArray(size: Int): Array<EntityMapperItem?> {
            return arrayOfNulls(size)
        }
    }
}