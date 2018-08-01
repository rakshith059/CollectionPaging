package quintype.com.templatecollectionwithrx.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_fragment.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.HomeCollectionAdapter
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.viewmodels.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: MainViewModel

    var linkedHashMap = LinkedHashMap<String, BulkTableModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getCollectionResponse()
        observeViewModel(viewModel)

        var layoutManager = LinearLayoutManager(getActivity());
        main_fragment_rv_collection_list.layoutManager = layoutManager

        main_fragment_rv_collection_list?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                var visibleItemCount = layoutManager.childCount
                var totalItemCount = layoutManager.itemCount

                var firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItemPosition + visibleItemCount >= totalItemCount && firstVisibleItemPosition >= 0
                        && totalItemCount >= Constants.PAGE_LIMIT) {
                    var currentPage = totalItemCount / Constants.PAGE_LIMIT

                    if (totalItemCount - 1 == layoutManager.findLastVisibleItemPosition()) {
                        Log.d("Rakshith", "current page is ===  $currentPage")
                        viewModel.getCollectionLoadMoreResponse(currentPage)
                    }
                }
            }
        })

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


        viewModel.getCollectionListObservable()?.observe(this, Observer<BulkTableModel>() {
            //            for (index in 0 until it?.size as Int) {
//                var slug = it?.get(index)?.slug

            it?.let { it1 -> linkedHashMap.put(it?.slug.toString(), it1) }
            var linkedCollectionList = linkedHashMap.values.toList()

            Log.d("Rakshith", "summary is ${it?.slug}")

            var collectionAdapter = HomeCollectionAdapter(linkedCollectionList)
            main_fragment_rv_collection_list?.adapter = collectionAdapter
//            }
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
