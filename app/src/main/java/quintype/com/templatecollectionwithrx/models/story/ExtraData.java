package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaykoul on 29/06/16.
 */
public class ExtraData implements Parcelable {
    @SerializedName("value")
    private String value;
    @SerializedName("label")
    private String label;

    public String value() {
        return value;
    }

    public String label() {
        return label;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value);
        dest.writeString(this.label);
    }

    public ExtraData() {
    }

    protected ExtraData(Parcel in) {
        this.value = in.readString();
        this.label = in.readString();
    }

    public static final Creator<ExtraData> CREATOR = new Creator<ExtraData>
            () {
        @Override
        public ExtraData createFromParcel(Parcel source) {
            return new ExtraData(source);
        }

        @Override
        public ExtraData[] newArray(int size) {
            return new ExtraData[size];
        }
    };

    @Override
    public String toString() {
        return "ExtraData{" +
                "label=" + label +
                "value=" + value +
                '}';
    }
}
