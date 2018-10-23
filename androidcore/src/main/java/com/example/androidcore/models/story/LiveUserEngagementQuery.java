//package com.example.androidcore.models.story;
//
//import com.quintype.commons.StringUtils;
//import com.quintype.core.data.Request;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import rx.Observable;
//
///**
// * Created by Deepak on 22/01/18.
// */
//public class LiveUserEngagementQuery extends Request<LiveUserEngagement> {
//
//    String storyContentId;
//    QuintypeStoryApi quintypeStoryApi;
//
//    public LiveUserEngagementQuery(String storyContentId, QuintypeStoryApi quintypeStoryApi) {
//        this.storyContentId = storyContentId;
//        this.quintypeStoryApi = quintypeStoryApi;
//    }
//
//    @Override
//    protected boolean validate() {
//        return !StringUtils.isEmpty(storyContentId);
//    }
//
//    @Override
//    public Observable<LiveUserEngagement> getObservable() {
//        return null;
//    }
//
//    @Override
//    protected Call getRetrofitCall() {
//        return quintypeStoryApi.getLiveUserEngagement(storyContentId);
//    }
//
//    @Override
//    protected Callback getRetrofitCallback(com.quintype.core.data.Callback<LiveUserEngagement> callback) {
//        return getGenericRetrofitCallback(callback);
//    }
//
//    @Override
//    protected LiveUserEngagement getEmptyResponse(int code) {
//        return null;
//    }
//}
