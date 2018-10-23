package com.example.androidcore.models.config

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SeoMetadatum() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("publisher-id")
    @Expose
    var publisherId: Int? = null
    @SerializedName("owner-type")
    @Expose
    var ownerType: String? = null
    @SerializedName("owner-id")
    @Expose
    var ownerId: Int? = null
    @SerializedName("unused-guid")
    @Expose
    var unusedGuid: Any? = null
    @SerializedName("data")
    @Expose
    var data: SeoMetadatumData? = null
    @SerializedName("created-at")
    @Expose
    var createdAt: Long? = null
    @SerializedName("updated-at")
    @Expose
    var updatedAt: Long? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        publisherId = parcel.readValue(Int::class.java.classLoader) as? Int
        ownerType = parcel.readString()
        ownerId = parcel.readValue(Int::class.java.classLoader) as? Int
        createdAt = parcel.readValue(Long::class.java.classLoader) as? Long
        updatedAt = parcel.readValue(Long::class.java.classLoader) as? Long
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(publisherId)
        parcel.writeString(ownerType)
        parcel.writeValue(ownerId)
        parcel.writeValue(createdAt)
        parcel.writeValue(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<SeoMetadatum> {
        override fun createFromParcel(parcel: Parcel): SeoMetadatum {
            return SeoMetadatum(parcel)
        }

        override fun newArray(size: Int): Array<SeoMetadatum?> {
            return arrayOfNulls(size)
        }
    }
}
