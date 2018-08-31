package quintype.com.templatecollectionwithrx.models.story;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

/**
 * An object instance representing SearchResult
 *
 * @author Madhu madhu@quintype.com
 */
public class StorySearchResult {

    /**
     * An object instance representing SearchResults
     *
     * @author Madhu madhu@quintype.com
     */
    public static class Results {
        @SerializedName("results")
        private StorySearchResult result;

        public StorySearchResult result() {
            return result;
        }

        @Override
        public String toString() {
            return "SearchResults{" +
                    "result=" + result +
                    '}';
        }
    }


    @SerializedName("term")
    private String term;
    @SerializedName("from")
    private int from;
    @SerializedName("size")
    private int size;
    @SerializedName("total")
    private int total;
    @SerializedName("stories")
    private List<Story> stories = Collections.emptyList();

    public String term() {
        return term;
    }

    public int from() {
        return from;
    }

    public int size() {
        return size;
    }

    public int total() {
        return total;
    }

    /**
     * @return List of stories found by search {@link #term()}
     */
    public List<Story> stories() {
        return Collections.unmodifiableList(stories);
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "term=" + term +
                ", from=" + from +
                ", size=" + size +
                ", total=" + total +
                ", stories=" + stories +
                '}';
    }
}
