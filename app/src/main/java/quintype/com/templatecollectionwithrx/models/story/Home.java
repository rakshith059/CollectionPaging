package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home implements Parcelable {

@SerializedName("default")
@Expose
private Default _default;

/**
* 
* @return
* The _default
*/
public Default getDefault() {
return _default;
}

/**
* 
* @param _default
* The default
*/
public void setDefault(Default _default) {
this._default = _default;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this._default, flags);
    }

    public Home() {
    }

    protected Home(Parcel in) {
        this._default = in.readParcelable(Default.class.getClassLoader());
    }

    public static final Creator<Home> CREATOR = new Creator<Home>() {
        @Override
        public Home createFromParcel(Parcel source) {
            return new Home(source);
        }

        @Override
        public Home[] newArray(int size) {
            return new Home[size];
        }
    };
}