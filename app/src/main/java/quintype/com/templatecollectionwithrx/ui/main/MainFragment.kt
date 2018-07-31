package quintype.com.templatecollectionwithrx.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.services.CollectionService
import quintype.com.templatecollectionwithrx.viewmodels.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getCollectionResponse()
        observeViewModel(viewModel)

//        getCollectionResponse()
    }

    private fun observeViewModel(viewModel: MainViewModel) {
//        if (viewModel.getCollectionListObservable() != null && viewModel.getCollectionListObservable()?.size as Int > 0) {
//            var viewmodelobservable = viewModel.getCollectionListObservable()?.last()
//            var hashmapKeys = viewmodelobservable?.keys
//
//            for (index in 0 until hashmapKeys?.size as Int) {
//                Log.d("Rakshith", " hashmap keys is ${hashmapKeys.indices}")
//            }
//        }


        viewModel.getCollectionListObservable()?.observe(this, Observer<List<BulkTableModel>>() {
            for (index in 0 until it?.size as Int) {
                var slug = it?.get(index)?.slug

                Log.d("Rakshith", "summary at $index is $slug")
            }
        })
    }

//    fun getCollectionResponse() {
//        var collectionResponse = CollectionService.getInstance(compositeDisposable).getCollectionResponse()
//    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.compositeDisposable.dispose()
    }
}
