package quintype.com.templatecollectionwithrx.models.sections;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Section implements Parcelable {

    public static final Section HOME = new Section();

    static {
        HOME.name = "home";
        HOME.id = "-1";
    }

    @SerializedName("name")
    public String name;
    @SerializedName("id")
    public String id;
    @SerializedName("display-name")
    public String displayName;
    @SerializedName("trending-stories-story-order")
    public ArrayList<String> trendingStories = new ArrayList<>();
    @SerializedName("featured-stories-story-order")
    public ArrayList<String> featuredStories = new ArrayList<>();
    @SerializedName("updated-at")
    public long updateAt;
    @SerializedName("story-order")
    public ArrayList<String> stories = new ArrayList<>();
    @SerializedName("publisher-id")
    public String publisherId;

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", trendingStories=" + trendingStories +
                ", featuredStories=" + featuredStories +
                ", updatedAt=" + updateAt +
                ", stories=" + stories +
                ", publisherId='" + publisherId + '\'' +
                '}';
    }

    public String key() {
        return "section#" + name.hashCode();
    }

    public String name() {
        return name;
    }

    public String id() {
        return id;
    }

    public String displayName() {
        return displayName;
    }

    public ArrayList<String> trendingStories() {
        return trendingStories;
    }

    public ArrayList<String> featuredStories() {
        return featuredStories;
    }

    public long updatedAt() {
        return updateAt;
    }

    public ArrayList<String> stories() {
        return stories;
    }

    public String publisherId() {
        return publisherId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.displayName);
        dest.writeStringList(this.trendingStories);
        dest.writeStringList(this.featuredStories);
        dest.writeLong(this.updateAt);
        dest.writeStringList(this.stories);
        dest.writeString(this.publisherId);
    }

    public Section() {
    }

    public Section(String name) {
        this.name = name;
    }

    protected Section(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.displayName = in.readString();
        this.trendingStories = in.createStringArrayList();
        this.featuredStories = in.createStringArrayList();
        this.updateAt = in.readLong();
        this.stories = in.createStringArrayList();
        this.publisherId = in.readString();
    }

    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        public Section createFromParcel(Parcel source) {
            return new Section(source);
        }

        public Section[] newArray(int size) {
            return new Section[size];
        }
    };

    public static  Section createSection(SectionMeta sectionMeta){
        Section section = new Section(sectionMeta.name());
        section.id(sectionMeta.id());
        section.displayName(sectionMeta.displayName());
        return section;
    }

    public void name(String name) {
        this.name = name;
    }

    public void id(String id) {
        this.id = id;
    }

    public void displayName(String displayName) {
        this.displayName = displayName;
    }

    public void trendingStories(ArrayList<String> trendingStories) {
        this.trendingStories = trendingStories;
    }

    public void featuredStories(ArrayList<String> featuredStories) {
        this.featuredStories = featuredStories;
    }

    public void updateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public void stories(ArrayList<String> stories) {
        this.stories = stories;
    }

    public void publisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public static boolean isHome(Section section) {
        return section.equals(Section.HOME);
    }
}