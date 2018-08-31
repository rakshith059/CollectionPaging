package quintype.com.templatecollectionwithrx.models.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * Basic Entity. Should not be used as is. Extend this class in your app and make it Parcelable.
 *
 * Following are the common values in all entities:
 * <p>
 * "updated-at": 1499191886946,
 * "publisher-id": 70,
 * "name": "Frenny Bawa",
 * "type": "individual",
 * "entity-type-id": 29,
 * "deleted-at": null,
 * "created-by": 88015,
 * "id": 32423,
 * "last-updated-by": 88015,
 * "created-at": 1499191886946
 */

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
        return updatedAt;
    }

    public String publisherId() {
        return publisherId;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String entityTypeId() {
        return entityTypeId;
    }

    public long deletedAt() {
        return deletedAt;
    }

    public String createdBy() {
        return createdBy;
    }

    public String id() {
        return id;
    }

    public String lastUpdatedBy() {
        return lastUpdatedBy;
    }

    public String createdAt() {
        return createdAt;
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

    /**
     * Override this method in classes that extend EntityModel and add the extra fields in this
     * form, e.g.:
     *
     * return "BookEntity{" +               //Name of your class + starting bound
     *        super.toString() +            //Call super for the common fields
     *        ", link='" + link + '\'' +    //Add the extra fields
     *        '}'; //end bound
     *
     * @return a string with all the common fields in Entities
     */
    @Override
    public String toString() {
        return "updatedAt=" + updatedAt +
                ", publisherId='" + publisherId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", entityTypeId='" + entityTypeId + '\'' +
                ", deletedAt=" + deletedAt +
                ", createdBy='" + createdBy + '\'' +
                ", id='" + id + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", createdAt='" + createdAt;
    }

    /**
     * Use the methods below to get an instance of a specific entity type: one that extends this
     * class
     *
     * @param entityJson the whole entity in JSON form
     * @param entityType the Type or Class name of your entity
     * @param <ENTITY> generic class that extends EntityModel
     * @return the JSON data deserialised into your entity class
     */

    public static <ENTITY extends EntityModel> ENTITY createFromJson(JsonObject entityJson, TypeToken<ENTITY> entityType) {
        Gson gson = new Gson();
        return gson.fromJson(entityJson, entityType.getType());
    }
    public static <ENTITY extends EntityModel> ENTITY createFromJson(JsonObject entityJson, Class<ENTITY> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(entityJson, clazz);
    }

    @Override
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

    /*
    Fixed the parcelable model according to the Stack overflow posts
    https://stackoverflow.com/a/10841502
    https://stackoverflow.com/a/35250396
     */
}
