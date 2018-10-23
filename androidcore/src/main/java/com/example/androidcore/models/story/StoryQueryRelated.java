//package com.example.androidcore.models.story;
//
//import com.quintype.core.cache.RelatedStoriesDiskLruCache;
//import com.quintype.core.data.Callback;
//import com.quintype.core.data.DataOrigin;
//import com.quintype.core.data.Request;
//import com.quintype.core.logger.GenericLogger;
//
//import java.io.IOException;
//
//import retrofit2.Call;
//import retrofit2.Response;
//import rx.Observable;
//import rx.functions.Action1;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//import rx.subscriptions.CompositeSubscription;
//
///**
// * An implementation for related stories request
// */
//public final class StoryQueryRelated extends Request<RelatedStories> {
//
//    private final GenericLogger logger = new GenericLogger("StoryQueryRelated.class");
//    private String id;
//    private String fields;
//    private String section;
//    private int limit;
//    private QuintypeStoryApi quintypeStoryApi;
//    private RelatedStoriesDiskLruCache relatedStoriesDiskLruCache;
//
//    private boolean forceNetwork = false;
//
//    StoryQueryRelated(QuintypeStoryApi quintypeStoryApi, RelatedStoriesDiskLruCache
//            relatedStoriesDiskLruCache) {
//        this.quintypeStoryApi = quintypeStoryApi;
//        this.relatedStoriesDiskLruCache = relatedStoriesDiskLruCache;
//    }
//
//    /**
//     * Set Id to fetch the story.
//     *
//     * @param id String Story id
//     */
//    public StoryQueryRelated id(String id) {
//        this.id = id;
//        return this;
//    }
//
//    /**
//     * Set Section to fetch the story.
//     *
//     * @param section String Story section
//     */
//    public StoryQueryRelated section(String section) {
//        this.section = section;
//        return this;
//    }
//
//    /**
//     * Set the limit to maximum number of stories to be fetched from the server.
//     *
//     * @param limit int limit. total number of stories to be fetched per network request
//     */
//    public StoryQueryRelated limit(int limit) {
//        this.limit = limit;
//        return this;
//    }
//
//    /**
//     * Set the fields to be fetched from server.
//     *
//     * @param fields An array of String fields. see {@link StoryRequest#getFields(String...)}
//     */
//    public StoryQueryRelated fields(String... fields) {
//        this.fields = StoryRequest.getFields(fields);
//        return this;
//    }
//
//    /**
//     * Forces request to use network and by pass cache
//     */
//    public StoryQueryRelated forceNetwork() {
//        this.forceNetwork = true;
//        return this;
//    }
//
//    @Override
//    protected boolean validate() {
//        if (id == null) {
//            throw new RuntimeException("ID cannot be empty");
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
//    public Observable<RelatedStories> getObservable() {
//        if (forceNetwork) {
//            return getFromNetwork();
//        } else {
//            RelatedStories memCachedRelatedStories = relatedStoriesDiskLruCache.getFromMemory
//                    (this.id);
//            if (memCachedRelatedStories != null) {
//                RelatedStories relatedStories = new RelatedStories(memCachedRelatedStories
//                        .stories(), DataOrigin.Source.MEMORY);
//                return Observable.just(relatedStories);
//            } else {
//                //do rx get from cache
//                return relatedStoriesDiskLruCache
//                        .rxGet(id)
//                        .flatMap(new Func1<RelatedStories, Observable<RelatedStories>>() {
//                            @Override
//                            public Observable<RelatedStories> call(RelatedStories relatedStories) {
//                                if (relatedStories != null) {
//                                    //got from disk cache
//                                    RelatedStories diskrelatedStories = new RelatedStories
//                                            (relatedStories.stories(), DataOrigin.Source.DISK);
//                                    return Observable.just(diskrelatedStories);
//                                } else {
//                                    //we need to get from network
//                                    return getFromNetwork();
//                                }
//                            }
//                        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends
//                                RelatedStories>>() {
//                            @Override
//                            public Observable<? extends RelatedStories> call(Throwable throwable) {
//                                logger.e(throwable, "Related stories load failed");
//                                return Observable.just(new RelatedStories());
//                            }
//                        });
//            }
//        }
//    }
//
//    private Observable<RelatedStories> getFromNetwork() {
//        return quintypeStoryApi
//                .getRelatedStoriesRx(id, section, fields)
//                .map(new Func1<RelatedStories, RelatedStories>() {
//                    @Override
//                    public RelatedStories call(RelatedStories relatedStories) {
//                        RelatedStories newRelatedStories = new RelatedStories(relatedStories
//                                .stories(), DataOrigin.Source.NETWORK);
//                        //put this story in cache
//                        relatedStoriesDiskLruCache.put(id, newRelatedStories);
//                        return newRelatedStories;
//                    }
//                });
//    }
//
//    @Override
//    protected Call getRetrofitCall() {
//        if (forceNetwork) {
//            return quintypeStoryApi.getRelatedStories(id, section, limit, fields);
//        } else {
//
//            return new Call<RelatedStories>() {
//                CompositeSubscription compositeSubscription = new CompositeSubscription();
//
//                @Override
//                public Response<RelatedStories> execute() throws IOException {
//                    RelatedStories cachedRelStories = relatedStoriesDiskLruCache.getFromMemory(id);
//                    if (cachedRelStories != null) {
//                        RelatedStories newRelatedStories = new RelatedStories(cachedRelStories
//                                .stories(), DataOrigin.Source.MEMORY);
//                        return Response.success(newRelatedStories);
//                    } else {
//                        //mem fail, check disk
//                        cachedRelStories = relatedStoriesDiskLruCache.get(id);
//                        if (cachedRelStories != null) {
//                            //disk hit
//                            RelatedStories newRelatedStories = new RelatedStories
//                                    (cachedRelStories.stories(), DataOrigin.Source.DISK);
//                            return Response.success(newRelatedStories);
//                        } else {
//                            //disk fail - go to network
//                            return quintypeStoryApi.getRelatedStories(id, section, limit, fields)
//                                    .execute();
//                        }
//                    }
//                }
//
//                @Override
//                public void enqueue(final retrofit2.Callback<RelatedStories> callback) {
//                    final Call call = clone();
//                    RelatedStories relStories = relatedStoriesDiskLruCache.getFromMemory(id);
//                    if (relStories != null) {
//                        RelatedStories newRelatedStories = new RelatedStories(relStories.stories
//                                (), DataOrigin.Source.MEMORY);
//                        Response.success(newRelatedStories);
//                    } else {
//                        //mem fail, check disk
//                        Observable<RelatedStories> observable = relatedStoriesDiskLruCache
//                                .rxGet(id)
//                                .subscribeOn(Schedulers.io())
//                                .flatMap(new Func1<RelatedStories, Observable<RelatedStories>>() {
//                                    @Override
//                                    public Observable<RelatedStories> call(RelatedStories
//                                                                                   relStories) {
//                                        if (relStories != null) {
//                                            //got from disk cache
//                                            RelatedStories cachedRelStories = new RelatedStories
//                                                    (relStories.stories(), DataOrigin.Source.DISK);
//                                            return Observable.just(cachedRelStories);
//                                        } else {
//                                            //we need to get from network
//                                            return getFromNetwork();
//                                        }
//                                    }
//                                }).onErrorResumeNext(new Func1<Throwable, Observable<? extends
//                                        RelatedStories>>() {
//                                    @Override
//                                    public Observable<? extends RelatedStories> call(Throwable
//                                                                                             throwable) {
//                                        logger.e(throwable, "Related stories load failed");
//                                        return Observable.just(new RelatedStories());
//                                    }
//                                });
//                        compositeSubscription.add(observable
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(Schedulers.computation())
//                                .onErrorResumeNext(Observable.<RelatedStories>empty())
//                                .subscribe(new Action1<RelatedStories>() {
//                                    @Override
//                                    public void call(RelatedStories relStories) {
//                                        callback.onResponse(call, Response.success(relStories));
//                                    }
//                                }));
//
//                    }
//                }
//
//                @Override
//                public boolean isExecuted() {
//                    return false;
//                }
//
//                @Override
//                public void cancel() {
//                    compositeSubscription.unsubscribe();
//                }
//
//                @Override
//                public boolean isCanceled() {
//                    return compositeSubscription.isUnsubscribed();
//                }
//
//                @Override
//                public Call<RelatedStories> clone() {
//                    return this;
//                }
//
//                @Override
//                public okhttp3.Request request() {
//                    return null;
//                }
//            };
//        }
//    }
//
//    @Override
//    protected retrofit2.Callback getRetrofitCallback(final Callback<RelatedStories> callback) {
//        return new retrofit2.Callback<RelatedStories>() {
//
//            @Override
//            public void onResponse(Call<RelatedStories> call, Response<RelatedStories> response) {
//                if (response == null || response.body() == null) {
//                    callback.onSuccess(getEmptyResponse(response.code()));
//                } else {
//                    callback.onSuccess(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RelatedStories> call, Throwable t) {
//                callback.onFailure(t);
//                call.cancel();
//            }
//        };
//    }
//
//    @Override
//    protected RelatedStories getEmptyResponse(int code) {
//        return null;
//    }
//}
