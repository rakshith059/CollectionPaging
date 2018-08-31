package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HeroImageMetadata implements Parcelable {

    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("mime-type")
    @Expose
    private String mimeType;
    @SerializedName("focus-point")
    @Expose
    private List<Integer> focusPoint = null;

    public static final Creator<HeroImageMetadata> CREATOR = new Creator<HeroImageMetadata>() {
        @Override
        public HeroImageMetadata createFromParcel(Parcel in) {
            return new HeroImageMetadata(in);
        }

        @Override
        public HeroImageMetadata[] newArray(int size) {
            return new HeroImageMetadata[size];
        }
    };

    /**
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return The mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType The mime-type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return The focusPoint
     */
    public List<Integer> getFocusPoint() {
        return focusPoint;
    }

    /**
     * @param focusPoint The focus-point
     */
    public void setFocusPoint(List<Integer> focusPoint) {
        this.focusPoint = focusPoint;
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
        dest.writeList(this.focusPoint);
    }

    public HeroImageMetadata() {
    }

    protected HeroImageMetadata(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.mimeType = in.readString();
        this.focusPoint = new ArrayList<Integer>();
        in.readList(this.focusPoint, Integer.class.getClassLoader());
    }
}