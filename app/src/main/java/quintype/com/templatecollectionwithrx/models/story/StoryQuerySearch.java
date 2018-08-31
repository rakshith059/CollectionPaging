//package quintype.com.templatecollectionwithrx.models.story;
//
//import com.quintype.core.data.Callback;
//import com.quintype.core.data.Request;
//
//import retrofit2.Call;
//import rx.Observable;
//
///**
// * An implementation for story search request
// */
//public final class StoryQuerySearch extends Request<StorySearchResult.Results> {
//
//    private String searchTerm;
//    private int offset;
//    private int limit;
//    private String fields;
//
//    private QuintypeStoryApi quintypeStoryApi;
//
//    StoryQuerySearch(QuintypeStoryApi quintypeStoryApi) {
//        this.quintypeStoryApi = quintypeStoryApi;
//    }
//
//    /**
//     * Search using a term.
//     *
//     * @param searchTerm String term.
//     */
//    public StoryQuerySearch term(String searchTerm) {
//        this.searchTerm = searchTerm;
//        return this;
//    }
//
//    /**
//     * Set offset by to fetch next list of stories.
//     *
//     * @param offset int offset. an offset 10 will fetch stories from 11
//     */
//    public StoryQuerySearch offset(int offset) {
//        this.offset = offset;
//        return this;
//    }
//
//    /**
//     * Set the limit to maximum number of stories to be fetched from the server.
//     *
//     * @param limit int limit. total number of stories to be fetched per network request
//     */
//    public StoryQuerySearch limit(int limit) {
//        this.limit = limit;
//        return this;
//    }
//
//    /**
//     * Set the fields to be fetched from server.
//     *
//     * @param fields An array of String fields. see {@link StoryRequest#getFields(String...)}
//     */
//    public StoryQuerySearch fields(String... fields) {
//        this.fields = StoryRequest.getFields(fields);
//        return this;
//    }
//
//    @Override
//    protected boolean validate() {
//        if (searchTerm == null) {
//            throw new IllegalArgumentException("Search term is empty");
//        }
//        if (fields == null) {
//            this.fields = StoryRequest.getFields();
//        }
//        return true;
//    }
//
//    @Override
//    public Observable<StorySearchResult.Results> getObservable() {
//        return quintypeStoryApi.getSearchResultRx(searchTerm, offset, limit, fields);
//    }
//
//    @Override
//    protected Call<StorySearchResult.Results> getRetrofitCall() {
//        return quintypeStoryApi.getSearchResult(searchTerm, offset, limit, fields);
//    }
//
//    @Override
//    protected retrofit2.Callback<StorySearchResult.Results> getRetrofitCallback(Callback<StorySearchResult.Results> callback) {
//        return getGenericRetrofitCallback(callback);
//    }
//
//    @Override
//    protected StorySearchResult.Results getEmptyResponse(int code) {
//        return new StorySearchResult.Results();
//    }
//}
