package quintype.com.templatecollectionwithrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import quintype.com.templatecollectionwithrx.models.sections.Section;
import quintype.com.templatecollectionwithrx.models.story.Tag;

/**
 * Created by Madhu on 22/07/15.
 */
public class NavMenu implements Parcelable {
    public static final String TYPE_SECTION = "section";
    public static final String TYPE_TAG = "tag";
    public static final String TYPE_LINK = "link";
    public static final NavMenu HOME = new NavMenu();
    private static final String homeType = "home";

    static {
        HOME.id = "-149";
    }

    @SerializedName("updated-at")
    private long updatedAt;
    @SerializedName("tag-name")
    private String tagName;
    @SerializedName("publisher-id")
    private String publisherId;
    @SerializedName("item-id")
    private String itemId;
    @SerializedName("rank")
    private long rank;
    @SerializedName("title")
    private String title;
    @SerializedName("item-type")
    private String type;
    @SerializedName("section-slug")
    private String sectionSlug;
    @SerializedName("id")
    private String id;
    @SerializedName("parent-id")
    private String parentId;
    @SerializedName("created-at")
    private long createdAt;
    @SerializedName("section-name")
    private String sectionName;
    @SerializedName("data")
    private NavMenuData data;

    public long updatedAt() {
        return updatedAt;
    }

    public String tagName() {
        return tagName;
    }

    public String publisherId() {
        return publisherId;
    }

    public String itemId() {
        return itemId;
    }

    public long rank() {
        return rank;
    }

    public String title() {
        return title;
    }

    public String type() {
        return type;
    }

    public String sectionSlug() {
        return sectionSlug;
    }

    public String id() {
        return id;
    }

    public String parentId() {
        return parentId;
    }

    public long createdAt() {
        return createdAt;
    }

    public String sectionName() {
        return sectionName;
    }

    public NavMenuData data() {
        return data;
    }

    @Override
    public String toString() {
        return "NavMenu{" +
                "updatedAt=" + updatedAt +
                ", tagName='" + tagName + '\'' +
                ", publisherId='" + publisherId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", rank=" + rank +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", sectionSlug='" + sectionSlug + '\'' +
                ", id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", sectionName='" + sectionName + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * @return true if type is section, false otherwise
     */
    public boolean isTypeSection() {
        return type.equalsIgnoreCase(TYPE_SECTION);
    }

    /**
     * @return true if type is tag, false otherwise
     */
    public boolean isTypeTag() {
        return type.equalsIgnoreCase(TYPE_TAG);
    }

    /**
     * @return true if type is link, false otherwise
     */
    public boolean isTypeLink() {
        return type.equalsIgnoreCase(TYPE_LINK);
    }

    /**
     * @return display name to be used in UI
     */
    public String displayName() {
        if (TextUtils.isEmpty(title)) {
            if (isTypeSection() && !TextUtils.isEmpty(sectionName))
                return sectionName;
            else if (isTypeTag() && !TextUtils.isEmpty(tagName))
                return tagName;
        }
        return title;
    }

    /**
     * @return build a section instance
     */

    public Section section() {
        if (HOME.id.equalsIgnoreCase(id)) {
            return new Section(homeType);
        } else if (!TextUtils.isEmpty(sectionName)) {
            return new Section(sectionName);
        } else return null;

    }

    /**
     * @return an instance of tag
     */
    public Tag tag() {
        if (!TextUtils.isEmpty(tagName))
            return new Tag(tagName);
        else return null;
    }

    /**
     * update Title for the navMenu
     *
     * @param title
     */
    public void title(String title) {
        this.title = title;
    }

    /**
     * update id for the navMenu
     *
     * @param id
     */
    public void id(String id) {
        this.id = id;
    }

    /**
     * update section Name for the navMenu
     *
     * @param sectionName
     */
    public void sectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * update tag Name for the navMenu
     *
     * @param tagName
     */
    public void tagName(String tagName) {
        this.tagName = tagName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.updatedAt);
        dest.writeString(this.tagName);
        dest.writeString(this.publisherId);
        dest.writeString(this.itemId);
        dest.writeLong(this.rank);
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.sectionSlug);
        dest.writeString(this.id);
        dest.writeString(this.parentId);
        dest.writeLong(this.createdAt);
        dest.writeString(this.sectionName);
        dest.writeParcelable(this.data, flags);
    }

    public NavMenu() {
    }

    protected NavMenu(Parcel in) {
        this.updatedAt = in.readLong();
        this.tagName = in.readString();
        this.publisherId = in.readString();
        this.itemId = in.readString();
        this.rank = in.readLong();
        this.title = in.readString();
        this.type = in.readString();
        this.sectionSlug = in.readString();
        this.id = in.readString();
        this.parentId = in.readString();
        this.createdAt = in.readLong();
        this.sectionName = in.readString();
        this.data = in.readParcelable(NavMenuData.class.getClassLoader());
    }

    public static final Creator<NavMenu> CREATOR = new Creator<NavMenu>() {
        @Override
        public NavMenu createFromParcel(Parcel source) {
            return new NavMenu(source);
        }

        @Override
        public NavMenu[] newArray(int size) {
            return new NavMenu[size];
        }
    };
}
