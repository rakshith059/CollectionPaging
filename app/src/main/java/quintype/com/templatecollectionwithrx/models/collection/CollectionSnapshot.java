package quintype.com.templatecollectionwithrx.models.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rakshith on 2/18/17.
 */
public class CollectionSnapshot implements Parcelable {
    @SerializedName("body")
    @Expose
    private
    String snapshotBody;

    protected CollectionSnapshot(Parcel in) {
        setSnapshotBody(in.readString());
    }

    public static final Creator<CollectionSnapshot> CREATOR = new Creator<CollectionSnapshot>() {
        @Override
        public CollectionSnapshot createFromParcel(Parcel in) {
            return new CollectionSnapshot(in);
        }

        @Override
        public CollectionSnapshot[] newArray(int size) {
            return new CollectionSnapshot[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(snapshotBody());
    }

    public String snapshotBody() {
        return snapshotBody;
    }

    public void setSnapshotBody(String snapshotBody) {
        this.snapshotBody = snapshotBody;
    }
}
