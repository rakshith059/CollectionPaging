package quintype.com.templatecollectionwithrx.models.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import quintype.com.templatecollectionwithrx.models.story.Story;

/**
 * Created by akshaykoul on 18/04/16.
 */
public class StoryCollection implements Parcelable {

    @SerializedName("updated-at")
    @Expose
    private long updatedAt;
    @SerializedName("publisher-id")
    @Expose
    private String publisherId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("published-at")
    @Expose
    private long publishedAt;
    @SerializedName("collection-date")
    @Expose
    private long collectionDate;
    @SerializedName("deleted-at")
    @Expose
    private Long deletedAt;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("story-content-ids")
    @Expose
    private List<String> storyContentIds = new ArrayList<String>();
    @SerializedName("created-at")
    @Expose
    private long createdAt;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("stories")
    @Expose
    private List<Story> stories = new ArrayList<Story>();


    public static final Creator<StoryCollection> CREATOR = new Creator<StoryCollection>() {
        @Override
        public StoryCollection createFromParcel(Parcel in) {
            return new StoryCollection(in);
        }

        @Override
        public StoryCollection[] newArray(int size) {
            return new StoryCollection[size];
        }
    };

    public long updatedAt() {
        return updatedAt;
    }

    public String publisherId() {
        return publisherId;
    }

    public String name() {
        return name;
    }

    public long publishedAt() {
        return publishedAt;
    }

    public long collectionDate() {
        return collectionDate;
    }

    public Long deletedAt() {
        return deletedAt;
    }

    public List<Story> stories() {
        return stories;
    }

    public String summary() {
        return summary;
    }

    public String getId() {
        return id;
    }

    public List<String> storyContentIds() {
        return storyContentIds;
    }

    public long createdAt() {
        return createdAt;
    }

    public Metadata metadata() {
        return metadata;
    }

    public StoryCollection() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.updatedAt);
        dest.writeString(this.publisherId);
        dest.writeString(this.name);
        dest.writeLong(this.publishedAt);
        dest.writeLong(this.collectionDate);
        dest.writeValue(this.deletedAt);
        dest.writeString(this.summary);
        dest.writeString(this.id);
        dest.writeStringList(this.storyContentIds);
        dest.writeLong(this.createdAt);
        dest.writeParcelable(this.metadata, flags);
        dest.writeTypedList(this.stories);
    }

    protected StoryCollection(Parcel in) {
        this.updatedAt = in.readLong();
        this.publisherId = in.readString();
        this.name = in.readString();
        this.publishedAt = in.readLong();
        this.collectionDate = in.readLong();
        this.deletedAt = (Long) in.readValue(Long.class.getClassLoader());
        this.summary = in.readString();
        this.id = in.readString();
        this.storyContentIds = in.createStringArrayList();
        this.createdAt = in.readLong();
        this.metadata = in.readParcelable(Metadata.class.getClassLoader());
        this.stories = in.createTypedArrayList(Story.CREATOR);
    }
}
