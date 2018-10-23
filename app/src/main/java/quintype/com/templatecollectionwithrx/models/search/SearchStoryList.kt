package quintype.com.templatecollectionwithrx.models.search

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SearchStoryList() : Parcelable {
    @SerializedName("results")
    @Expose
    private var results: SearchStoriesResults? = null

    constructor(parcel: Parcel) : this() {
    }

    fun getResults(): SearchStoriesResults? {
        return results
    }

    fun setResults(results: SearchStoriesResults) {
        this.results = results
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchStoryList> {
        override fun createFromParcel(parcel: Parcel): SearchStoryList {
            return SearchStoryList(parcel)
        }

        override fun newArray(size: Int): Array<SearchStoryList?> {
            return arrayOfNulls(size)
        }
    }
}