//package com.example.androidcore.models.story;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import com.google.gson.annotations.SerializedName;
//import com.quintype.core.data.DataOrigin;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * An object instance representing RelatedStories
// *
// * @author Madhu madhu@quintype.com
// */
//public class RelatedStories implements Parcelable, DataOrigin {
//
//    @SerializedName("related-stories")
//    private List<Story> relatedStories = Collections.emptyList();
//    @SerializedName("local-source")
//    private Source source = Source.NETWORK;
//
//    /**
//     * @return List of related stories
//     */
//    public List<Story> stories() {
//        return relatedStories;
//    }
//
//    @Override
//    public Source getSource() {
//        return source;
//    }
//
//    public void setSource(Source source) {
//        if (source == null) {
//            throw new IllegalArgumentException("Source cannot be null");
//        }
//        this.source = source;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeList(this.relatedStories);
//        dest.writeString(this.source.name());
//    }
//
//    public RelatedStories() {
//    }
//
//    public RelatedStories(List<Story> relatedStories, Source source) {
//        this.relatedStories = new ArrayList<>(relatedStories);
//        this.source = source;
//    }
//
//    protected RelatedStories(Parcel in) {
//        this.relatedStories = new ArrayList<Story>();
//        in.readList(this.relatedStories, List.class.getClassLoader());
//        String sourceString = in.readString();
//        this.source = DataOrigin.Source.valueOf(sourceString);
//    }
//
//    public static final Creator<RelatedStories> CREATOR = new Creator<RelatedStories>() {
//        public RelatedStories createFromParcel(Parcel source) {
//            return new RelatedStories(source);
//        }
//
//        public RelatedStories[] newArray(int size) {
//            return new RelatedStories[size];
//        }
//    };
//}
