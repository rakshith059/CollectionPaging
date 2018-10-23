package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import com.example.androidcore.services.StoriesListService

class SearchListViewModel : ViewModel() {
    var mCompositeDisposable = CompositeDisposable()

    fun getSearchListResponse(mSearchTerm: String, mPageNamber: Int) = StoriesListService.getInstance(mCompositeDisposable).getSearchStoryListResponse(mSearchTerm, mPageNamber)
}