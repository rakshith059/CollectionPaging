package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ishitasinha on 20/06/17.
 */

public class StoryElementData implements Parcelable {

    @SerializedName("content")
    String content;
    @SerializedName("content-type")
    String contentType;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.contentType);
    }

    public StoryElementData() {
    }

    protected StoryElementData(Parcel in) {
        this.content = in.readString();
        this.contentType = in.readString();
    }

    public static final Creator<StoryElementData> CREATOR = new Creator<StoryElementData>() {
        @Override
        public StoryElementData createFromParcel(Parcel source) {
            return new StoryElementData(source);
        }

        @Override
        public StoryElementData[] newArray(int size) {
            return new StoryElementData[size];
        }
    };

    public String content() {
        return content;
    }

    public String contentType() {
        return contentType;
    }
}
