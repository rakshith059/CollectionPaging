package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * An object instance representing Id based story
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
//public class IdStory implements Parcelable, DataOrigin {
//
//    @SerializedName("story")
//    private Story story;
//    @SerializedName("local-source")
//    private DataOrigin.Source source = DataOrigin.Source.NETWORK;
//
//
//    @Override
//    public String toString() {
//        return "IdStory{" +
//                "story=" + story +
//                '}';
//    }
//
//    /**
//     * @return {@link Story}
//     */
//    public Story story() {
//        return story;
//    }
//
//
//    @Override
//    public Source getSource() {
//        return source;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeParcelable(this.story, 0);
//        dest.writeString(this.source.name());
//    }
//
//    public IdStory() {
//    }
//
//    public IdStory(Story story, Source source) {
//        if (source == null) {
//            throw new IllegalArgumentException("Source cannot be null");
//        }
//        this.source = source;
//        this.story = story;
//    }
//
//    protected IdStory(Parcel in) {
//        this.story = in.readParcelable(Story.class.getClassLoader());
//        String sourceString = in.readString();
//        this.source = DataOrigin.Source.valueOf(sourceString);
//    }
//
//    public static final Creator<IdStory> CREATOR = new Creator<IdStory>() {
//        public IdStory createFromParcel(Parcel source) {
//            return new IdStory(source);
//        }
//
//        public IdStory[] newArray(int size) {
//            return new IdStory[size];
//        }
//    };
//}
