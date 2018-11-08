package quintype.com.templatecollectionwithrx.services

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.collection.CollectionResponse
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.ErrorHandler

/**
 * Created TemplateCollectionWithRx by rakshith on 7/23/18.
 */

class CollectionService {
    companion object {
        var collectionService: CollectionService? = null
        var collectionApiService: CollectionApiService = RetrofitApiClient.getRetrofitApiClient().create(CollectionApiService::class.java)
        var collectionData: MutableLiveData<BulkTableModel> = MutableLiveData()
        var collectionModelList = ArrayList<BulkTableModel>()

        @Synchronized
        fun getInstance(): CollectionService {
            if (collectionService == null)
                collectionService = CollectionService()

            return collectionService as CollectionService
        }

        var TAG = CollectionService::class.java.simpleName
    }

    fun getCollectionResponse(collectionSlug: String, pageNumber: Int, errorHandler: ErrorHandler?): LiveData<BulkTableModel> {

        /*If pageNumber is '0' i.e it has been called for the first time or its being called when Pull to refresh is triggered.
        Reset the variables in this case, since the variables are declared global it will hold the old values.*/
        if (pageNumber == 0) {
            collectionData = MutableLiveData()
            collectionModelList.clear()
        }

        Log.d(TAG, "First Iteration Collection Slug - " + collectionSlug + " Limit - " + Constants.COLLECTION_LIMIT + " Offset - " + pageNumber * Constants.COLLECTION_LIMIT)
        Log.d(TAG, "CollectionModelList Size before API call - " + collectionModelList.size)
        val subscribeWith = collectionApiService.getCollectionApiService(collectionSlug, Constants.COLLECTION_LIMIT, pageNumber * Constants.COLLECTION_LIMIT, Constants.STORY_FIELDS)
                .doOnError { error -> Log.d(TAG, "error is " + error.message) }
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { mCollectionResponse ->
                    Log.d(TAG, "OnSuccess the total collectionItems is - " + mCollectionResponse.items?.size)
                    for (index in 0 until mCollectionResponse.items?.size as Int) {
                        val mCollectionItem = mCollectionResponse.items?.get(index)

                        if (mCollectionItem?.type == Constants.TYPE_COLLECTION) {
                            Log.d(TAG, "The " + index + "th collection Item is COLLECTION type, the slug is " + mCollectionItem.slug)
                            val bulkTableModel = BulkTableModel(mCollectionItem.slug,
                                    null,
                                    mCollectionItem.name,
                                    mCollectionItem.associatedMetadata,
                                    mCollectionItem.template,
                                    null,
                                    null,
                                    null)

                            collectionModelList.add(bulkTableModel)

                            collectionData.value = bulkTableModel

                        } else if (mCollectionItem?.type == Constants.TYPE_STORY) {
                            Log.d(TAG, "The " + index + "th collection Item is STORY type, the headline is " + mCollectionItem.story?.headline)
                            val bulkTableModel = BulkTableModel(mCollectionItem.story?.slug,
                                    mCollectionItem.story,
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
                }
                .filter { mCollectionItem -> mCollectionItem.type.equals(Constants.TYPE_COLLECTION) }
                .concatMapEager { mCollectionItem ->
                    var PAGE_LIMIT_CHILD = Constants.PAGE_LIMIT_CHILD
                    val noOfStoriesToShow = mCollectionItem.associatedMetadata?.associatedMetadataNumberOfStoriesToShow
                    if (noOfStoriesToShow != null && noOfStoriesToShow > 0) {
                        PAGE_LIMIT_CHILD = noOfStoriesToShow
                    }

//                    return@concatMapEager collectionApiService.getCollectionApiService(mCollectionItem?.slug as String, PAGE_LIMIT_CHILD, 0)
                    /**
                     * Using getCollectionOnlyStoriesApiService for getting only stories
                     */
                    return@concatMapEager collectionApiService.getCollectionOnlyStoriesApiService(mCollectionItem.slug as String, PAGE_LIMIT_CHILD, 0, Constants.TYPE_STORY, Constants.STORY_FIELDS)
                            .doOnError { error -> Log.d(TAG, "error is " + error.message) }
                            .retry(3)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                    override fun onComplete() {
                        Log.d(TAG, "onComplete of First iteration")
//                        collectionData.value = collectionModelList
                        Log.d(TAG, "CollectionModelList Size onComplete - " + collectionModelList.size)
                        errorHandler?.onAPISuccess()
                    }

                    override fun onNext(mCollectionsModel: CollectionResponse) {
                        val mCollectionSlug = mCollectionsModel.slug
                        val mCollectionItems = mCollectionsModel.items
                        val mCollectionSize = mCollectionItems?.size as Int

                        Log.d(TAG, " onNext of collectionItem slug - ${mCollectionsModel.slug}")

//                        if (mCollectionSize > 4)
//                            mCollectionSize = 4

                        for (index in 0 until collectionModelList.size) {
                            if (collectionModelList[index].slug?.equals(mCollectionSlug) == true) {
                                val bulkModel: BulkTableModel = collectionModelList[index]

                                if (mCollectionsModel.items?.size as Int > 0) {
                                    val innerCollectionFirstItem = mCollectionsModel.items?.get(0)

                                    bulkModel.outerCollectionInnerSlug = innerCollectionFirstItem?.slug
                                    bulkModel.outerCollectionInnerItem = innerCollectionFirstItem
                                }
                            }
                        }

                        for (index in 0 until mCollectionSize) {
                            val mCollectionItem = mCollectionItems.get(index)

                            if (index == 0 && mCollectionItem.type?.equals(Constants.TYPE_COLLECTION) as Boolean) {
                                if (mCollectionItem.template?.equals(Constants.WIDGET_TEMPLATE) == false) {
                                    getChildRxResponse(mCollectionItem.slug as String)
                                    //todo call child collection for 1st position if 0th position is widget
                                }
                            } else //todo call child collection for 1st position if 0th position is widget
                            {
                                if (mCollectionItem.type?.equals(Constants.TYPE_STORY) as Boolean) {
                                    for (collectionListIndex in 0 until collectionModelList.size) {
                                        if (collectionModelList.get(collectionListIndex).slug?.equals(mCollectionSlug) == true) {
                                            val bulkModel: BulkTableModel = collectionModelList.get(collectionListIndex)
                                            bulkModel.innerCollectionResponse = mCollectionsModel

                                            collectionData.value = bulkModel
                                        }
                                        //Log.d(TAG, "api call success and onNext collectionSlug == $mCollectionSlug &&  collectionStory headline is ${mCollectionItem.story?.headline}")
                                    }
                                }
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        errorHandler?.onAPIFailure()
                        Log.d(TAG, "First iteration API failed for collection SLUG " + collectionSlug + ", getting " + e.message)
                    }
                })
        return collectionData
    }

    fun getChildRxResponse(collectionSlug: String) {
        val collectionApiService: CollectionApiService = RetrofitApiClient.getRetrofitApiClient().create(CollectionApiService::class.java)
        Log.d(TAG, "Second Iteration Collection Slug - " + collectionSlug)
        val subscribeWith = collectionApiService.getCollectionOnlyStoriesApiService(collectionSlug, Constants.PAGE_LIMIT_CHILD, 0, Constants.TYPE_STORY, Constants.STORY_FIELDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap({ mCollectionResponse -> Flowable.fromIterable(mCollectionResponse.items) })
                .subscribeWith(object : ResourceSubscriber<CollectionResponse>() {
                    override fun onComplete() {
                        Log.d(TAG, " Second iteration api call completed")
                    }

                    override fun onNext(mCollectionsModel: CollectionResponse) {
                        val mCollectionSlug = mCollectionsModel.slug
                        val mCollectionItems = mCollectionsModel.items
                        val mCollectionSize = mCollectionItems?.size as Int

                        for (index in 0 until collectionModelList.size) {
                            if (collectionModelList[index].outerCollectionInnerSlug?.equals(mCollectionSlug) == true && collectionModelList[index].outerCollectionInnerSlug?.isEmpty() == false) {
                                val bulkModel: BulkTableModel = collectionModelList.get(index)
                                bulkModel.innerCollectionResponse = mCollectionsModel

                                collectionData.value = bulkModel
                            }
                        }
                        Log.d(TAG, "onNext of second iteration collectionItem slug - ${mCollectionsModel.slug}")

                        for (index in 0 until mCollectionSize) {
                            val mCollectionItem = mCollectionItems[index]

                            if (index == 0 && mCollectionItem.type?.equals(Constants.TYPE_COLLECTION) as Boolean) {
                                if (mCollectionItem.template?.equals(Constants.WIDGET_TEMPLATE) == false) {
                                    getChildRxResponse(mCollectionItem.slug as String)
                                }
                            } else if (mCollectionItem.type?.equals(Constants.TYPE_STORY) as Boolean) {
                                //Log.d(TAG, " second iteration api call success and onNext collectionSlug == $mCollectionSlug &&  collectionStory headline is ${mCollectionItem.story?.headline}")
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "Second iteration API failed for collection SLUG " + collectionSlug + ", getting " + e.message)
                    }
                })
    }
}