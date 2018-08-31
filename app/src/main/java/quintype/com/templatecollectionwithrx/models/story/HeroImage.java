package quintype.com.templatecollectionwithrx.models.story;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroImage implements Parcelable {

@SerializedName("hero-image-url")
@Expose
private String heroImageUrl;
@SerializedName("hero-image-metadata")
@Expose
private HeroImageMetadata  heroImageMetadata;
@SerializedName("hero-image-caption")
@Expose
private String heroImageCaption;
@SerializedName("hero-image-s3-key")
@Expose
private String heroImageS3Key;

/**
* 
* @return
* The heroImageUrl
*/
public String getHeroImageUrl() {
return heroImageUrl;
}

/**
* 
* @param heroImageUrl
* The hero-image-url
*/
public void setHeroImageUrl(String heroImageUrl) {
this.heroImageUrl = heroImageUrl;
}

/**
* 
* @return
* The heroImageMetadata
*/
public HeroImageMetadata getHeroImageMetadata() {
return heroImageMetadata;
}

/**
* 
* @param heroImageMetadata
* The hero-image-metadata
*/
public void setHeroImageMetadata(HeroImageMetadata heroImageMetadata) {
this.heroImageMetadata = heroImageMetadata;
}

/**
* 
* @return
* The heroImageCaption
*/
public String getHeroImageCaption() {
return heroImageCaption;
}

/**
* 
* @param heroImageCaption
* The hero-image-caption
*/
public void setHeroImageCaption(String heroImageCaption) {
this.heroImageCaption = heroImageCaption;
}

/**
* 
* @return
* The heroImageS3Key
*/
public String getHeroImageS3Key() {
return heroImageS3Key;
}

/**
* 
* @param heroImageS3Key
* The hero-image-s3-key
*/
public void setHeroImageS3Key(String heroImageS3Key) {
this.heroImageS3Key = heroImageS3Key;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.heroImageUrl);
        dest.writeParcelable(this.heroImageMetadata, flags);
        dest.writeString(this.heroImageCaption);
        dest.writeString(this.heroImageS3Key);
    }

    public HeroImage() {
    }

    protected HeroImage(Parcel in) {
        this.heroImageUrl = in.readString();
        this.heroImageMetadata = in.readParcelable(HeroImageMetadata.class.getClassLoader());
        this.heroImageCaption = in.readString();
        this.heroImageS3Key = in.readString();
    }

    public static final Creator<HeroImage> CREATOR = new Creator<HeroImage>
            () {
        @Override
        public HeroImage createFromParcel(Parcel source) {
            return new HeroImage(source);
        }

        @Override
        public HeroImage[] newArray(int size) {
            return new HeroImage[size];
        }
    };
}