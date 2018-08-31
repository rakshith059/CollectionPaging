package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alternative implements Parcelable {

    @SerializedName("home")
    @Expose
    private Home home;

    public static final Creator<Alternative> CREATOR = new Creator<Alternative>() {
        @Override
        public Alternative createFromParcel(Parcel in) {
            return new Alternative(in);
        }

        @Override
        public Alternative[] newArray(int size) {
            return new Alternative[size];
        }
    };

    /**
     * @return The home
     */
    public Home getHome() {
        return home;
    }

    /**
     * @param home The home
     */
    public void setHome(Home home) {
        this.home = home;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.home, flags);
    }

    public Alternative() {
    }

    protected Alternative(Parcel in) {
        this.home = in.readParcelable(Home.class.getClassLoader());
    }

}