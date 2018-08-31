package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import quintype.com.templatecollectionwithrx.models.sections.Section
import quintype.com.templatecollectionwithrx.models.story.Tag
import java.util.ArrayList

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */
class Metadata : Parcelable {

    @SerializedName("cover-image")
    @Expose
    private val coverImage: CoverImage
    @SerializedName("tags")
    @Expose
    private var tags = ArrayList<Tag>()
    @SerializedName("snapshot")
    @Expose
    private val snapshot: CollectionSnapshot
    /*For TheQuint publisher, if the collectionTemplate is 'big-story' then the key will be 'sections'*/
    @SerializedName("sections")
    @Expose
    val sections: List<Section>
    /*For TheQuint publisher, if the collectionTemplate is not 'big-story' then the key will be 'section'*/
    @SerializedName("section")
    @Expose
    val section: List<Section>

    fun snapshot(): CollectionSnapshot {
        return snapshot
    }

    fun coverImage(): CoverImage {
        return coverImage
    }

    fun tags(): List<Tag> {
        return tags
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.coverImage, flags)
        dest.writeTypedList(this.tags)
        dest.writeParcelable(this.snapshot, flags)
        dest.writeTypedList(this.sections)
        dest.writeTypedList(this.section)
    }

    protected constructor(parcel: Parcel) {
        this.coverImage = parcel.readParcelable(CoverImage::class.java.classLoader)
        this.tags = parcel.createTypedArrayList(Tag.CREATOR)
        this.snapshot = parcel.readParcelable(CollectionSnapshot::class.java.classLoader)
        this.sections = parcel.createTypedArrayList(Section.CREATOR)
        this.section = parcel.createTypedArrayList(Section.CREATOR)
    }

    companion object CREATOR : Parcelable.Creator<Metadata> {
        override fun createFromParcel(parcel: Parcel): Metadata {
            return Metadata(parcel)
        }

        override fun newArray(size: Int): Array<Metadata?> {
            return arrayOfNulls(size)
        }
    }
}