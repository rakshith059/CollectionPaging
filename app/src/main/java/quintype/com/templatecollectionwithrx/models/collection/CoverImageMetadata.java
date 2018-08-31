package quintype.com.templatecollectionwithrx.models.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaykoul on 18/04/16.
 */
public class CoverImageMetadata implements Parcelable {
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("mime-type")
    @Expose
    private String mimeType;

    public static final Creator<CoverImageMetadata> CREATOR = new Creator<CoverImageMetadata>() {
        @Override
        public CoverImageMetadata createFromParcel(Parcel in) {
            return new CoverImageMetadata(in);
        }

        @Override
        public CoverImageMetadata[] newArray(int size) {
            return new CoverImageMetadata[size];
        }
    };

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public String mimeType() {
        return mimeType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.mimeType);
    }

    public CoverImageMetadata() {
    }

    protected CoverImageMetadata(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.mimeType = in.readString();
    }
}
