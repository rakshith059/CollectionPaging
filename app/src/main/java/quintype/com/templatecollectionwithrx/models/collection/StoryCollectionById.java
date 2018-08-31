package quintype.com.templatecollectionwithrx.models.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import quintype.com.templatecollectionwithrx.models.story.Story;

/**
 * Created by akshaykoul on 27/04/16.
 */
public class StoryCollectionById implements Parcelable {

    @SerializedName("updated-at")
    @Expose
    private Long updatedAt;
    @SerializedName("publisher-id")
    @Expose
    private Long publisherId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("published-at")
    @Expose
    private Long publishedAt;
    @SerializedName("stories")
    @Expose
    private List<Story> stories = new ArrayList<Story>();
    @SerializedName("deleted-at")
    @Expose
    private Long deletedAt;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("created-at")
    @Expose
    private Long createdAt;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public Long updatedAt() {
        return updatedAt;
    }

    public Long publisherId() {
        return publisherId;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public Long publishedAt() {
        return publishedAt;
    }

    public List<Story> stories() {
        return stories;
    }

    public Long deletedAt() {
        return deletedAt;
    }

    public String summary() {
        return summary;
    }

    public Long getId() {
        return id;
    }

    public Long createdAt() {
        return createdAt;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.updatedAt);
        dest.writeValue(this.publisherId);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeValue(this.publishedAt);
        dest.writeTypedList(stories);
        dest.writeValue(this.deletedAt);
        dest.writeString(this.summary);
        dest.writeValue(this.id);
        dest.writeValue(this.createdAt);
        dest.writeParcelable(this.metadata, flags);
    }

    public StoryCollectionById() {
    }

    protected StoryCollectionById(Parcel in) {
        this.updatedAt = (Long) in.readValue(Long.class.getClassLoader());
        this.publisherId = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.type = in.readString();
        this.publishedAt = (Long) in.readValue(Long.class.getClassLoader());
        this.stories = in.createTypedArrayList(Story.CREATOR);
        this.deletedAt = (Long) in.readValue(Long.class.getClassLoader());
        this.summary = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.createdAt = (Long) in.readValue(Long.class.getClassLoader());
        this.metadata = in.readParcelable(Metadata.class.getClassLoader());
    }

    public static final Creator<StoryCollectionById> CREATOR = new Creator<StoryCollectionById>() {
        @Override
        public StoryCollectionById createFromParcel(Parcel source) {
            return new StoryCollectionById(source);
        }

        @Override
        public StoryCollectionById[] newArray(int size) {
            return new StoryCollectionById[size];
        }
    };
}
