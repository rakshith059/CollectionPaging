package quintype.com.templatecollectionwithrx.models

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils

import com.google.gson.annotations.SerializedName
import quintype.com.templatecollectionwithrx.models.sections.Section
import quintype.com.templatecollectionwithrx.models.story.Tag


class NavMenu : Parcelable {

    @SerializedName("updated-at")
    private var updatedAt: Long = 0
    @SerializedName("tag-name")
    private var tagName: String? = null
    @SerializedName("publisher-id")
    private var publisherId: String = ""
    @SerializedName("item-id")
    private var itemId: String = ""
    @SerializedName("rank")
    private var rank: Long = 0
    @SerializedName("title")
    private var title: String? = null
    @SerializedName("item-type")
    private var type: String = ""
    @SerializedName("section-slug")
    private var sectionSlug: String = ""
    @SerializedName("id")
    private var id: String? = null
    @SerializedName("parent-id")
    private var parentId: String = ""
    @SerializedName("created-at")
    private var createdAt: Long = 0
    @SerializedName("section-name")
    private var sectionName: String? = null
    @SerializedName("data")
    private var data: NavMenuData? = null

    private val homeType: String = "home"

    /**
     * @return true if type is section, false otherwise
     */
    fun isTypeSection(): Boolean {
        return type.equals(TYPE_SECTION, ignoreCase = true)
    }

    /**
     * @return true if type is tag, false otherwise
     */
    fun isTypeTag(): Boolean {
        return type.equals(TYPE_TAG, ignoreCase = true)
    }

    /**
     * @return true if type is link, false otherwise
     */
    fun isTypeLink(): Boolean {
        return type.equals(TYPE_LINK, ignoreCase = true)
    }

    fun updatedAt(): Long {
        return updatedAt
    }

    fun tagName(): String? {
        return tagName
    }

    fun publisherId(): String {
        return publisherId
    }

    fun itemId(): String {
        return itemId
    }

    fun rank(): Long {
        return rank
    }

    fun title(): String? {
        return title
    }

    fun type(): String {
        return type
    }

    fun sectionSlug(): String {
        return sectionSlug
    }

    fun id(): String? {
        return id
    }

    fun parentId(): String {
        return parentId
    }

    fun createdAt(): Long {
        return createdAt
    }

    fun sectionName(): String? {
        return sectionName
    }

    fun data(): NavMenuData? {
        return data
    }

    override fun toString(): String {
        return "NavMenu{" +
                "updatedAt=" + updatedAt +
                ", tagName='" + tagName + '\''.toString() +
                ", publisherId='" + publisherId + '\''.toString() +
                ", itemId='" + itemId + '\''.toString() +
                ", rank=" + rank +
                ", title='" + title + '\''.toString() +
                ", type='" + type + '\''.toString() +
                ", sectionSlug='" + sectionSlug + '\''.toString() +
                ", id='" + id + '\''.toString() +
                ", createdAt=" + createdAt +
                ", sectionName='" + sectionName + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }

    /**
     * @return display name to be used in UI
     */
    fun displayName(): String? {
        if (TextUtils.isEmpty(title)) {
            if (isTypeSection() && !TextUtils.isEmpty(sectionName))
                return sectionName
            else if (isTypeTag() && !TextUtils.isEmpty(tagName))
                return tagName
        }
        return title
    }

    /**
     * @return build a section instance
     */

    fun section(): Section {
        return if (HOME.id.equals(id, ignoreCase = true)) {
            Section(homeType)
        } else {
            Section(sectionName as String)
        }
    }

    /**
     * @return an instance of tag
     */
    fun tag(): Tag {
        return Tag(tagName as String)
    }

    /**
     * update Title for the navMenu
     *
     * @param title
     */
    fun title(title: String) {
        this.title = title
    }

    /**
     * update id for the navMenu
     *
     * @param id
     */
    fun id(id: String) {
        this.id = id
    }

    /**
     * update section Name for the navMenu
     *
     * @param sectionName
     */
    fun sectionName(sectionName: String) {
        this.sectionName = sectionName
    }

    /**
     * update tag Name for the navMenu
     *
     * @param tagName
     */
    fun tagName(tagName: String) {
        this.tagName = tagName
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this.updatedAt)
        dest.writeString(this.tagName)
        dest.writeString(this.publisherId)
        dest.writeString(this.itemId)
        dest.writeLong(this.rank)
        dest.writeString(this.title)
        dest.writeString(this.type)
        dest.writeString(this.sectionSlug)
        dest.writeString(this.id)
        dest.writeString(this.parentId)
        dest.writeLong(this.createdAt)
        dest.writeString(this.sectionName)
        dest.writeParcelable(this.data, flags)
    }

    constructor()

    protected constructor(`in`: Parcel) {
        this.updatedAt = `in`.readLong()
        this.tagName = `in`.readString()
        this.publisherId = `in`.readString()
        this.itemId = `in`.readString()
        this.rank = `in`.readLong()
        this.title = `in`.readString()
        this.type = `in`.readString()
        this.sectionSlug = `in`.readString()
        this.id = `in`.readString()
        this.parentId = `in`.readString()
        this.createdAt = `in`.readLong()
        this.sectionName = `in`.readString()
        this.data = `in`.readParcelable<NavMenuData>(NavMenuData::class.java.classLoader)
    }

    companion object {
        val TYPE_SECTION = "section"
        val TYPE_TAG = "tag"
        val TYPE_LINK = "link"
        val HOME = NavMenu()

        init {
            HOME.id = "-149"
        }

        /**
         * @param navMenu nav menu instance
         * @return whether this nav menu is home
         */
        fun isHome(navMenu: NavMenu): Boolean {
            return HOME.id.equals(navMenu.id, ignoreCase = true)
        }

        val CREATOR: Parcelable.Creator<NavMenu> = object : Parcelable.Creator<NavMenu> {
            override fun createFromParcel(source: Parcel): NavMenu {
                return NavMenu(source)
            }

            override fun newArray(size: Int): Array<NavMenu?> {
                return arrayOfNulls(size)
            }
        }
    }
}
