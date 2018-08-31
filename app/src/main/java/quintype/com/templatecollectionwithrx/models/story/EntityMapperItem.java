package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Just a pojo to hold the id/name mapper items for entities
 * Created by ishitasinha on 11/07/17.
 */

public class EntityMapperItem implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "EntityMapperItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    public EntityMapperItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public EntityMapperItem() {
    }

    protected EntityMapperItem(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public static final Creator<EntityMapperItem> CREATOR = new Creator<EntityMapperItem>() {
        @Override
        public EntityMapperItem createFromParcel(Parcel source) {
            return new EntityMapperItem(source);
        }

        @Override
        public EntityMapperItem[] newArray(int size) {
            return new EntityMapperItem[size];
        }
    };
}
