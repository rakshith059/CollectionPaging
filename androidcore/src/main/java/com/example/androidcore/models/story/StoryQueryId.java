//package com.example.androidcore.models.story;
//
//import com.quintype.commons.StringUtils;
//import com.quintype.core.cache.StoryDiskLruCache;
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
// * An implementation of id based stories
// */
//public final class StoryQueryId extends Request<IdStory> {
//
//    private final GenericLogger logger = new GenericLogger("StoryQueryId.class");
//    private final StoryDiskLruCache storyDiskLruCache;
//    private String id;
//    private String fields;
//    private QuintypeStoryApi quintypeStoryApi;
//    private boolean forceNetwork = false;
//
//    StoryQueryId(QuintypeStoryApi quintypeStoryApi, StoryDiskLruCache storyDiskLruCache) {
//        this.quintypeStoryApi = quintypeStoryApi;
//        this.storyDiskLruCache = storyDiskLruCache;
//    }
//
//    /**
//     * Set Id to fetch the story.
//     *
//     * @param id String Story id
//     */
//    public StoryQueryId id(String id) {
//        this.id = id;
//        return this;
//    }
//
//    /**
//     * Set the fields to be fetched from server.
//     *
//     * @param fields An array of String fields. see {@link StoryRequest#getFields(String...)}
//     */
//    public StoryQueryId fields(String... fields) {
//        this.fields = StoryRequest.getFields(fields);
//        return this;
//    }
//
//    /**
//     * Forces request to use network and by pass cache
//     */
//    public StoryQueryId forceNetwork() {
//        this.forceNetwork = true;
//        return this;
//    }
//
//    @Override
//    protected boolean validate() {
//
//        if (StringUtils.isEmpty(id)) {
//            throw new RuntimeException("Id cannot be null");
//        }
//        if (fields == null) {
//            fields = StoryRequest.getFields();
//        }
//        return true;
//    }
//
//    @Override
//    public Observable<IdStory> getObservable() {
//        if (forceNetwork) {
//            return getFromNetwork();
//        } else {
//            Story memCachedStory = storyDiskLruCache.getFromMemory(this.id);
//            if (memCachedStory != null) {
//                IdStory idStory = new IdStory(memCachedStory, DataOrigin.Source.MEMORY);
//                return Observable.just(idStory);
//            } else {
//                //do rx get from cache
//                return storyDiskLruCache
//                        .rxGet(id)
//                        .flatMap(new Func1<Story, Observable<IdStory>>() {
//                            @Override
//                            public Observable<IdStory> call(Story story) {
//                                if (story != null) {
//                                    //got from disk cache
//                                    IdStory idStory = new IdStory(story, DataOrigin.Source.DISK);
//                                    return Observable.just(idStory);
//                                } else {
//                                    //we need to get from network
//                                    return getFromNetwork();
//                                }
//                            }
//                        })
//                        .onErrorResumeNext(new Func1<Throwable, Observable<? extends IdStory>>() {
//                            @Override
//                            public Observable<? extends IdStory> call(Throwable throwable) {
//                                logger.e(throwable, "Id story load failed");
//                                return Observable.just(new IdStory());
//                            }
//                        });
//            }
//        }
//    }
//
//    private Observable<IdStory> getFromNetwork() {
//        return quintypeStoryApi
//                .getStoryFromIdRx(id, fields)
//                .map(new Func1<IdStory, IdStory>() {
//                    @Override
//                    public IdStory call(IdStory idStory) {
//                        IdStory newStory = new IdStory(idStory.story(), DataOrigin.Source.NETWORK);
//                        //put this story in cache
//                        storyDiskLruCache.put(idStory.story().contentId(), idStory.story());
//                        return newStory;
//                    }
//                });
//    }
//
//    @Override
//    protected Call<IdStory> getRetrofitCall() {
//        if (forceNetwork) {
//            return quintypeStoryApi.getStoryFromId(id, fields);
//        } else {
//            return new Call<IdStory>() {
//                CompositeSubscription compositeSubscription = new CompositeSubscription();
//
//                @Override
//                public Response<IdStory> execute() throws IOException {
//                    Story cachedStory = storyDiskLruCache.getFromMemory(id);
//                    if (cachedStory != null) {
//                        IdStory newStory = new IdStory(cachedStory, DataOrigin.Source.MEMORY);
//                        return Response.success(newStory);
//                    } else {
//                        //mem fail, check disk
//                        cachedStory = storyDiskLruCache.get(id);
//                        if (cachedStory != null) {
//                            //disk hit
//                            IdStory newStory = new IdStory(cachedStory, DataOrigin.Source.DISK);
//                            return Response.success(newStory);
//                        } else {
//                            //disk fail - go to network
//                            return quintypeStoryApi.getStoryFromId(id, fields).execute();
//                        }
//                    }
//                }
//
//                @Override
//                public void enqueue(final retrofit2.Callback<IdStory> callback) {
//                    final Call call = clone();
//                    Story cachedStory = storyDiskLruCache.getFromMemory(id);
//                    if (cachedStory != null) {
//                        IdStory newStory = new IdStory(cachedStory, DataOrigin.Source.MEMORY);
//                        callback.onResponse(call, Response.success(newStory));
//                    } else {
//                        //mem fail, check disk
//                        Observable<IdStory> observable = storyDiskLruCache
//                                .rxGet(id)
//                                .subscribeOn(Schedulers.io())
//                                .flatMap(new Func1<Story, Observable<IdStory>>() {
//                                    @Override
//                                    public Observable<IdStory> call(Story story) {
//                                        if (story != null) {
//                                            //got from disk cache
//                                            IdStory idStory = new IdStory(story, DataOrigin
//                                                    .Source.DISK);
//                                            return Observable.just(idStory);
//                                        } else {
//                                            //we need to get from network
//                                            return getFromNetwork();
//                                        }
//                                    }
//                                })
//                                .onErrorResumeNext(new Func1<Throwable, Observable<? extends
//                                        IdStory>>() {
//                                    @Override
//                                    public Observable<? extends IdStory> call(Throwable throwable) {
//                                        logger.e(throwable, "Id story load failed");
//                                        return Observable.just(new IdStory());
//                                    }
//                                });
//                        compositeSubscription.add(observable
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(Schedulers.computation())
//                                .onErrorResumeNext(Observable.<IdStory>empty())
//                                .subscribe(new Action1<IdStory>() {
//                                    @Override
//                                    public void call(IdStory idStory) {
//                                        callback.onResponse(call, Response.success(idStory));
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
//                public Call<IdStory> clone() {
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
//    protected retrofit2.Callback<IdStory> getRetrofitCallback(final Callback<IdStory> callback) {
//        return new retrofit2.Callback<IdStory>() {
//            @Override
//            public void onResponse(Call<IdStory> call, Response<IdStory> response) {
//                if (response == null || response.body() == null) {
//                    callback.onFailure(new Exception("Id Not found"));
//                } else {
//                    callback.onSuccess(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<IdStory> call, Throwable t) {
//                callback.onFailure(t);
//            }
//        };
//    }
//
//    @Override
//    protected IdStory getEmptyResponse(int code) {
//        return null;
//    }
//}
