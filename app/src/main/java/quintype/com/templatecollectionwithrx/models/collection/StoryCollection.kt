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

class StoryCollection : Parcelable {

    @SerializedName("updated-at")
    @Expose
    private val updatedAt: Long
    @SerializedName("publisher-id")
    @Expose
    private val publisherId: String
    @SerializedName("name")
    @Expose
    private val name: String
    @SerializedName("published-at")
    @Expose
    private val publishedAt: Long
    @SerializedName("collection-date")
    @Expose
    private val collectionDate: Long
    @SerializedName("deleted-at")
    @Expose
    private val deletedAt: Long?
    @SerializedName("summary")
    @Expose
    private val summary: String
    @SerializedName("id")
    @Expose
    val id: String
    @SerializedName("story-content-ids")
    @Expose
    private var storyContentIds = ArrayList<String>()
    @SerializedName("created-at")
    @Expose
    private val createdAt: Long
    @SerializedName("metadata")
    @Expose
    private val metadata: Metadata
    @SerializedName("stories")
    @Expose
    private var stories = ArrayList<Story>()

    fun updatedAt(): Long {
        return updatedAt
    }

    fun publisherId(): String {
        return publisherId
    }

    fun name(): String {
        return name
    }

    fun publishedAt(): Long {
        return publishedAt
    }

    fun collectionDate(): Long {
        return collectionDate
    }

    fun deletedAt(): Long? {
        return deletedAt
    }

    fun stories(): List<Story> {
        return stories
    }

    fun summary(): String {
        return summary
    }

    fun storyContentIds(): List<String> {
        return storyContentIds
    }

    fun createdAt(): Long {
        return createdAt
    }

    fun metadata(): Metadata {
        return metadata
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this.updatedAt)
        dest.writeString(this.publisherId)
        dest.writeString(this.name)
        dest.writeLong(this.publishedAt)
        dest.writeLong(this.collectionDate)
        dest.writeValue(this.deletedAt)
        dest.writeString(this.summary)
        dest.writeString(this.id)
        dest.writeStringList(this.storyContentIds)
        dest.writeLong(this.createdAt)
        dest.writeParcelable(this.metadata, flags)
        dest.writeTypedList(this.stories)
    }

    protected constructor(parcel: Parcel) {
        this.updatedAt = parcel.readLong()
        this.publisherId = parcel.readString()
        this.name = parcel.readString()
        this.publishedAt = parcel.readLong()
        this.collectionDate = parcel.readLong()
        this.deletedAt = parcel.readValue(Long::class.java.classLoader) as Long
        this.summary = parcel.readString()
        this.id = parcel.readString()
        this.storyContentIds = parcel.createStringArrayList()
        this.createdAt = parcel.readLong()
        this.metadata = parcel.readParcelable(Metadata::class.java.classLoader)
        this.stories = parcel.createTypedArrayList(Story.CREATOR)
    }

    companion object CREATOR : Parcelable.Creator<StoryCollection> {
        override fun createFromParcel(parcel: Parcel): StoryCollection {
            return StoryCollection(parcel)
        }

        override fun newArray(size: Int): Array<StoryCollection?> {
            return arrayOfNulls(size)
        }
    }

}