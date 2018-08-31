package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * An object representing Story Comments.
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
public class Comment implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("upvotes")
    private long upVotes;
    @SerializedName("downvotes")
    private long downVotes;
    @SerializedName("text")
    private String text;
    @SerializedName("member-id")
    private long memberId;
    @SerializedName("member-name")
    private String memberName;
    @SerializedName("created-at")
    private long createdAt;

    public long id() {
        return id;
    }

    public long upVotes() {
        return upVotes;
    }

    public long downVotes() {
        return downVotes;
    }

    public String text() {
        return text;
    }

    public long memberId() {
        return memberId;
    }

    public String memberName() {
        return memberName;
    }

    public long createdAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", text='" + text + '\'' +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.upVotes);
        dest.writeLong(this.downVotes);
        dest.writeString(this.text);
        dest.writeLong(this.memberId);
        dest.writeString(this.memberName);
        dest.writeLong(this.createdAt);
    }

    public Comment() {
    }

    protected Comment(Parcel in) {
        this.id = in.readLong();
        this.upVotes = in.readLong();
        this.downVotes = in.readLong();
        this.text = in.readString();
        this.memberId = in.readLong();
        this.memberName = in.readString();
        this.createdAt = in.readLong();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
