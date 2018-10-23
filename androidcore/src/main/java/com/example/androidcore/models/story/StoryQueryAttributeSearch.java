//package com.example.androidcore.models.story;
//
//import com.quintype.core.data.Callback;
//import com.quintype.core.data.Request;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import retrofit2.Call;
//import rx.Observable;
//
///**
// * Created by rakshith on 5/24/17.
// */
//public final class StoryQueryAttributeSearch extends Request<List<Story>> {
//
//    private String searchTerm;
//    private String searchAttributeKey;
//    private String searchAttributeValue;
//    private int offset;
//    private int limit;
//    private String fields;
//
//    String SEARCH_QUERY_TERM_PARAM = "q";
//    String SEARCH_QUERY_TERM_ATTRIBUTE = "story-attributes.%s";
//    String SEARCH_QUERY_FROM_PARAM = "from";
//    String SEARCH_QUERY_SIZE_PARAM = "size";
//    String QUERY_PARAM_FIELDS = "fields";
//
//    private QuintypeStoryApi quintypeStoryApi;
//
//    StoryQueryAttributeSearch(QuintypeStoryApi quintypeStoryApi) {
//        this.quintypeStoryApi = quintypeStoryApi;
//    }
//
//    /**
//     * Search using a term.
//     *
//     * @param searchTerm String term.
//     */
//    public com.quintype.core.story.StoryQueryAttributeSearch term(String searchTerm) {
//        this.searchTerm = searchTerm;
//        return this;
//    }
//
//    /**
//     * Search using a term.
//     *
//     * @param searchAttributeKey String attribute key.
//     */
//    public com.quintype.core.story.StoryQueryAttributeSearch attributeKey(String searchAttributeKey) {
//        this.searchAttributeKey = searchAttributeKey;
//        return this;
//    }
//
//    /**
//     * Search using a term.
//     *
//     * @param searchAttributeValue String attribute value.
//     */
//    public com.quintype.core.story.StoryQueryAttributeSearch attributeValue(String searchAttributeValue) {
//        this.searchAttributeValue = searchAttributeValue;
//        return this;
//    }
//
//    /**
//     * Set offset by to fetch next list of stories.
//     *
//     * @param offset int offset. an offset 10 will fetch stories from 11
//     */
//    public com.quintype.core.story.StoryQueryAttributeSearch offset(int offset) {
//        this.offset = offset;
//        return this;
//    }
//
//    /**
//     * Set the limit to maximum number of stories to be fetched from the server.
//     *
//     * @param limit int limit. total number of stories to be fetched per network request
//     */
//    public com.quintype.core.story.StoryQueryAttributeSearch limit(int limit) {
//        this.limit = limit;
//        return this;
//    }
//
//    /**
//     * Set the fields to be fetched from server.
//     *
//     * @param fields An array of String fields. see {@link StoryRequest#getFields(String...)}
//     */
//    public com.quintype.core.story.StoryQueryAttributeSearch fields(String... fields) {
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
//    public Observable<List<Story>> getObservable() {
//        return quintypeStoryApi.getSearchAttributeResultRx(fetchValues());
//    }
//
//    private Map<String, String> fetchValues() {
//        Map<String, String> data = new HashMap<String, String>();
//        data.put(SEARCH_QUERY_TERM_PARAM, searchTerm);
//        data.put(QUERY_PARAM_FIELDS, fields);
//        String attributeKey = String.format(SEARCH_QUERY_TERM_ATTRIBUTE, searchAttributeKey);
//        data.put(attributeKey, searchAttributeValue);
//        return data;
//    }
//
//    @Override
//    protected Call<List<Story>> getRetrofitCall() {
//        return quintypeStoryApi.getSearchAttributeResult(fetchValues());
//    }
//
//    @Override
//    protected retrofit2.Callback<List<Story>> getRetrofitCallback(Callback<List<Story>> callback) {
//        return getGenericRetrofitCallback(callback);
//    }
//
//    @Override
//    protected List<Story> getEmptyResponse(int code) {
//        return new ArrayList<>();
//    }
//}