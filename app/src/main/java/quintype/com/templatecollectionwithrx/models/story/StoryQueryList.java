//package quintype.com.templatecollectionwithrx.models.story;
//
//import com.quintype.core.data.Callback;
//import com.quintype.core.data.Request;
//
//import java.util.Collections;
//import java.util.List;
//
//import retrofit2.Call;
//import rx.Observable;
//
///**
// * An implementation for story list requests
// */
//public final class StoryQueryList extends Request<List<Story>> {
//
//    private String id;
//    private String offset;
//    private String limit;
//    private String section;
//    private String sectionId;
//    private String fields;
//    private String tag;
//    private String sortType;
//
//    private QuintypeStoryApi quintypeStoryApi;
//
//    StoryQueryList(QuintypeStoryApi quintypeStoryApi) {
//        this.quintypeStoryApi = quintypeStoryApi;
//    }
//
//    /**
//     * Set Id to fetch the story.
//     *
//     * @param id String Story id
//     */
//    public StoryQueryList id(String id) {
//        this.id = id;
//        return this;
//    }
//
//    /**
//     * Set offset by to fetch next list of stories.
//     *
//     * @param offset int offset. an offset 10 will fetch stories from 11
//     */
//    public StoryQueryList offset(int offset) {
//        this.offset = String.valueOf(offset);
//        return this;
//    }
//
//    /**
//     * Set the limit to maximum number of stories to be fetched from the server.
//     *
//     * @param limit int limit. total number of stories to be fetched per network request
//     */
//    public StoryQueryList limit(int limit) {
//        this.limit = String.valueOf(limit);
//        return this;
//    }
//
//    /**
//     * Set the section of the stories to be fetched.
//     *
//     * @param section String section.
//     */
//    public StoryQueryList section(String section) {
//        this.section = section;
//        return this;
//    }
//
//    /**
//     * Set the section-id of the stories to be fetched.
//     *
//     * @param sectionId-id String section-id.
//     */
//    public StoryQueryList sectionId(String sectionId) {
//        this.sectionId = sectionId;
//        return this;
//    }
//
//    /**
//     * Set the section of the stories to be fetched.
//     *
//     * @param sections String section.
//     */
//    public StoryQueryList sections(String... sections) {
//        this.section = sections[0];
//        return this;
//    }
//
//
//    /**
//     * Set the fields to be fetched from server.
//     *
//     * @param fields An array of String fields. see {@link StoryRequest#getFields(String...)}
//     */
//    public StoryQueryList fields(String... fields) {
//        this.fields = StoryRequest.getFields(fields);
//        return this;
//    }
//
//    /**
//     * Set tag for the story request.
//     *
//     * @param tag String tag
//     */
//    public StoryQueryList tag(String tag) {
//        this.tag = tag;
//        return this;
//    }
//
//    /**
//     * Set sort type for story request. For instance sort by section "fashion"
//     *
//     * @param sortType String sort type.
//     */
//    public StoryQueryList sortType(String sortType) {
//        this.sortType = sortType;
//        return this;
//    }
//
//    @Override
//    protected boolean validate() {
//        if (fields == null) {
//            this.fields = StoryRequest.getFields();
//        }
//        return true;
//    }
//
//    @Override
//    public Observable<List<Story>> getObservable() {
//        return quintypeStoryApi.getStoriesRx(offset, limit, section, sectionId, fields, tag,
//                sortType);
//    }
//
//    @Override
//    protected Call<List<Story>> getRetrofitCall() {
//        return quintypeStoryApi.getStories(offset, limit, section, sectionId, fields, tag,
//                sortType);
//    }
//
//    @Override
//    protected retrofit2.Callback<List<Story>> getRetrofitCallback(Callback<List<Story>> callback) {
//        return getGenericRetrofitCallback(callback);
//    }
//
//    @Override
//    protected List<Story> getEmptyResponse(int code) {
//        return Collections.emptyList();
//    }
//}
