package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.retry_layout.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.HomeCollectionAdapter
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Constants.Companion.COLLECTION_SLUG
import quintype.com.templatecollectionwithrx.utils.EndlessRecyclerOnScrollListener
import quintype.com.templatecollectionwithrx.utils.ErrorHandler
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
import quintype.com.templatecollectionwithrx.viewmodels.MainViewModel

class SectionFragment : BaseFragment(), ErrorHandler {
    override var TAG = SectionFragment::class.java.simpleName
    lateinit var errorHandler: ErrorHandler
    var mCollectionSlug: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    /*Avoid using 'companion object', which will create issues when we create new instances*/
    fun newInstance(collectionSlug: String?, title: String?): SectionFragment {
        val sectionFragmentArgs = Bundle()
        sectionFragmentArgs.putString(COLLECTION_SLUG, collectionSlug)
        sectionFragmentArgs.putString(Constants.PAGE_TITLE, title)
        val sectionFragment = SectionFragment()
        sectionFragment.arguments = sectionFragmentArgs
        return sectionFragment
    }

    private var mainViewModel: MainViewModel? = null
    private var homeCollectionAdapter: HomeCollectionAdapter? = null
    private var linkedHashMap = LinkedHashMap<String, BulkTableModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.collection_fragment_layout, container, false)
        recyclerView = view.findViewById(R.id.collection_fragment_recycler_view)
        progressBar = view.findViewById(R.id.collection_fragment_progress_bar)
        swipeRefreshLayout = view.findViewById(R.id.collection_fragment_swipeContainer)

        if (homeCollectionAdapter != null) {/*If the user revisit the already created fragment this will get executed. Just binding the views again.*/
            recyclerView.adapter = homeCollectionAdapter
            recyclerView.addOnScrollListener(getEndlessScrollListener())
            homeCollectionAdapter?.notifyAdapter(linkedHashMap.values.toList())

            swipeRefreshLayout.setOnRefreshListener {
                Log.d(TAG, "On Pull to refresh - Slug " + mCollectionSlug)
                /*TODO Having issues, need to fix.*/
                homeCollectionAdapter = null
                linkedHashMap.clear()
                mainViewModel?.getCollectionLoadMoreResponse(0, errorHandler)
            }
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorHandler = this
        mCollectionSlug = arguments?.getString(COLLECTION_SLUG)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*To avoid taking screenshot*/
        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        /*Check for the internet connectivity before doing any operation.*/
        if (NetworkUtils.isConnected(activity?.applicationContext!!)) {
            /*Create viewModel and observe to it only when it is null.
             There is a possibility where only the view gets recreated, in those scenarios we need to make use of the data we have and bind to the newly created view.*/
            if (!TextUtils.isEmpty(mCollectionSlug) && mainViewModel == null) {
                Log.d(TAG, "onActivityCreated - Slug " + mCollectionSlug)
                val factory = MainViewModel.Factory(activity?.application!!, mCollectionSlug!!)
                mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

                swipeRefreshLayout.setOnRefreshListener {
                    Log.d(TAG, "On Pull to refresh - Slug " + mCollectionSlug)
                    homeCollectionAdapter = null
                    linkedHashMap.clear()
                    mainViewModel?.getCollectionLoadMoreResponse(0, errorHandler)
                }

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
                Log.d(TAG, "onLoadMore - Slug " + mCollectionSlug)
                mainViewModel?.getCollectionLoadMoreResponse(currentPage, errorHandler)
            }
        }
    }


    private fun observeViewModel() {
        val observer = Observer<BulkTableModel> {
            it?.let { it1 -> linkedHashMap?.put(it?.slug.toString(), it1) }
            Log.d(TAG, "On ObserveViewModel 0f slug $mCollectionSlug")
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

        retry_container?.visibility = VISIBLE
        error_message?.text = errorMessage
        retry_button?.setOnClickListener { v ->
            observeViewModel()
        }
    }

    private fun hideRetryLayout() {
        retry_container?.visibility = View.GONE
        swipeRefreshLayout?.visibility = VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        linkedHashMap.clear()
    }
}