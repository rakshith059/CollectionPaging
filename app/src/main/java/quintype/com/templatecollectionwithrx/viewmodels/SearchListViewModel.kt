package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.services.StoriesListService

class SearchListViewModel : ViewModel() {
    var mCompositeDisposable = CompositeDisposable()

    fun getSearchListResponse(mSearchTerm: String, mPageNamber: Int) = StoriesListService.getInstance(mCompositeDisposable).getSearchStoryListResponse(mSearchTerm, mPageNamber)
}