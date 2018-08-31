package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * An object instance representing Story Tag
 *
 * @author Imran imran@quintype.com
 * @author Madhu madhu@quintype.com
 */
public class Tag implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;

    /**
     * Key for tag
     *
     * @return String key
     */
    public String key() {
        return "tag#" + name.hashCode();
    }

    /**
     * Name of tag
     *
     * @return String name
     */
    public String name() {
        return name;
    }

    /**
     * Id for tag
     *
     * @return String id
     */
    public String id() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
    }

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    protected Tag(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
    }

    public static final Creator<Tag> CREATOR = new Creator<Tag>() {
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };
}
