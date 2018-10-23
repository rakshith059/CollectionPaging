package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import com.example.androidcore.services.StoriesListService

class StoriesListViewModel : ViewModel() {
    var mCompositeDisposable = CompositeDisposable()
//    var mStoriesListObservable: LiveData<Story>? = null

    fun getStoriesListResponse(mSearchTerm: String, mPageNamber: Int) = StoriesListService.getInstance(mCompositeDisposable).getStoriesListResponse(mSearchTerm, mPageNamber)

//    fun getSearchListResponse(mSearchTerm: String, mPageNamber: Int) = StoriesListService.getInstance(mCompositeDisposable).getSearchStoryListResponse(mSearchTerm, mPageNamber)

//    fun getStoriesListObservable(): LiveData<Story>? {
//        return mStoriesListObservable
//    }
}