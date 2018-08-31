package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

/**
 * Wrapper class for card attributes
 * <p>
 * Created by ishitasinha on 11/07/17.
 */

public class CardMetadata implements Parcelable {

    @SerializedName("attributes")
    private JsonObject attributes;

    public CardMetadata() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (attributes == null ? 0 : 1));
        if (attributes != null)
            dest.writeString(this.attributes.toString());
    }

    protected CardMetadata(Parcel in) {
        if (in.readByte() != 0) {
            this.attributes = (JsonObject) new JsonParser().parse(in.readString());
        }
    }

    public JsonObject attributes() {
        return this.attributes;
    }

    public static final Creator<CardMetadata> CREATOR = new Creator<CardMetadata>() {
        @Override
        public CardMetadata createFromParcel(Parcel source) {
            return new CardMetadata(source);
        }

        @Override
        public CardMetadata[] newArray(int size) {
            return new CardMetadata[size];
        }
    };
}
