package quintype.com.templatecollectionwithrx.services

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants

class StoriesListService {
    companion object {
        var storiesListService = StoriesListService()
        var storiesListApiService: StoriesListApiService = RetrofitApiClient.getRetrofitApiClient().create(StoriesListApiService::class.java)
        var mCompositeDisposable: CompositeDisposable? = null

        var mStoriesListData: MutableLiveData<Story> = MutableLiveData()

        @Synchronized
        fun getInstance(compositeDisposable: CompositeDisposable): StoriesListService {
            if (compositeDisposable != null)
                mCompositeDisposable = compositeDisposable

            if (storiesListService == null)
                storiesListService = StoriesListService()

            return storiesListService
        }
    }

    fun getStoriesListResponse(searchTerm: String, pageNumber: Int) = storiesListApiService.getTagStoriesList(searchTerm, Constants.STORY_LIMIT, pageNumber * Constants.STORY_LIMIT)

    fun getSearchStoryListResponse(searchTerm: String, pageNumber: Int) = storiesListApiService.getSearchStoriesList(searchTerm, Constants.STORY_LIMIT, pageNumber * Constants.STORY_LIMIT)

//    fun getStoriesListResponse(searchTerm: String, pageNumber: Int): LiveData<Story> {
//        mCompositeDisposable?.add(storiesListApiService.getTagStoriesList(searchTerm, Constants.STORY_LIMIT, pageNumber * Constants.STORY_LIMIT)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : ResourceSubscriber<TagListResponse>() {
//                    override fun onComplete() {
//                        Log.d("Rakshith", " tag list api call completed..")
//                    }
//
//                    override fun onNext(tagListResponse: TagListResponse?) {
//                        for (index in 0 until tagListResponse?.stories?.size as Int)
//                            mStoriesListData.value = tagListResponse.stories?.get(index)
//                    }
//
//                    override fun onError(t: Throwable?) {
//                        Log.d("Rakshith", " tag list api call failed error is ${t?.message}")
//                    }
//                }))
//        return mStoriesListData
//    }
}
