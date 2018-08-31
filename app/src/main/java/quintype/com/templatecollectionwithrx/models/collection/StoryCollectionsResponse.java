package quintype.com.templatecollectionwithrx.models.collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshaykoul on 24/06/16.
 */
public class StoryCollectionsResponse {
    @SerializedName("story-collections")
    @Expose
    private List<StoryCollection> storyCollections = new ArrayList<StoryCollection>();

    public List<StoryCollection> storyCollections() {
        return storyCollections;
    }

    public void storyCollections(List<StoryCollection> storyCollections) {
        this.storyCollections = storyCollections;
    }
}
