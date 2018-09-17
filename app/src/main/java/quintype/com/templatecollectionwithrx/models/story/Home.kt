package quintype.com.templatecollectionwithrx.models.story

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created TemplateCollectionWithRx by rakshith on 9/4/18.
 */

class Home : Parcelable {

    /**
     *
     * @return
     * The _default
     */
    /**
     *
     * @param _default
     * The default
     */
    @SerializedName("default")
    @Expose
    var default: Default? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.default, flags)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.default = `in`.readParcelable(Default::class.java.classLoader)
    }

    companion object CREATOR : Parcelable.Creator<Home> {
        override fun createFromParcel(source: Parcel): Home {
            return Home(source)
        }

        override fun newArray(size: Int): Array<Home?> {
            return arrayOfNulls(size)
        }
    }
}