package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.collection_fragment_layout.*
import kotlinx.android.synthetic.main.retry_layout.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.HomeCollectionAdapter
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.EndlessRecyclerOnScrollListener
import quintype.com.templatecollectionwithrx.utils.ErrorHandler
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
import quintype.com.templatecollectionwithrx.viewmodels.MainViewModel

class SectionFragment : BaseFragment(), ErrorHandler {
    override var TAG = SectionFragment::class.java.simpleName
    lateinit var errorHandler: ErrorHandler

    companion object {
        var mCollectionSlug: String? = null
        const val COLLECTION_SLUG = "COLLECTION_SLUG"

        fun newInstance(collectionSlug: String?, title: String?): SectionFragment {
            val sectionFragment = SectionFragment()
            val args = Bundle()
            args.putString(COLLECTION_SLUG, collectionSlug)
            args.putString(Constants.PAGE_TITLE, title)
            sectionFragment.arguments = args
            return sectionFragment
        }
    }

    private lateinit var viewModel: MainViewModel
    var collectionAdapter: HomeCollectionAdapter? = null
    var linkedHashMap = LinkedHashMap<String, BulkTableModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.collection_fragment_layout, container, false)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume - Slug " + mCollectionSlug)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        errorHandler = this
        mCollectionSlug = arguments?.getString(COLLECTION_SLUG)

        /*To avoid taking screenshot*/
        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)

        val factory = MainViewModel.Factory(activity?.application!!, mCollectionSlug!!)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        if (!TextUtils.isEmpty(mCollectionSlug)) {
            Log.d(TAG, "onActivityCreated - Slug " + mCollectionSlug)
            viewModel.getCollectionLoadMoreResponse(0, errorHandler)

            collection_fragment_swipeContainer.setOnRefreshListener {
                Log.d(TAG, "On Pull to refresh - Slug " + mCollectionSlug)
                collectionAdapter = null
                linkedHashMap.clear()
                viewModel.getCollectionLoadMoreResponse(0, errorHandler)
            }

            if (NetworkUtils.isConnected(activity?.applicationContext!!)) {
                observeViewModel(viewModel)
            } else {
                /* Not connected to Network, show retry layout and hide the rest*/
                showRetryLayout(viewModel, activity?.getText(R.string.no_internet))
            }
        }
    }

    private fun getEndlessScrollListener(): RecyclerView.OnScrollListener {
        return object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore(currentPage: Int) {
                Log.d(TAG, "onLoadMore - Slug " + mCollectionSlug)
                viewModel.getCollectionLoadMoreResponse(currentPage, errorHandler)
            }
        }
    }

    private fun observeViewModel(viewModel: MainViewModel) {
        viewModel.getCollectionListObservable()?.observe(this, Observer<BulkTableModel>() {

            it?.let { it1 -> linkedHashMap?.put(it?.slug.toString(), it1) }

            Log.d(TAG, "summary is ${it?.slug}")
            if (collectionAdapter == null) {
                collectionAdapter = HomeCollectionAdapter(linkedHashMap?.values?.toList(), fragmentCallbacks)
                collection_fragment_recycler_view?.adapter = collectionAdapter
                collection_fragment_recycler_view.addOnScrollListener(getEndlessScrollListener())
            } else {
                collectionAdapter?.notifyAdapter(linkedHashMap?.values?.toList())
            }
        })
    }

    override fun onAPISuccess() {
        hideRetryLayout()
        collection_fragment_swipeContainer.setRefreshing(false)
        collection_fragment_progress_bar.visibility = View.GONE
    }

    override fun onAPIFailure() {
        collection_fragment_progress_bar.visibility = View.GONE
        if (linkedHashMap.size == 0)
            showRetryLayout(viewModel, this.resources.getString(R.string.oops))
    }

    private fun showRetryLayout(viewModel: MainViewModel, errorMessage: CharSequence?) {
        collection_fragment_progress_bar.visibility = View.GONE
        collection_fragment_swipeContainer.visibility = View.GONE

        retry_container.visibility = View.VISIBLE
        error_message.text = errorMessage
        retry_button.setOnClickListener { v ->
            observeViewModel(viewModel)
        }
    }

    private fun hideRetryLayout() {
        retry_container.visibility = View.GONE
        collection_fragment_swipeContainer.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        linkedHashMap.clear()
    }
}