package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * An object instance representing SlugStory
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
public class SlugStory implements Parcelable {

    @SerializedName("story")
    private Story story;

    @Override
    public String toString() {
        return "SlugStory{" +
                "story=" + story +
                '}';
    }

    /**
     * @return Slug {@link Story}
     */
    public Story story() {
        return story;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.story, 0);
    }

    public SlugStory() {
    }

    protected SlugStory(Parcel in) {
        this.story = in.readParcelable(Story.class.getClassLoader());
    }

    public static final Creator<SlugStory> CREATOR = new Creator<SlugStory>() {
        public SlugStory createFromParcel(Parcel source) {
            return new SlugStory(source);
        }

        public SlugStory[] newArray(int size) {
            return new SlugStory[size];
        }
    };
}
