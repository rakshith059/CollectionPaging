package quintype.com.templatecollectionwithrx.models.entities;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public abstract class EntityModel implements Parcelable {
    @SerializedName("updated-at")
    protected long updatedAt;
    @SerializedName("publisher-id")
    protected String publisherId;
    @SerializedName("name")
    protected String name;
    @SerializedName("type")
    protected String type;
    @SerializedName("entity-type-id")
    protected String entityTypeId;
    @SerializedName("deleted-at")
    protected long deletedAt;
    @SerializedName("created-by")
    protected String createdBy;
    @SerializedName("id")
    protected String id;
    @SerializedName("last-updated-by")
    protected String lastUpdatedBy;
    @SerializedName("created-at")
    protected String createdAt;

    public long updatedAt() {
        return this.updatedAt;
    }

    public String publisherId() {
        return this.publisherId;
    }

    public String name() {
        return this.name;
    }

    public String type() {
        return this.type;
    }

    public String entityTypeId() {
        return this.entityTypeId;
    }

    public long deletedAt() {
        return this.deletedAt;
    }

    public String createdBy() {
        return this.createdBy;
    }

    public String id() {
        return this.id;
    }

    public String lastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    protected EntityModel(Parcel in) {
        this.updatedAt = in.readLong();
        this.publisherId = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.entityTypeId = in.readString();
        this.deletedAt = in.readLong();
        this.createdBy = in.readString();
        this.id = in.readString();
        this.lastUpdatedBy = in.readString();
        this.createdAt = in.readString();
    }

    public EntityModel() {
    }

    public String toString() {
        return "updatedAt=" + this.updatedAt + ", publisherId='" + this.publisherId + '\'' + ", name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", entityTypeId='" + this.entityTypeId + '\'' + ", deletedAt=" + this.deletedAt + ", createdBy='" + this.createdBy + '\'' + ", id='" + this.id + '\'' + ", lastUpdatedBy='" + this.lastUpdatedBy + '\'' + ", createdAt='" + this.createdAt;
    }

    public static <ENTITY extends EntityModel> ENTITY createFromJson(JsonObject entityJson, TypeToken<ENTITY> entityType) {
        Gson gson = new Gson();
        return (ENTITY) gson.fromJson(entityJson, entityType.getType());
    }

    public static <ENTITY extends EntityModel> ENTITY createFromJson(JsonObject entityJson, Class<ENTITY> clazz) {
        Gson gson = new Gson();
        return (ENTITY) gson.fromJson(entityJson, clazz);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.updatedAt);
        dest.writeString(this.publisherId);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.entityTypeId);
        dest.writeLong(this.deletedAt);
        dest.writeString(this.createdBy);
        dest.writeString(this.id);
        dest.writeString(this.lastUpdatedBy);
        dest.writeString(this.createdAt);
    }
}
