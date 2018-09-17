package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class StorySearchResult() : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.term)
        dest?.writeInt(this.from)
        dest?.writeInt(this.size)
        dest?.writeInt(this.total)
        dest?.writeTypedList(stories)
    }

    override fun describeContents(): Int {
        return 0
    }

    @SerializedName("term")
    private var term: String? = null
    @SerializedName("from")
    private var from: Int = 0
    @SerializedName("size")
    private var size: Int = 0
    @SerializedName("total")
    private var total: Int = 0
    @SerializedName("stories")
    private var stories = emptyList<Story>()

    constructor(parcel: Parcel) : this() {
        this.term = parcel.readString()
        this.from = parcel.readInt()
        this.size = parcel.readInt()
        this.total = parcel.readInt()
        this.stories = parcel.createTypedArrayList(Story.CREATOR)
    }

    /**
     * An object instance representing SearchResults
     *
     * @author Madhu madhu@quintype.com
     */
    class Results {
        @SerializedName("results")
        private val result: StorySearchResult? = null

        fun result(): StorySearchResult? {
            return result
        }

        override fun toString(): String {
            return "SearchResults{" +
                    "result=" + result +
                    '}'.toString()
        }
    }


    /**
     * @return List of stories found by search [.term]
     */
    fun stories(): List<Story> {
        return Collections.unmodifiableList(stories)
    }

    override fun toString(): String {
        return "SearchResult{" +
                "term=" + term +
                ", from=" + from +
                ", size=" + size +
                ", total=" + total +
                ", stories=" + stories +
                '}'.toString()
    }

    companion object CREATOR : Parcelable.Creator<StorySearchResult> {
        override fun createFromParcel(parcel: Parcel): StorySearchResult {
            return StorySearchResult(parcel)
        }

        override fun newArray(size: Int): Array<StorySearchResult?> {
            return arrayOfNulls(size)
        }
    }
}
