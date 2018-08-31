package quintype.com.templatecollectionwithrx.models.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

public class CollectionResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("created-at")
    @Expose
    public String createdAt;
    @SerializedName("updated-at")
    @Expose
    public String updatedAt;
    @SerializedName("items")
    @Expose
    public List<CollectionItem> items = null;
    @SerializedName("total-count")
    @Expose
    public int totalCount;
    @SerializedName("metadata")
    @Expose
    public Metadata collectionMetadata;
    @SerializedName("template")
    @Expose
    public String template;

    public CollectionResponse(Parcel in) {
        id = in.readInt();
        slug = in.readString();
        name = in.readString();
        summary = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        items = in.createTypedArrayList(CollectionItem.CREATOR);
        totalCount = in.readInt();
        collectionMetadata = in.readParcelable(Metadata.class.getClassLoader());
        template = in.readString();
    }

    public static final Creator<CollectionResponse> CREATOR = new Creator<CollectionResponse>() {
        @Override
        public CollectionResponse createFromParcel(Parcel in) {
            return new CollectionResponse(in);
        }

        @Override
        public CollectionResponse[] newArray(int size) {
            return new CollectionResponse[size];
        }
    };

    public CollectionResponse() {
    }

    public int getId() {
        return id;
    }

    public void id(int id) {
        this.id = id;
    }

    public String slug() {
        return slug;
    }

    public void slug(String slug) {
        this.slug = slug;
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public String summary() {
        return summary;
    }

    public void summary(String summary) {
        this.summary = summary;
    }

    public String createdAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String updatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<CollectionItem> items() {
        return items;
    }

    public void setItems(List<CollectionItem> items) {
        this.items = items;
    }

    public int totalCount() {
        return totalCount;
    }

    public void totalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Metadata collectionMetadata() {
        return collectionMetadata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(slug);
        parcel.writeString(name);
        parcel.writeString(summary);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        parcel.writeTypedList(items);
        parcel.writeInt(totalCount);
        parcel.writeParcelable(collectionMetadata, i);
        parcel.writeString(template);
    }

    @Override
    public String toString() {
        return "CollectionResponse{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", items=" + items +
                ", totalCount=" + totalCount +
                ", collectionMetadata=" + collectionMetadata +
                ", template='" + template + '\'' +
                '}';
    }

    public String template() {
        return template;
    }

    public void template(String template) {
        this.template = template;
    }
}
