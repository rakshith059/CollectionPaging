//package com.example.androidcore.models.story;
//
//import com.quintype.core.data.Callback;
//import com.quintype.core.data.Request;
//
//import retrofit2.Call;
//import rx.Observable;
//
///**
// * An implementation for Author story search request
// */
//public final class StoryAuthorQuerySearch extends Request<StorySearchResult.Results> {
//
//    private String authorId;
//    private int offset;
//    private int limit;
//    private String fields;
//    public static final String LATEST_SORT_ORDER = "latest-published";
//
//    private QuintypeStoryApi quintypeStoryApi;
//
//    StoryAuthorQuerySearch(QuintypeStoryApi quintypeStoryApi) {
//        this.quintypeStoryApi = quintypeStoryApi;
//    }
//
//    /**
//     * Search using a term.
//     *
//     * @param searchTerm String term.
//     */
//    public StoryAuthorQuerySearch authorId(String searchTerm) {
//        this.authorId = searchTerm;
//        return this;
//    }
//
//    /**
//     * Set offset by to fetch next list of stories.
//     *
//     * @param offset int offset. an offset 10 will fetch stories from 11
//     */
//    public StoryAuthorQuerySearch offset(int offset) {
//        this.offset = offset;
//        return this;
//    }
//
//    /**
//     * Set the limit to maximum number of stories to be fetched from the server.
//     *
//     * @param limit int limit. total number of stories to be fetched per network request
//     */
//    public StoryAuthorQuerySearch limit(int limit) {
//        this.limit = limit;
//        return this;
//    }
//
//    /**
//     * Set the fields to be fetched from server.
//     *
//     * @param fields An array of String fields. see {@link StoryRequest#getFields(String...)}
//     */
//    public StoryAuthorQuerySearch fields(String... fields) {
//        this.fields = StoryRequest.getFields(fields);
//        return this;
//    }
//
//    @Override
//    protected boolean validate() {
//        if (authorId == null) {
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
//        return quintypeStoryApi.getSearchAuthorResultRx(authorId, offset, limit, fields,LATEST_SORT_ORDER);
//    }
//
//    @Override
//    protected Call<StorySearchResult.Results> getRetrofitCall() {
//        return quintypeStoryApi.getSearchAuthorResult(authorId, offset, limit, fields,LATEST_SORT_ORDER);
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
