package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An object instance representing StoryElementSubTypeMetaData
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */

public class StoryElementSubTypeMetaData implements Parcelable {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TYPE_SLIDESHOW, TYPE_GALLERY, TYPE_INVALID})
    public @interface Type {
    }


    public static final String TYPE_SLIDESHOW = "slideshow";
    public static final String TYPE_GALLERY = "gallery";
    public static final String TYPE_INVALID = "invalid";

    @SerializedName("tweet-url")
    private String tweetUrl;
    @SerializedName("tweet-id")
    private String tweetId;
    @SerializedName("attribution")
    private String attribution;
    @SerializedName("content")
    private String content;
    @SerializedName("type")
    private String type;
    @SerializedName("question")
    private String question;
    @SerializedName("answer")
    private String answer;
    @SerializedName("video-id")
    public String videoId;
    @SerializedName("file-name")
    String fileName;
    @SerializedName("has-header")
    boolean hasHeader;
    @SerializedName("linked-story")
    LinkedStory linkedStory;
    @SerializedName("linked-story-id")
    public String linkedStoryId;
    @SerializedName("account-id")
    private String mBrightCoveAccountID;
    @SerializedName("poster-url")
    private String mBrightCovePosterURL;

    public boolean hasHeader() {
        return hasHeader;
    }

    @Override
    public String toString() {
        return "StoryElementSubTypeMetaData{" +
                "tweetUrl='" + tweetUrl + '\'' +
                ", tweetId='" + tweetId + '\'' +
                ", attribution='" + attribution + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", videoId='" + videoId + '\'' +
                ", hasHeader='" + hasHeader + '\'' +
                ", linkedStory='" + linkedStory + '\'' +
                ", linkedStoryId='" + linkedStoryId + '\'' +
                ", mBrightCoveAccountID='" + mBrightCoveAccountID + '\'' +
                ", mBrightCovePosterURL='" + mBrightCovePosterURL + '\'' +
                '}';
    }


    /**
     * Tweet Url
     *
     * @return String tweet url
     */
    public String tweetUrl() {
        return tweetUrl;
    }

    /**
     * Tweet Url
     *
     * @return String video Id
     */
    public String videoId() {
        return videoId;
    }

    /**
     * Tweet Id
     *
     * @return String tweet id
     */
    public String tweetId() {
        return tweetId;
    }

    /**
     * @return String attribution
     */
    public String attribution() {
        return attribution;
    }

    /**
     * @return String content
     */
    public String content() {
        return content;
    }

    /**
     * Type of SubElement
     *
     * @return String {@link Type}
     */
//    public
//    @Type
//    String type() {
//        if (TextUtils.isEmpty(type)) {
//            return TYPE_INVALID;
//        }
//
//        if (isTypeSlideShow()) {
//            return TYPE_SLIDESHOW;
//        } else if (isTypeGallery()) {
//            return TYPE_GALLERY;
//        }
//        return TYPE_INVALID;
//    }

    /**
     * Check if SubElement is of type gallery
     *
     * @return true if the element is gallery else false
     */
//    public boolean isTypeGallery() {
//        return StringUtils.equalsIgnoreCase(type, TYPE_GALLERY);
//    }
//
//    /**
//     * Check if SubElement is of type slide show
//     *
//     * @return true is the element is slideshow else false
//     */
//    public boolean isTypeSlideShow() {
//        return StringUtils.equalsIgnoreCase(type, TYPE_SLIDESHOW);
//    }

    public String question() {
        return question;
    }

    public String answer() {
        return answer;
    }

    public StoryElementSubTypeMetaData() {
    }

    public String getmBrightCoveAccountID() {
        return mBrightCoveAccountID;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getLinkedStoryId() {
        return linkedStoryId;
    }


    public String getmBrightCovePosterURL() {
        return mBrightCovePosterURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tweetUrl);
        dest.writeString(this.tweetId);
        dest.writeString(this.attribution);
        dest.writeString(this.content);
        dest.writeString(this.type);
        dest.writeString(this.question);
        dest.writeString(this.answer);
        dest.writeString(this.videoId);
        dest.writeByte(hasHeader ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.linkedStory, 0);
        dest.writeString(this.linkedStoryId);
        dest.writeString(this.mBrightCoveAccountID);
        dest.writeString(this.mBrightCovePosterURL);
    }

    protected StoryElementSubTypeMetaData(Parcel in) {
        this.tweetUrl = in.readString();
        this.tweetId = in.readString();
        this.attribution = in.readString();
        this.content = in.readString();
        this.type = in.readString();
        this.question = in.readString();
        this.answer = in.readString();
        this.videoId = in.readString();
        this.hasHeader = in.readByte() != 0;
        this.linkedStory = in.readParcelable(LinkedStory.class.getClassLoader());
        this.linkedStoryId = in.readString();
        this.mBrightCoveAccountID = in.readString();
        this.mBrightCovePosterURL = in.readString();
    }

    public static final Creator<StoryElementSubTypeMetaData> CREATOR = new Creator<StoryElementSubTypeMetaData>() {
        public StoryElementSubTypeMetaData createFromParcel(Parcel source) {
            return new StoryElementSubTypeMetaData(source);
        }

        public StoryElementSubTypeMetaData[] newArray(int size) {
            return new StoryElementSubTypeMetaData[size];
        }
    };
}
