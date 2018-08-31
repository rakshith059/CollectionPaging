package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

/**
 * An object instance representing StoryMetaData
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
public class StoryMetaData implements Parcelable {

    @SerializedName("is-closed")
    public boolean isClosed;
    @SerializedName("review-title")
    public String reviewTitle;
    @SerializedName("review-rating")
    public ExtraData reviewRating;
    @SerializedName("story-attributes")
    public JsonObject storyAttributes;
    @SerializedName("reference-url")
    public String referenceUrl;
    @SerializedName("sponsored-by")
    public String sponsoredBy;

    public static final Creator<StoryMetaData> CREATOR = new Creator<StoryMetaData>() {
        @Override
        public StoryMetaData createFromParcel(Parcel in) {
            return new StoryMetaData(in);
        }

        @Override
        public StoryMetaData[] newArray(int size) {
            return new StoryMetaData[size];
        }
    };

    @Override
    public String toString() {
        return "StoryMetaData{" +
                "isClosed=" + isClosed +
                "reviewTitle=" + reviewTitle +
                "reviewRating=" + reviewRating +
                "storyAttributes=" + storyAttributes +
                "sponsoredBy=" + sponsoredBy +
                '}';
    }

    /**
     * Check if Story is closed
     *
     * @return boolean true is closed else false
     */
    public boolean isClosed() {
        return isClosed;
    }

    public StoryMetaData() {
    }


    public ExtraData reviewRating() {
        return reviewRating;
    }

    public String reviewTitle() {
        return reviewTitle;
    }

    public JsonObject storyAttributes() {
        return storyAttributes;
    }

    public String referenceUrl() {
        return referenceUrl;
    }

    public String getSponsoredBy() {
        return sponsoredBy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isClosed ? (byte) 1 : (byte) 0);
        dest.writeString(this.reviewTitle);
        dest.writeParcelable(this.reviewRating, flags);
        dest.writeByte((byte) (storyAttributes == null ? 0 : 1));
        if (storyAttributes != null)
            dest.writeString(this.storyAttributes.toString());
        dest.writeString(this.referenceUrl);
        dest.writeString(this.sponsoredBy);
    }

    protected StoryMetaData(Parcel in) {
        this.isClosed = in.readByte() != 0;
        this.reviewTitle = in.readString();
        this.reviewRating = in.readParcelable(ExtraData.class.getClassLoader());
        if (in.readByte() != 0) {
            this.storyAttributes = (JsonObject) new JsonParser().parse(in.readString());
        }
        this.referenceUrl = in.readString();
        this.sponsoredBy = in.readString();
    }
}
