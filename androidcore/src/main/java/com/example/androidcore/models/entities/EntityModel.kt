//package com.example.androidcore.models.entities
//
//import android.os.Parcel
//import android.os.Parcelable
//import com.google.gson.Gson
//import com.google.gson.JsonObject
//import com.google.gson.annotations.SerializedName
//import com.google.gson.reflect.TypeToken
//
///**
// * Created TemplateCollectionWithRx by rakshith on 8/31/18.
// */
//
///**
// * Basic Entity. Should not be used as is. Extend this class in your app and make it Parcelable.
// *
// * Following are the common values in all entities:
// *
// *
// * "updated-at": 1499191886946,
// * "publisher-id": 70,
// * "name": "Frenny Bawa",
// * "type": "individual",
// * "entity-type-id": 29,
// * "deleted-at": null,
// * "created-by": 88015,
// * "id": 32423,
// * "last-updated-by": 88015,
// * "created-at": 1499191886946
// */
//
//abstract class EntityModel : Parcelable {
//    @SerializedName("updated-at")
//    protected var updatedAt: Long = 0
//    @SerializedName("publisher-id")
//    protected var publisherId: String
//    @SerializedName("name")
//    protected var name: String
//    @SerializedName("type")
//    protected var type: String
//    @SerializedName("entity-type-id")
//    protected var entityTypeId: String
//    @SerializedName("deleted-at")
//    protected var deletedAt: Long = 0
//    @SerializedName("created-by")
//    protected var createdBy: String
//    @SerializedName("id")
//    public var id: String
//    @SerializedName("last-updated-by")
//    protected var lastUpdatedBy: String
//    @SerializedName("created-at")
//    protected var createdAt: String
//
//    protected constructor(parcel: Parcel) {
//        this.updatedAt = parcel.readLong()
//        this.publisherId = parcel.readString()
//        this.name = parcel.readString()
//        this.type = parcel.readString()
//        this.entityTypeId = parcel.readString()
//        this.deletedAt = parcel.readLong()
//        this.createdBy = parcel.readString()
//        this.id = parcel.readString()
//        this.lastUpdatedBy = parcel.readString()
//        this.createdAt = parcel.readString()
//    }
//
//    /**
//     * Override this method in classes that extend EntityModel and add the extra fields in this
//     * form, e.g.:
//     *
//     * return "BookEntity{" +               //Name of your class + starting bound
//     * super.toString() +            //Call super for the common fields
//     * ", link='" + link + '\'' +    //Add the extra fields
//     * '}'; //end bound
//     *
//     * @return a string with all the common fields in Entities
//     */
//    override fun toString(): String {
//        return "updatedAt=" + updatedAt +
//                ", publisherId='" + publisherId + '\''.toString() +
//                ", name='" + name + '\''.toString() +
//                ", type='" + type + '\''.toString() +
//                ", entityTypeId='" + entityTypeId + '\''.toString() +
//                ", deletedAt=" + deletedAt +
//                ", createdBy='" + createdBy + '\''.toString() +
//                ", id='" + id + '\''.toString() +
//                ", lastUpdatedBy='" + lastUpdatedBy + '\''.toString() +
//                ", createdAt='" + createdAt
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeLong(this.updatedAt)
//        dest.writeString(this.publisherId)
//        dest.writeString(this.name)
//        dest.writeString(this.type)
//        dest.writeString(this.entityTypeId)
//        dest.writeLong(this.deletedAt)
//        dest.writeString(this.createdBy)
//        dest.writeString(this.id)
//        dest.writeString(this.lastUpdatedBy)
//        dest.writeString(this.createdAt)
//    }
//
//    companion object {
//
//        /**
//         * Use the methods below to get an instance of a specific entity type: one that extends this
//         * class
//         *
//         * @param entityJson the whole entity in JSON form
//         * @param entityType the Type or Class name of your entity
//         * @param <ENTITY> generic class that extends EntityModel
//         * @return the JSON data deserialised into your entity class
//        </ENTITY> */
//
//        fun <ENTITY : EntityModel> createFromJson(entityJson: JsonObject, entityType: TypeToken<ENTITY>): ENTITY? {
//            val gson = Gson()
//            return gson.fromJson<ENTITY>(entityJson, entityType.type)
//        }
//
//        fun <ENTITY : EntityModel> createFromJson(entityJson: JsonObject, clazz: Class<ENTITY>): ENTITY {
//            val gson = Gson()
//            return gson.fromJson(entityJson, clazz)
//        }
//    }
//
//    /*
//    Fixed the parcelable model according to the Stack overflow posts
//    https://stackoverflow.com/a/10841502
//    https://stackoverflow.com/a/35250396
//     */
//}
//
