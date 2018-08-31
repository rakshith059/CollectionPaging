package quintype.com.templatecollectionwithrx.models.sections;

import com.google.gson.annotations.SerializedName;

import quintype.com.templatecollectionwithrx.models.collection.CollectionMeta;

public class SectionMeta {
    @SerializedName("id")
    private String id;
    @SerializedName("display-name")
    private String displayName;
    @SerializedName("name")
    private String name;
    @SerializedName("parent-id")
    private String parentId;
    @SerializedName("slug")
    private String slug;
    @SerializedName("collection")
    private CollectionMeta collectionMeta;

    public String id() {
        return id;
    }

    public String displayName() {
        return displayName;
    }

    public String name() {
        return name;
    }

    public String parentId() {
        return parentId;
    }

    public String slug() {
        return slug;
    }

    public CollectionMeta collectionMeta() {
        return collectionMeta;
    }

    public void collectionMeta(CollectionMeta collectionMeta) {
        this.collectionMeta = collectionMeta;
    }

    @Override
    public String toString() {
        return "SectionMeta{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}
