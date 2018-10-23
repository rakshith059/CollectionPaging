package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import com.example.androidcore.models.BulkTableModel
import com.example.androidcore.services.CollectionService

class MainViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    private var collectionListObservable: LiveData<BulkTableModel>? = null

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getCollectionListObservable(): LiveData<BulkTableModel>? {
        return collectionListObservable
    }

    fun getCollectionResponse(collectionSlug: String) {
        collectionListObservable = CollectionService.getInstance(compositeDisposable).getCollectionResponse(collectionSlug, 0)
//        CollectionService.getInstance(compositeDisposable).getChildRxResponse("home", Constants.PAGE_LIMIT, 0)
    }

    fun getCollectionLoadMoreResponse(collectionSlug: String, currentPage: Int) {
        collectionListObservable = CollectionService.getInstance(compositeDisposable).getCollectionResponse(collectionSlug, currentPage)
    }
}