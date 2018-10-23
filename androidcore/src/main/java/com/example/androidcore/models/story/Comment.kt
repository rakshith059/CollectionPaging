package com.example.androidcore.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class Comment : Parcelable {

    @SerializedName("id")
    private val id: Long
    @SerializedName("upvotes")
    private val upVotes: Long
    @SerializedName("downvotes")
    private val downVotes: Long
    @SerializedName("text")
    private val text: String
    @SerializedName("member-id")
    private val memberId: Long
    @SerializedName("member-name")
    private val memberName: String
    @SerializedName("created-at")
    private val createdAt: Long

    fun id(): Long {
        return id
    }

    fun upVotes(): Long {
        return upVotes
    }

    fun downVotes(): Long {
        return downVotes
    }

    fun text(): String {
        return text
    }

    fun memberId(): Long {
        return memberId
    }

    fun memberName(): String {
        return memberName
    }

    fun createdAt(): Long {
        return createdAt
    }

    override fun toString(): String {
        return "Comment{" +
                "id=" + id +
                ", upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", text='" + text + '\''.toString() +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\''.toString() +
                ", createdAt=" + createdAt +
                '}'.toString()
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this.id)
        dest.writeLong(this.upVotes)
        dest.writeLong(this.downVotes)
        dest.writeString(this.text)
        dest.writeLong(this.memberId)
        dest.writeString(this.memberName)
        dest.writeLong(this.createdAt)
    }

    protected constructor(parcel: Parcel) {
        this.id = parcel.readLong()
        this.upVotes = parcel.readLong()
        this.downVotes = parcel.readLong()
        this.text = parcel.readString()
        this.memberId = parcel.readLong()
        this.memberName = parcel.readString()
        this.createdAt = parcel.readLong()
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }
}