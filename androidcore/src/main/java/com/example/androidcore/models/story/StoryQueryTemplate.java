//package com.example.androidcore.models.story;
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
// * An object instance of a StoryQueryTemplate class
// * <p/>
// * A Builder Query to get list of stories by template.
// */
//public final class StoryQueryTemplate extends Request<List<Story>> {
//
//    private String template;
//    private int limit;
//    private String fields;
//    private QuintypeStoryApi quintypeStoryApi;
//
//    StoryQueryTemplate(QuintypeStoryApi quintypeStoryApi) {
//        this.quintypeStoryApi = quintypeStoryApi;
//    }
//
//    /**
//     * @param template Story template
//     */
//    public StoryQueryTemplate template(String template) {
//        this.template = template;
//        return this;
//    }
//
//    /**
//     * Set the limit to maximum number of stories to be fetched from the server.
//     *
//     * @param limit int limit. total number of stories to be fetched per network request
//     */
//    public StoryQueryTemplate limit(int limit) {
//        this.limit = limit;
//        return this;
//    }
//
//    /**
//     * Set the fields to be fetched from server.
//     *
//     * @param fields An array of String fields. see {@link StoryRequest#getFields(String...)}
//     */
//    public StoryQueryTemplate fields(String... fields) {
//        this.fields = StoryRequest.getFields(fields);
//        return this;
//    }
//
//    @Override
//    protected boolean validate() {
//
//        if (template == null) {
//            throw new RuntimeException("Template is empty");
//        }
//
//        if (fields == null) {
//            fields = StoryRequest.getFields();
//        }
//        return true;
//    }
//
//    @Override
//    public Observable<List<Story>> getObservable() {
//        return quintypeStoryApi.getStoriesByTemplateRx(template, limit, fields);
//    }
//
//    @Override
//    protected Call<List<Story>> getRetrofitCall() {
//        return quintypeStoryApi.getStoriesByTemplate(template, limit, fields);
//    }
//
//    @Override
//    protected retrofit2.Callback getRetrofitCallback(Callback<List<Story>> callback) {
//        return getGenericRetrofitCallback(callback);
//    }
//
//    @Override
//    protected List<Story> getEmptyResponse(int code) {
//        return Collections.emptyList();
//    }
//
//}
