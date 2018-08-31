package quintype.com.templatecollectionwithrx.models.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaykoul on 18/04/16.
 */
public class CoverImage implements Parcelable {

    @SerializedName("cover-image-url")
    @Expose
    private String coverImageUrl;
    @SerializedName("cover-image-metadata")
    @Expose
    private CoverImageMetadata coverImageMetadata;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("cover-image-s3-key")
    @Expose
    private String coverImageS3Key;

    public static final Creator<CoverImage> CREATOR = new Creator<CoverImage>() {
        @Override
        public CoverImage createFromParcel(Parcel in) {
            return new CoverImage(in);
        }

        @Override
        public CoverImage[] newArray(int size) {
            return new CoverImage[size];
        }
    };

    public String coverImageUrl() {
        return coverImageUrl;
    }

    public CoverImageMetadata coverImageMetadata() {
        return coverImageMetadata;
    }

    public String caption() {
        return caption;
    }

    public String coverImageS3Key() {
        return coverImageS3Key;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.coverImageUrl);
        dest.writeParcelable(this.coverImageMetadata, flags);
        dest.writeString(this.caption);
        dest.writeString(this.coverImageS3Key);
    }

    public CoverImage() {
    }

    protected CoverImage(Parcel in) {
        this.coverImageUrl = in.readString();
        this.coverImageMetadata = in.readParcelable(CoverImageMetadata.class.getClassLoader());
        this.caption = in.readString();
        this.coverImageS3Key = in.readString();
    }
}
