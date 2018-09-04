package quintype.com.templatecollectionwithrx.models.collection

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import quintype.com.templatecollectionwithrx.models.story.Story
import java.util.ArrayList

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class StoryCollectionById : Parcelable {

    @SerializedName("updated-at")
    @Expose
    private val updatedAt: Long?
    @SerializedName("publisher-id")
    @Expose
    private val publisherId: Long?
    @SerializedName("name")
    @Expose
    private val name: String
    @SerializedName("type")
    @Expose
    private val type: String
    @SerializedName("published-at")
    @Expose
    private val publishedAt: Long?
    @SerializedName("stories")
    @Expose
    private var stories = ArrayList<Story>()
    @SerializedName("deleted-at")
    @Expose
    private val deletedAt: Long?
    @SerializedName("summary")
    @Expose
    private val summary: String
    @SerializedName("id")
    @Expose
    val id: Long?
    @SerializedName("created-at")
    @Expose
    private val createdAt: Long?
    @SerializedName("metadata")
    @Expose
    val metadata: Metadata

    fun updatedAt(): Long? {
        return updatedAt
    }

    fun publisherId(): Long? {
        return publisherId
    }

    fun name(): String {
        return name
    }

    fun type(): String {
        return type
    }

    fun publishedAt(): Long? {
        return publishedAt
    }

    fun stories(): List<Story> {
        return stories
    }

    fun deletedAt(): Long? {
        return deletedAt
    }

    fun summary(): String {
        return summary
    }

    fun createdAt(): Long? {
        return createdAt
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(this.updatedAt)
        dest.writeValue(this.publisherId)
        dest.writeString(this.name)
        dest.writeString(this.type)
        dest.writeValue(this.publishedAt)
        dest.writeTypedList(stories)
        dest.writeValue(this.deletedAt)
        dest.writeString(this.summary)
        dest.writeValue(this.id)
        dest.writeValue(this.createdAt)
        dest.writeParcelable(this.metadata, flags)
    }

    protected constructor(parcel: Parcel) {
        this.updatedAt = parcel.readValue(Long::class.java.classLoader) as Long
        this.publisherId = parcel.readValue(Long::class.java.classLoader) as Long
        this.name = parcel.readString()
        this.type = parcel.readString()
        this.publishedAt = parcel.readValue(Long::class.java.classLoader) as Long
        this.stories = parcel.createTypedArrayList(Story.CREATOR)
        this.deletedAt = parcel.readValue(Long::class.java.classLoader) as Long
        this.summary = parcel.readString()
        this.id = parcel.readValue(Long::class.java.classLoader) as Long
        this.createdAt = parcel.readValue(Long::class.java.classLoader) as Long
        this.metadata = parcel.readParcelable(Metadata::class.java.classLoader)
    }

    companion object CREATOR : Parcelable.Creator<StoryCollectionById> {
        override fun createFromParcel(parcel: Parcel): StoryCollectionById {
            return StoryCollectionById(parcel)
        }

        override fun newArray(size: Int): Array<StoryCollectionById?> {
            return arrayOfNulls(size)
        }
    }

}
