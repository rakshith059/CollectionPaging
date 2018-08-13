package quintype.com.templatecollectionwithrx.services

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.CollectionResponse
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
                                    mCollectionItem?.associatedMetadata,
                                    mCollectionItem?.template,
                                    null,
                                    null,
                                    null)

                            collectionModelList.add(bulkTableModel)

                            collectionData.value = bulkTableModel

                        } else if (mCollectionItem?.type == Constants.TYPE_STORY) {
                            var bulkTableModel = BulkTableModel(mCollectionItem?.story?.slug,
                                    mCollectionItem?.story,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null)

//                            var collectionHashMap = collectionOrderHashMap.put(mCollectionItem?.story?.slug as String, bulkTableModel)

                            collectionModelList.add(bulkTableModel)
                            collectionData.value = bulkTableModel
                        }
                    }
                    Flowable.fromIterable(mCollectionResponse.items)
                })
                .filter({ mCollectionItem -> mCollectionItem.type.equals(Constants.TYPE_COLLECTION) })
                .concatMapEager({ mCollectionItem ->
                    var PAGE_LIMIT_CHILD = Constants.PAGE_LIMIT_CHILD
                    var noOfStoriesToShow = mCollectionItem.associatedMetadata?.associatedMetadataNumberOfStoriesToShow
                    if (noOfStoriesToShow != null && noOfStoriesToShow > 0) {
                        PAGE_LIMIT_CHILD = noOfStoriesToShow
                    }

                    return@concatMapEager collectionApiService.getCollectionApiService(mCollectionItem?.slug as String, PAGE_LIMIT_CHILD, 0)
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
                                    getChildRxResponse(mCollectionItem.slug as String, Constants.PAGE_LIMIT_CHILD, 0)
                                }
                            } else if (mCollectionItem.type?.equals(Constants.TYPE_STORY) as Boolean) {
                                for (collectionListIndex in 0 until collectionModelList.size) {
                                    if (collectionModelList.get(collectionListIndex)?.slug?.equals(mCollectionSlug) == true) {
                                        var bulkModel: BulkTableModel = collectionModelList.get(collectionListIndex)
                                        bulkModel.innerCollectionResponse = mCollectionsModel

                                        collectionData.value = bulkModel
                                    }
                                    Log.d("Rakshith", "api call success and onNext collectionSlug == $mCollectionSlug &&  collectionStory headline is ${mCollectionItem.story?.headline}")
                                }
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
                        Log.d("Rakshith", " second iteration collectionItem slug is ${mCollectionsModel.slug}")

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