package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * An object representing UserEngagement for story.
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
public class UserEngagement implements Parcelable {

    @SerializedName("story-content-id")
    private String storyContentId;
    @SerializedName("votes")
    private List<String> votes;
    @SerializedName("comments")
    private List<Comment> comments;

    public String storyContentId() {
        return storyContentId;
    }

    public List<String> votes() {
        return votes;
    }

    public List<Comment> comments() {
        return comments;
    }

    @Override
    public String toString() {
        return "UserEngagement{" +
                "storyContentId='" + storyContentId + '\'' +
                ", votes=" + votes +
                ", comments=" + comments +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.storyContentId);
        dest.writeStringList(this.votes);
        dest.writeTypedList(comments);
    }

    public UserEngagement() {
    }

    protected UserEngagement(Parcel in) {
        this.storyContentId = in.readString();
        this.votes = in.createStringArrayList();
        this.comments = in.createTypedArrayList(Comment.CREATOR);
    }

    public static final Creator<UserEngagement> CREATOR = new Creator<UserEngagement>() {
        public UserEngagement createFromParcel(Parcel source) {
            return new UserEngagement(source);
        }

        public UserEngagement[] newArray(int size) {
            return new UserEngagement[size];
        }
    };
}
