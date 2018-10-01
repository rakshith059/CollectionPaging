package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.models.TagListResponse
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.services.StoriesListService

class StoriesListViewModel : ViewModel() {
    var mCompositeDisposable = CompositeDisposable()
    var mStoriesListObservable: LiveData<Story>? = null

    fun getStoriesListResponse(mSearchTerm: String, mPageNamber: Int) {
        mStoriesListObservable = StoriesListService.getInstance(mCompositeDisposable).getStoriesListResponse(mSearchTerm, mPageNamber)
    }

    fun getStoriesListObservable(): LiveData<Story>? {
        return mStoriesListObservable
    }
}
