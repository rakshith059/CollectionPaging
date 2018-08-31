package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Default implements Parcelable {

@SerializedName("hero-image")
@Expose
private HeroImage heroImage;
@SerializedName("headline")
@Expose
private String headline;

/**
* 
* @return
* The heroImage
*/
public HeroImage getHeroImage() {
return heroImage;
}

/**
* 
* @param heroImage
* The hero-image
*/
public void setHeroImage(HeroImage heroImage) {
this.heroImage = heroImage;
}

/**
* 
* @return
* The headline
*/
public String getHeadline() {
return headline;
}

/**
* 
* @param headline
* The headline
*/
public void setHeadline(String headline) {
this.headline = headline;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.heroImage, flags);
        dest.writeString(this.headline);
    }

    public Default() {
    }

    protected Default(Parcel in) {
        this.heroImage = in.readParcelable(HeroImage.class.getClassLoader());
        this.headline = in.readString();
    }

    public static final Creator<Default> CREATOR = new Creator<Default>() {
        @Override
        public Default createFromParcel(Parcel source) {
            return new Default(source);
        }

        @Override
        public Default[] newArray(int size) {
            return new Default[size];
        }
    };
}