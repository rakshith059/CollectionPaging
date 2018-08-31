package quintype.com.templatecollectionwithrx.models.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CollectionMeta implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("slug")
    private String slug;
    @SerializedName("name")
    private String name;

    public String id() {
        return id;
    }

    public void id(String id) {
        this.id = id;
    }

    public String slug() {
        return slug;
    }

    public void slug(String slug) {
        this.slug = slug;
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.slug);
        dest.writeString(this.name);
    }

    public CollectionMeta() {
    }

    protected CollectionMeta(Parcel in) {
        this.id = in.readString();
        this.slug = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CollectionMeta> CREATOR = new Parcelable
            .Creator<CollectionMeta>() {
        @Override
        public CollectionMeta createFromParcel(Parcel source) {
            return new CollectionMeta(source);
        }

        @Override
        public CollectionMeta[] newArray(int size) {
            return new CollectionMeta[size];
        }
    };
}
