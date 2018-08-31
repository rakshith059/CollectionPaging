package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rakshith on 9/26/17.
 */
public class LinkedStory implements Parcelable {
    @SerializedName("headline")
    private
    String mHeadline;
    @SerializedName("story-content-id")
    private
    String storyContentId;
    @SerializedName("id")
    private
    String mStoryId;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getmHeadline() {
        return mHeadline;
    }

    public void setmHeadline(String mHeadline) {
        this.mHeadline = mHeadline;
    }

    public String getStoryContentId() {
        return storyContentId;
    }

    public void setStoryContentId(String storyContentId) {
        this.storyContentId = storyContentId;
    }

    public String getmStoryId() {
        return mStoryId;
    }

    public void setmStoryId(String mStoryId) {
        this.mStoryId = mStoryId;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mHeadline);
        parcel.writeString(this.storyContentId);
        parcel.writeString(this.mStoryId);
    }

    public LinkedStory(Parcel in) {
        this.mHeadline = in.readString();
        this.storyContentId = in.readString();
        this.mStoryId = in.readString();
    }

    @Override
    public String toString() {
        return "LinkedStory{" +
                "mHeadline='" + mHeadline + '\'' +
                ", storyContentId='" + storyContentId + '\'' +
                ", mStoryId='" + mStoryId + '\'' +
                '}';
    }

    public static final Creator<LinkedStory> CREATOR = new Creator<LinkedStory>() {
        @Override
        public LinkedStory createFromParcel(Parcel in) {
            return new LinkedStory(in);
        }

        @Override
        public LinkedStory[] newArray(int size) {
            return new LinkedStory[size];
        }
    };

    public static Creator<LinkedStory> getCREATOR() {
        return CREATOR;
    }

}
