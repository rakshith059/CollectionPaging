package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.services.CollectionService
import quintype.com.templatecollectionwithrx.utils.ErrorHandler

class MainViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    private var collectionListObservable: LiveData<BulkTableModel>? = null

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getCollectionListObservable(): LiveData<BulkTableModel>? {
        return collectionListObservable
    }

    fun getCollectionResponse(collectionSlug: String, errorHandler: ErrorHandler?) {
        collectionListObservable = CollectionService.getInstance(compositeDisposable).getCollectionResponse(collectionSlug, 0, errorHandler)
//        CollectionService.getInstance(compositeDisposable).getChildRxResponse("home", Constants.STORY_LIMIT, 0)
    }

    fun getCollectionLoadMoreResponse(collectionSlug: String, currentPage: Int, errorHandler: ErrorHandler?) {
        collectionListObservable = CollectionService.getInstance(compositeDisposable).getCollectionResponse(collectionSlug, currentPage, errorHandler)
    }
}