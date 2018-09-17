package quintype.com.templatecollectionwithrx.services

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import quintype.com.templatecollectionwithrx.models.story.SlugStory

class StoryService {
    companion object {
        var storyServiceApi: StoryServiceApi = RetrofitApiClient.getRetrofitApiClient().create(StoryServiceApi::class.java)

        var storyServiceInstance: StoryService? = null
        var mCompositeDisposable: CompositeDisposable? = null
        var storyData: MutableLiveData<SlugStory> = MutableLiveData()

        @Synchronized
        fun getInstance(compositeDisposable: CompositeDisposable): StoryService {
            if (storyServiceInstance == null)
                storyServiceInstance = StoryService()

            if (compositeDisposable != null)
                mCompositeDisposable = CompositeDisposable()

            return storyServiceInstance as StoryService
        }
    }

    fun getStoryResponse(storySlug: String): LiveData<SlugStory> {
        mCompositeDisposable?.add(storyServiceApi.getStoryDetailBySlug(storySlug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                ?.subscribeWith(object :DisposableSubscriber<Story>(){})
                .subscribeWith(object : ResourceSubscriber<SlugStory>() {
                    override fun onComplete() {
                        Log.d("Rakshith", "getStoryResponse onCompleted")
                    }

                    override fun onNext(story: SlugStory) {
                        Log.d("Rakshith", "getStoryResponse onNext ${story.story.headline}")
                        storyData.value = story
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Rakshith", "getStoryResponse onError ${e.message}")
                    }
                }))

        return storyData
    }
}
