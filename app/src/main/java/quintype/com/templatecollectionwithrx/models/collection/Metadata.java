package quintype.com.templatecollectionwithrx.models.collection;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import quintype.com.templatecollectionwithrx.models.sections.Section;
import quintype.com.templatecollectionwithrx.models.story.Tag;

public class Metadata implements Parcelable {

    @SerializedName("cover-image")
    @Expose
    private CoverImage coverImage;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = new ArrayList<Tag>();
    @SerializedName("snapshot")
    @Expose
    private
    CollectionSnapshot snapshot;
    /*For TheQuint publisher, if the collectionTemplate is 'big-story' then the key will be 'sections'*/
    @SerializedName("sections")
    @Expose
    private List<Section> sections;
    /*For TheQuint publisher, if the collectionTemplate is not 'big-story' then the key will be 'section'*/
    @SerializedName("section")
    @Expose
    private List<Section> section;

    public CollectionSnapshot snapshot() {
        return snapshot;
    }

    public CoverImage coverImage() {
        return coverImage;
    }

    public List<Tag> tags() {
        return tags;
    }

    public List<Section> getSection() {
        return section;
    }

    public List<Section> getSections() {
        return sections;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.coverImage, flags);
        dest.writeTypedList(this.tags);
        dest.writeParcelable(this.snapshot, flags);
        dest.writeTypedList(this.sections);
        dest.writeTypedList(this.section);
    }

    public Metadata() {
    }

    protected Metadata(Parcel in) {
        this.coverImage = in.readParcelable(CoverImage.class.getClassLoader());
        this.tags = in.createTypedArrayList(Tag.CREATOR);
        this.snapshot = in.readParcelable(CollectionSnapshot.class.getClassLoader());
        this.sections = in.createTypedArrayList(Section.CREATOR);
        this.section = in.createTypedArrayList(Section.CREATOR);
    }

    public static final Creator<Metadata> CREATOR = new Creator<Metadata>() {
        @Override
        public Metadata createFromParcel(Parcel source) {
            return new Metadata(source);
        }

        @Override
        public Metadata[] newArray(int size) {
            return new Metadata[size];
        }
    };
}