package quintype.com.templatecollectionwithrx.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.services.CollectionService
import quintype.com.templatecollectionwithrx.utils.ErrorHandler

class MainViewModel(collectionSlug: String, application: Application) : AndroidViewModel(application) {
    private val mCollectionSlug = collectionSlug
    private var collectionListObservable: LiveData<BulkTableModel>? = null

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getCollectionListObservable(): LiveData<BulkTableModel>? {
        return collectionListObservable
    }

    fun getCollectionLoadMoreResponse(currentPage: Int, errorHandler: ErrorHandler?) {
        collectionListObservable = CollectionService.getInstance().getCollectionResponse(mCollectionSlug, currentPage, errorHandler)
    }


    class Factory(application: Application, collectionSlug: String) : ViewModelProvider.NewInstanceFactory() {
        private val mCollectionSlug = collectionSlug
        private val mApplication = application

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(mCollectionSlug, mApplication) as T
        }
    }
}