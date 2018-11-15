@file:Suppress("UNREACHABLE_CODE")

package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.retry_layout.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.HomeCollectionAdapter
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.EndlessRecyclerOnScrollListener
import quintype.com.templatecollectionwithrx.utils.ErrorHandler
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
import quintype.com.templatecollectionwithrx.viewmodels.MainViewModel

class MainFragment : BaseFragment(), ErrorHandler {
    lateinit var errorHandler: ErrorHandler

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putString(Constants.PAGE_TITLE, "HOME")
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: MainViewModel
    var collectionAdapter: HomeCollectionAdapter? = null
    var linkedHashMap = LinkedHashMap<String, BulkTableModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        errorHandler = this
        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getCollectionLoadMoreResponse(Constants.COLLECTION_HOME, 0, errorHandler)


        home_fragment_swipeContainer.setOnRefreshListener {
            collectionAdapter = null
            linkedHashMap.clear()
            viewModel.getCollectionLoadMoreResponse(Constants.COLLECTION_HOME, 0, errorHandler)
        }

        main_fragment_rv_collection_list.layoutManager = LinearLayoutManager(getActivity())

        if (NetworkUtils.isConnected(activity?.applicationContext!!)) {
            observeViewModel(viewModel)
        } else {
            /* Not connected to Network, show retry layout and hide the rest*/
            showRetryLayout(viewModel, activity?.getText(R.string.no_internet))
        }
    }

    private fun getEndlessScrollListener(): RecyclerView.OnScrollListener {
        return object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore(currentPage: Int) {
                viewModel.getCollectionLoadMoreResponse(Constants.COLLECTION_HOME, currentPage, errorHandler)
            }
        }
    }

    private fun observeViewModel(viewModel: MainViewModel) {
        viewModel.getCollectionListObservable()?.observe(this, Observer<BulkTableModel>() {
            it?.let { it1 -> linkedHashMap.put(it?.slug.toString(), it1) }
            val linkedCollectionList = linkedHashMap.values.toList()

            //Log.d("Rakshith", "summary is ${it?.slug}")
            if (linkedHashMap.size < Constants.COLLECTION_LIMIT) {
                collectionAdapter = HomeCollectionAdapter(linkedCollectionList, fragmentCallbacks)
                main_fragment_rv_collection_list?.adapter = collectionAdapter
                main_fragment_rv_collection_list.addOnScrollListener(getEndlessScrollListener())
            } else {
                collectionAdapter?.notifyAdapter(linkedCollectionList)
            }
        })
    }

    override fun onAPISuccess() {
        hideRetryLayout()
        home_fragment_swipeContainer.setRefreshing(false)
        home_fragment_progress_bar.visibility = View.GONE
    }

    override fun onAPIFailure() {
        if (linkedHashMap.size == 0)
            showRetryLayout(viewModel, this.resources.getString(R.string.oops))
    }

    private fun showRetryLayout(viewModel: MainViewModel, errorMessage: CharSequence?) {
        home_fragment_progress_bar.visibility = View.GONE
        home_fragment_swipeContainer.visibility = View.GONE

        retry_container.visibility = View.VISIBLE
        error_message.text = errorMessage
        retry_button.setOnClickListener { v ->
            observeViewModel(viewModel)
        }
    }

    private fun hideRetryLayout() {
        retry_container.visibility = View.GONE
        home_fragment_swipeContainer.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.compositeDisposable.dispose()
    }
}
