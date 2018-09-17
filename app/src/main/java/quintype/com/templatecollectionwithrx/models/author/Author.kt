package quintype.com.templatecollectionwithrx.models.author

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 8/31/18.
 */

class Author : Parcelable {

    /**
     * id : 705
     * publisher-id : 13
     * name : Radhesh Kanumury
     * twitter-handle : null
     * bio : Radhesh Kanumury leads the Global Entrepreneur Program for IBM India and South Asia.
     * avatar-url : http://d9zv4zsfqrm9s.cloudfront
     * .net/staging_swarajya/2016-02/eccf5406-4f5f-4787-a37e-b7a971393239/54e719f20385c.jpg
     * avatar-s3-key : staging_swarajya/2016-02/eccf5406-4f5f-4787-a37e-b7a971393239
     * /54e719f20385c.jpg
     * slug : radhesh-kanumury
     */


    @SerializedName("id")
    var id: String? = null
    @SerializedName("publisher-id")
    var publisherId: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("twitter-handle")
    var twitterHandle: String? = null
    @SerializedName("bio")
    var bio: String? = null
    @SerializedName("avatar-url")
    var avatarUrl: String? = null
    @SerializedName("avatar-s3-key")
    var avatarS3Key: String? = null
    @SerializedName("slug")
    var slug: String? = null
    @SerializedName("email")
    var email: String? = null

    constructor(builder: Builder) {
        id = builder.id
        publisherId = builder.publisherId
        name = builder.name
        twitterHandle = builder.twitterHandle
        bio = builder.bio
        avatarUrl = builder.avatarUrl
        avatarS3Key = builder.avatarS3Key
        slug = builder.slug
        email = builder.email
    }

    override fun toString(): String {
        return "Author{" +
                "name='" + name + '\''.toString() +
                ", id='" + id + '\''.toString() +
                ", twitterHandle=" + twitterHandle +
                ", bio=" + bio +
                ", avatarUrl=" + avatarUrl +
                ", avatarS3Key=" + avatarS3Key +
                ", slug=" + slug +
                ", email='" + email + '\''.toString() +
                ", publisherId='" + publisherId + '\''.toString() +
                '}'.toString()
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeString(this.publisherId)
        dest.writeString(this.name)
        dest.writeString(this.twitterHandle)
        dest.writeString(this.bio)
        dest.writeString(this.avatarUrl)
        dest.writeString(this.avatarS3Key)
        dest.writeString(this.slug)
        dest.writeString(this.email)
    }

    protected constructor(parcel: Parcel) {
        this.id = parcel.readString()
        this.publisherId = parcel.readString()
        this.name = parcel.readString()
        this.twitterHandle = parcel.readString()
        this.bio = parcel.readString()
        this.avatarUrl = parcel.readString()
        this.avatarS3Key = parcel.readString()
        this.slug = parcel.readString()
        this.email = parcel.readString()
    }


    class Builder {
        var id: String? = null
        var publisherId: String? = null
        var name: String? = null
        var twitterHandle: String? = null
        var bio: String? = null
        var avatarUrl: String? = null
        var avatarS3Key: String? = null
        var slug: String? = null
        var email: String? = null

        fun id(`val`: String): Builder {
            id = `val`
            return this
        }

        fun publisherId(`val`: String): Builder {
            publisherId = `val`
            return this
        }

        fun name(`val`: String): Builder {
            name = `val`
            return this
        }

        fun twitterHandle(`val`: String): Builder {
            twitterHandle = `val`
            return this
        }

        fun bio(`val`: String): Builder {
            bio = `val`
            return this
        }

        fun avatarUrl(`val`: String): Builder {
            avatarUrl = `val`
            return this
        }

        fun avatarS3Key(`val`: String): Builder {
            avatarS3Key = `val`
            return this
        }

        fun slug(`val`: String): Builder {
            slug = `val`
            return this
        }

        fun email(`val`: String): Builder {
            email = `val`
            return this
        }

        fun build(): Author {
            return Author(this)
        }
    }

    companion object CREATOR : Parcelable.Creator<Author> {
        override fun createFromParcel(parcel: Parcel): Author {
            return Author(parcel)
        }

        override fun newArray(size: Int): Array<Author?> {
            return arrayOfNulls(size)
        }
    }
}