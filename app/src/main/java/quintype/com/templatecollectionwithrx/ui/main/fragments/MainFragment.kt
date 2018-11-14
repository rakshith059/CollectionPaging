@file:Suppress("UNREACHABLE_CODE")

package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    /*Avoid using 'companion object', which will create issues when we create new instances*/
    /*fun newInstance(collectionSlug: String?, title: String?): SectionFragment {
        val sectionFragmentArgs = Bundle()
        sectionFragmentArgs.putString(Constants.COLLECTION_SLUG, collectionSlug)
        sectionFragmentArgs.putString(Constants.PAGE_TITLE, title)
        val sectionFragment = SectionFragment()
        sectionFragment.arguments = sectionFragmentArgs
        return sectionFragment
    }*/

    private var mainViewModel: MainViewModel? = null
    private var homeCollectionAdapter: HomeCollectionAdapter? = null
    private var linkedHashMap = LinkedHashMap<String, BulkTableModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.collection_fragment_layout, container, false)
        /*The reason why we are creating variables for view is we can't manipulate the view directly by its 'id' inside onCreateView.*/
        recyclerView = view.findViewById(R.id.collection_fragment_recycler_view)
        progressBar = view.findViewById(R.id.collection_fragment_progress_bar)
        swipeRefreshLayout = view.findViewById(R.id.collection_fragment_swipeContainer)

        swipeRefreshLayout.setOnRefreshListener {
            homeCollectionAdapter = null
            linkedHashMap.clear()
            mainViewModel?.getCollectionLoadMoreResponse(0, errorHandler)
        }

        /*If the user revisit the already created fragment this will get executed. Just binding the views again.*/
        if (homeCollectionAdapter != null) {
            progressBar.visibility = View.GONE/*Setting it to gone by default*/

            if (linkedHashMap.size != 0) {
                /*We have some data to show so set the adapter*/
                recyclerView.adapter = homeCollectionAdapter
                recyclerView.addOnScrollListener(getEndlessScrollListener())
                homeCollectionAdapter?.notifyAdapter(linkedHashMap.values.toList())
            } else {
                /*We don't have any data to show*/
                showRetryLayout(this.resources.getString(R.string.oops))
            }

        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorHandler = this
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*To avoid taking screenshot*/
        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        /*Check for the internet connectivity before doing any operation.*/
        if (NetworkUtils.isConnected(activity?.applicationContext!!)) {
            /*Create viewModel and observe to it only when it is null.
             There is a possibility where only the view gets recreated, in those scenarios we need to make use of the data we have and bind to the newly created view.*/
            if (mainViewModel == null) {
                val factory = MainViewModel.Factory(activity?.application!!, Constants.COLLECTION_HOME)
                mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
                mainViewModel?.getCollectionLoadMoreResponse(0, errorHandler)
                observeViewModel()
            }
        } else {
            /* Not connected to Network, show retry layout and hide the rest*/
            showRetryLayout(activity?.getText(R.string.no_internet))
        }
    }

    private fun getEndlessScrollListener(): RecyclerView.OnScrollListener {
        return object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore(currentPage: Int) {
                mainViewModel?.getCollectionLoadMoreResponse(currentPage, errorHandler)
            }
        }
    }


    private fun observeViewModel() {
        val observer = Observer<BulkTableModel> {
            it?.let { it1 -> linkedHashMap?.put(it?.slug.toString(), it1) }
            if (homeCollectionAdapter == null) {
                homeCollectionAdapter = HomeCollectionAdapter(linkedHashMap?.values?.toList(), fragmentCallbacks)
                recyclerView?.adapter = homeCollectionAdapter
                recyclerView?.addOnScrollListener(getEndlessScrollListener())
            } else {
                homeCollectionAdapter?.notifyAdapter(linkedHashMap?.values?.toList())
            }
        }

        /*Be careful while adding observer to the LiveData.*/
        mainViewModel?.getCollectionListObservable()?.observe(this, observer)
    }

    override fun onAPISuccess() {
        hideRetryLayout()
        swipeRefreshLayout?.setRefreshing(false)
        progressBar?.visibility = View.GONE
    }

    override fun onAPIFailure() {
        progressBar?.visibility = View.GONE
        if (linkedHashMap.size == 0)
            showRetryLayout(this.resources.getString(R.string.oops))
    }

    private fun showRetryLayout(errorMessage: CharSequence?) {
        progressBar?.visibility = View.GONE
        swipeRefreshLayout?.visibility = View.GONE

        retry_container?.visibility = View.VISIBLE
        error_message?.text = errorMessage
        retry_button?.setOnClickListener { v ->
            mainViewModel?.getCollectionLoadMoreResponse(0, errorHandler)
        }
    }

    private fun hideRetryLayout() {
        retry_container?.visibility = View.GONE
        swipeRefreshLayout?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        linkedHashMap.clear()
    }
}
