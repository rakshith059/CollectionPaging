//package com.example.androidcore.models.story;
//
//
//import java.util.Collections;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import rx.Observable;
//
///**
// * An object representing UserEngagementQuery
// * <p/>
// * Get story related comments and votes
// */
////public class UserEngagementQuery extends Request<List<UserEngagement>> {
////
////    String storyContentId;
////    QuintypeStoryApi quintypeStoryApi;
////
////    UserEngagementQuery(String storyContentId, QuintypeStoryApi quintypeStoryApi) {
////        this.storyContentId = storyContentId;
////        this.quintypeStoryApi = quintypeStoryApi;
////    }
////
////    @Override
////    protected boolean validate() {
////        return !StringUtils.isEmpty(storyContentId);
////    }
////
////    @Override
////    public Observable<List<UserEngagement>> getObservable() {
////        return quintypeStoryApi.getUserEngagementRx(storyContentId);
////    }
////
////    @Override
////    protected Call getRetrofitCall() {
////        return quintypeStoryApi.getUserEngagement(storyContentId);
////    }
////
////    @Override
////    protected Callback getRetrofitCallback(com.quintype.core.data.Callback<List<UserEngagement>> callback) {
////        return getGenericRetrofitCallback(callback);
////    }
////
////    @Override
////    protected List<UserEngagement> getEmptyResponse(int code) {
////        return Collections.emptyList();
////    }
////}
