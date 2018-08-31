//package quintype.com.templatecollectionwithrx.models.story;
//
//import com.quintype.core.data.Callback;
//import com.quintype.core.data.Request;
//
//import retrofit2.Call;
//import retrofit2.Response;
//import rx.Observable;
//
///**
// * An implementation of slug based story request
// */
//public final class StoryQuerySlug extends Request<SlugStory> {
//
//    private String slug;
//    private String fields;
//    private QuintypeStoryApi quintypeStoryApi;
//
//    StoryQuerySlug(QuintypeStoryApi quintypeStoryApi) {
//        this.quintypeStoryApi = quintypeStoryApi;
//    }
//
//    /**
//     * find story by slug
//     *
//     * @param slug String slug
//     */
//    public StoryQuerySlug slug(String slug) {
//        this.slug = slug;
//        return this;
//    }
//
//    /**
//     * Set the fields to be fetched from server.
//     *
//     * @param fields An array of String fields. see {@link StoryRequest#getFields(String...)}
//     */
//    public StoryQuerySlug fields(String... fields) {
//        this.fields = StoryRequest.getFields(fields);
//        return this;
//    }
//
//
//    @Override
//    protected boolean validate() {
//        if (slug == null) {
//            throw new RuntimeException("SLUG cannot be empty");
//        }
//
//        if (fields == null) {
//            this.fields = StoryRequest.getFields();
//        }
//
//        return true;
//    }
//
//    @Override
//    public Observable<SlugStory> getObservable() {
//        return quintypeStoryApi.getStoriesFromSlugRx(slug, fields);
//    }
//
//    @Override
//    protected Call getRetrofitCall() {
//        return quintypeStoryApi.getStoriesFromSlug(slug, fields);
//    }
//
//    @Override
//    protected retrofit2.Callback getRetrofitCallback(final Callback<SlugStory> callback) {
//        return new retrofit2.Callback<SlugStory>() {
//
//            @Override
//            public void onResponse(Call<SlugStory> call, Response<SlugStory> response) {
//                if (response == null || response.body() == null) {
//                    callback.onSuccess(getEmptyResponse(response.code()));
//                } else {
//                    callback.onSuccess(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SlugStory> call, Throwable t) {
//                callback.onFailure(t);
//            }
//        };
//    }
//
//    @Override
//    protected SlugStory getEmptyResponse(int code) {
//        return null;
//    }
//}
