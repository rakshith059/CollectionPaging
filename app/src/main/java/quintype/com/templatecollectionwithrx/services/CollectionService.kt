package quintype.com.templatecollectionwithrx.services

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import io.reactivex.subscribers.ResourceSubscriber
import org.reactivestreams.Subscriber
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.CollectionItem
import quintype.com.templatecollectionwithrx.models.CollectionResponse
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.utils.Constants

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class CollectionService {
    companion object {
        var collectionService: CollectionService? = null
        var collectionApiService: CollectionApiService = CollectionApiClient.getCollectonApiClient().create(CollectionApiService::class.java)
        var mCompositeDisposable: CompositeDisposable? = null

        var collectionData: MutableLiveData<BulkTableModel> = MutableLiveData()

        //        var collectionList = ArrayList<HashMap<String, BulkTableModel>>()
        var collectionModelList = ArrayList<BulkTableModel>()

        @Synchronized
        fun getInstance(compositeDisposable: CompositeDisposable): CollectionService {
            if (collectionService == null)
                collectionService = CollectionService()

            if (compositeDisposable != null)
                mCompositeDisposable = compositeDisposable

            return collectionService as CollectionService
        }
    }

    fun getCollectionResponse(pageNumber: Int): LiveData<BulkTableModel> {
//        var collectionOrderHashMap = LinkedHashMap<String, BulkTableModel>()

//        var collectionItemList = ArrayList<String>()
        Log.d("Rakshith", "api call started for first iteration.. ")

        mCompositeDisposable?.add(collectionApiService.getCollectionApiService(Constants.COLLECTION_HOME, Constants.PAGE_LIMIT, pageNumber * Constants.PAGE_LIMIT)
                .doOnError { error -> Log.d("Rakshith", "error is " + error.message) }
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap({ mCollectionResponse ->
                    for (index in 0 until mCollectionResponse.items?.size as Int) {
                        var mCollectionItem = mCollectionResponse.items?.get(index)

                        if (mCollectionItem?.type == Constants.TYPE_COLLECTION) {

                            var bulkTableModel = BulkTableModel(mCollectionItem.slug,
                                    null,
                                    mCollectionItem?.name,
                                    mCollectionItem?.template,
                                    null,
                                    null,
                                    null)

//                            collectionOrderHashMap.put(mCollectionItem.slug as String, bulkTableModel)

                            collectionModelList.add(bulkTableModel)

                            collectionData.value = bulkTableModel

//                            collectionItemList.add(mCollectionItem?.slug.toString())
                        } else if (mCollectionItem?.type == Constants.TYPE_STORY) {
                            var bulkTableModel = BulkTableModel(mCollectionItem?.story?.slug,
                                    mCollectionItem?.story,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null)

//                            var collectionHashMap = collectionOrderHashMap.put(mCollectionItem?.story?.slug as String, bulkTableModel)

                            collectionModelList.add(bulkTableModel)
                            collectionData.value = bulkTableModel

//                            collectionItemList.add(mCollectionItem?.story?.slug.toString())
                        }
                    }
//                    for (index in 0 until collectionList.size)
//                        Log.d("Rakshith", "collection order is " + collectionList.get(index).keys)

//                    collectionList.add(collectionOrderHashMap)
                    Flowable.fromIterable(mCollectionResponse.items)
                })
                .filter({ mCollectionItem -> mCollectionItem.type.equals(Constants.TYPE_COLLECTION) })
                .concatMapEager({ mCollectionItem ->
                    return@concatMapEager collectionApiService.getCollectionApiService(mCollectionItem?.slug as String, Constants.PAGE_LIMIT_CHILD, 0)
                            .doOnError { error -> Log.d("Rakshith", "error is " + error.message) }
                            .retry(3)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                })
                .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                    override fun onComplete() {
                        Log.d("Rakshith", "api call completed for first iteration.. ")

//                        collectionData.value = collectionModelList
                    }

                    override fun onNext(mCollectionsModel: CollectionResponse) {
                        var mCollectionSlug = mCollectionsModel?.slug
                        var mCollectionItems = mCollectionsModel?.items
                        var mCollectionSize = mCollectionItems?.size as Int

                        Log.d("Rakshith", "collectionItem slug is ${mCollectionsModel.slug}")

//                        if (mCollectionSize > 4)
//                            mCollectionSize = 4

                        for (index in 0 until collectionModelList.size) {
                            if (collectionModelList.get(index)?.slug?.equals(mCollectionSlug) == true) {
                                var bulkModel: BulkTableModel = collectionModelList.get(index)

                                if (mCollectionsModel?.items?.size as Int > 0) {
                                    var innerCollectionFirstItem = mCollectionsModel?.items?.get(0)

                                    bulkModel.outerCollectionInnerSlug = innerCollectionFirstItem?.slug
                                    bulkModel.outerCollectionInnerItem = innerCollectionFirstItem
                                }
                            }
                        }

                        for (index in 0 until mCollectionSize) {
                            var mCollectionItem = mCollectionItems.get(index)

                            if (index == 0 && mCollectionItem.type?.equals(Constants.TYPE_COLLECTION) as Boolean) {
                                if (mCollectionItem.template?.equals(Constants.WIDGET_TEMPLATE) == false) {
//                                    if (collectionModelList.get(index)?.slug?.equals(mCollectionSlug) == true) {
////                                    if (collectionList.get(0).containsKey(mCollectionSlug) == true) {
////                                        var mCollectionInnerItem = collectionList.get(0).get(mCollectionSlug)
//                                        var bulkModel: BulkTableModel = collectionModelList.get(index)
//                                        bulkModel.outerCollectionInnerSlug = mCollectionItem.slug
//                                        bulkModel.outerCollectionInnerItem = mCollectionItem
//
////                                        collectionOrderHashMap.put(mCollectionInnerItem?.slug as String,
////                                                BulkTableModel(mCollectionInnerItem?.slug,
////                                                        mCollectionInnerItem?.story,
////                                                        mCollectionInnerItem?.outerCollectionName,
////                                                        mCollectionInnerItem?.outerCollectionTemplate,
////                                                        mCollectionItem?.slug,
////                                                        mCollectionItem,
////                                                        null))
//                                    }
                                    getChildRxResponse(mCollectionItem.slug as String, Constants.PAGE_LIMIT_CHILD, 0)
                                }
                            } else if (mCollectionItem.type?.equals(Constants.TYPE_STORY) as Boolean) {
                                if (collectionModelList.get(index)?.slug?.equals(mCollectionSlug) == true) {
//                                    if (collectionList.get(0).containsKey(mCollectionSlug) == true) {
//                                        var mCollectionInnerItem = collectionList.get(0).get(mCollectionSlug)
                                    var bulkModel: BulkTableModel = collectionModelList.get(index)
                                    bulkModel.innerCollectionResponse = mCollectionsModel

                                    collectionData.value = bulkModel

//                                if (collectionList.get(0).containsKey(mCollectionSlug) == true) {
//                                    var mCollectionInnerItem = collectionList.get(0).get(mCollectionSlug)
//
//                                    collectionOrderHashMap.put(mCollectionInnerItem?.slug as String,
//                                            BulkTableModel(mCollectionInnerItem?.slug,
//                                                    mCollectionInnerItem?.story,
//                                                    mCollectionInnerItem?.outerCollectionName,
//                                                    mCollectionInnerItem?.outerCollectionTemplate,
//                                                    null,
//                                                    null,
//                                                    mCollectionsModel))
                                }
                                Log.d("Rakshith", "api call success and onNext collectionSlug == $mCollectionSlug &&  collectionStory headline is ${mCollectionItem.story?.headline}")
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Rakshith", "api call failed .. " + e.message)
                    }
                })
        )
        return collectionData
    }

    fun getChildRxResponse(collectionSlug: String, limit: Int, offset: Int) {
        var collectionApiService: CollectionApiService = CollectionApiClient.getCollectonApiClient().create(CollectionApiService::class.java)

        mCompositeDisposable?.add(collectionApiService.getCollectionApiService(collectionSlug, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap({ mCollectionResponse -> Flowable.fromIterable(mCollectionResponse.items) })
                .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                    override fun onComplete() {
                        Log.d("Rakshith", " second iteration api call completed for second iteration.. ")
                    }

                    override fun onNext(mCollectionsModel: CollectionResponse) {
                        var mCollectionSlug = mCollectionsModel?.slug
                        var mCollectionItems = mCollectionsModel?.items
                        var mCollectionSize = mCollectionItems?.size as Int

                        for (index in 0 until collectionModelList.size) {
                            if (collectionModelList.get(index)?.outerCollectionInnerSlug?.equals(mCollectionSlug) == true && collectionModelList.get(index)?.outerCollectionInnerSlug?.isEmpty() == false) {
                                var bulkModel: BulkTableModel = collectionModelList.get(index)
                                bulkModel.innerCollectionResponse = mCollectionsModel

                                collectionData.value = bulkModel
                            }
                        }

//                            for (key in collectionOrderHashMap.keys) {
//                                if (collectionList.get(0).containsKey(key) == true && !TextUtils.isEmpty(collectionList.get(0).get(key)?.outerCollectionName)) {
//                                    if (mCollectionsModel?.slug?.equals(collectionList.get(0).get(key)?.outerCollectionInnerSlug) == true) {
//                                        var mCollectionInnerItem = collectionList.get(0).get(key)
//
//                                        collectionOrderHashMap.put(mCollectionInnerItem?.slug as String,
//                                                BulkTableModel(mCollectionInnerItem?.slug,
//                                                        mCollectionInnerItem?.story,
//                                                        mCollectionInnerItem?.outerCollectionName,
//                                                        mCollectionInnerItem?.outerCollectionTemplate,
//                                                        mCollectionInnerItem?.outerCollectionInnerSlug,
//                                                        mCollectionInnerItem?.outerCollectionInnerItem,
//                                                        mCollectionsModel))
//                                    }
//                                }
//                            }

                        Log.d("Rakshith", " second iteration collectionItem slug is ${mCollectionsModel.slug}")

//                        if (mCollectionSize > 4)
//                            mCollectionSize = 4

                        for (index in 0 until mCollectionSize) {
                            var mCollectionItem = mCollectionItems.get(index)

                            if (index == 0 && mCollectionItem.type?.equals(Constants.TYPE_COLLECTION) as Boolean) {
                                if (mCollectionItem.template?.equals(Constants.WIDGET_TEMPLATE) == false) {
                                    getChildRxResponse(mCollectionItem.slug as String, Constants.PAGE_LIMIT_CHILD, 0)
                                }
                            } else if (mCollectionItem.type?.equals(Constants.TYPE_STORY) as Boolean) {
                                Log.d("Rakshith", " second iteration api call success and onNext collectionSlug == $mCollectionSlug &&  collectionStory headline is ${mCollectionItem.story?.headline}")
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Rakshith", " second iteration api call failed .. " + e.message)
                    }
                }))
    }


}