package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class UserEngagement : Parcelable {

    @SerializedName("story-content-id")
    private val storyContentId: String
    @SerializedName("votes")
    private val votes: List<String>
    @SerializedName("comments")
    private val comments: List<Comment>

    override fun toString(): String {
        return "UserEngagement{" +
                "storyContentId='" + storyContentId + '\''.toString() +
                ", votes=" + votes +
                ", comments=" + comments +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.storyContentId)
        dest.writeStringList(this.votes)
        dest.writeTypedList(comments)
    }

    protected constructor(`in`: Parcel) {
        this.storyContentId = `in`.readString()
        this.votes = `in`.createStringArrayList()
        this.comments = `in`.createTypedArrayList(Comment)
    }

    companion object CREATOR : Parcelable.Creator<UserEngagement> {
        override fun createFromParcel(source: Parcel): UserEngagement {
            return UserEngagement(source)
        }

        override fun newArray(size: Int): Array<UserEngagement?> {
            return arrayOfNulls(size)
        }
    }
}
