package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.StringDef
import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Constants.Companion.TYPE_GALLERY
import quintype.com.templatecollectionwithrx.utils.Constants.Companion.TYPE_INVALID
import quintype.com.templatecollectionwithrx.utils.Constants.Companion.TYPE_SLIDESHOW
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class StoryElementSubTypeMetaData : Parcelable {

    @SerializedName("tweet-url")
    private val tweetUrl: String
    @SerializedName("tweet-id")
    public val tweetId: String
    @SerializedName("attribution")
    private val attribution: String
    @SerializedName("content")
    private val content: String
    @SerializedName("type")
    private val type: String
    @SerializedName("question")
    private val question: String
    @SerializedName("answer")
    private val answer: String
    @SerializedName("video-id")
    var videoId: String
    @SerializedName("file-name")
    internal var fileName: String? = null
    @SerializedName("has-header")
    internal var hasHeader: Boolean = false
    @SerializedName("linked-story")
    internal var linkedStory: LinkedStory
    @SerializedName("linked-story-id")
    var linkedStoryId: String
    @SerializedName("account-id")
    private val mBrightCoveAccountID: String
    @SerializedName("poster-url")
    private val mBrightCovePosterURL: String

    @Retention(RetentionPolicy.SOURCE)
    @StringDef(Constants.TYPE_SLIDESHOW, Constants.TYPE_GALLERY, Constants.TYPE_INVALID)
    annotation class Type

    override fun toString(): String {
        return "StoryElementSubTypeMetaData{" +
                "tweetUrl='" + tweetUrl + '\''.toString() +
                ", tweetId='" + tweetId + '\''.toString() +
                ", attribution='" + attribution + '\''.toString() +
                ", content='" + content + '\''.toString() +
                ", type='" + type + '\''.toString() +
                ", question='" + question + '\''.toString() +
                ", answer='" + answer + '\''.toString() +
                ", videoId='" + videoId + '\''.toString() +
                ", hasHeader='" + hasHeader + '\''.toString() +
                ", linkedStory='" + linkedStory + '\''.toString() +
                ", linkedStoryId='" + linkedStoryId + '\''.toString() +
                ", mBrightCoveAccountID='" + mBrightCoveAccountID + '\''.toString() +
                ", mBrightCovePosterURL='" + mBrightCovePosterURL + '\''.toString() +
                '}'.toString()
    }

    /**
     * Type of SubElement
     *
     * @return String [Type]
     */
    fun type(): String {
        return if (TextUtils.isEmpty(this.type)) {
            "invalid"
        } else if (this.isTypeSlideShow()) {
            "slideshow"
        } else {
            if (this.isTypeGallery()) "gallery" else "invalid"
        }
    }

    /**
     * Check if SubElement is of type gallery
     *
     * @return true if the element is gallery else false
     */
    fun isTypeGallery(): Boolean {
        return type.equals(TYPE_GALLERY, true)
    }

    /**
     * Check if SubElement is of type slide show
     *
     * @return true is the element is slideshow else false
     */
    fun isTypeSlideShow(): Boolean {
        return type.equals(TYPE_SLIDESHOW, true)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.tweetUrl)
        dest.writeString(this.tweetId)
        dest.writeString(this.attribution)
        dest.writeString(this.content)
        dest.writeString(this.type)
        dest.writeString(this.question)
        dest.writeString(this.answer)
        dest.writeString(this.videoId)
        dest.writeByte(if (hasHeader) 1.toByte() else 0.toByte())
        dest.writeParcelable(this.linkedStory, 0)
        dest.writeString(this.linkedStoryId)
        dest.writeString(this.mBrightCoveAccountID)
        dest.writeString(this.mBrightCovePosterURL)
    }

    protected constructor(parcel: Parcel) {
        this.tweetUrl = parcel.readString()
        this.tweetId = parcel.readString()
        this.attribution = parcel.readString()
        this.content = parcel.readString()
        this.type = parcel.readString()
        this.question = parcel.readString()
        this.answer = parcel.readString()
        this.videoId = parcel.readString()
        this.hasHeader = parcel.readByte().toInt() != 0
        this.linkedStory = parcel.readParcelable(LinkedStory::class.java.classLoader)
        this.linkedStoryId = parcel.readString()
        this.mBrightCoveAccountID = parcel.readString()
        this.mBrightCovePosterURL = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<StoryElementSubTypeMetaData> {
        override fun createFromParcel(parcel: Parcel): StoryElementSubTypeMetaData {
            return StoryElementSubTypeMetaData(parcel)
        }

        override fun newArray(size: Int): Array<StoryElementSubTypeMetaData?> {
            return arrayOfNulls(size)
        }
    }
}