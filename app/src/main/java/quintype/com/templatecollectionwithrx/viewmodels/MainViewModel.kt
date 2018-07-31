package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.CollectionResponse
import quintype.com.templatecollectionwithrx.services.CollectionService

class MainViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    private var collectionListObservable: LiveData<List<BulkTableModel>>? = null

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getCollectionListObservable(): LiveData<List<BulkTableModel>>? {
        return collectionListObservable
    }

    fun getCollectionResponse() {
        collectionListObservable = CollectionService.getInstance(compositeDisposable).getCollectionResponse()
//        CollectionService.getInstance(compositeDisposable).getChildRxResponse("home", Constants.PAGE_LIMIT, 0)
    }
}