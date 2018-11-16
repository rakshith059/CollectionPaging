package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import kotlinx.android.synthetic.main.custom_tool_bar.*
import kotlinx.android.synthetic.main.fragment_tag_list.*
import kotlinx.android.synthetic.main.retry_layout.*
import kotlinx.android.synthetic.main.search_list_fragment.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.SearchListAdapter
import quintype.com.templatecollectionwithrx.models.TagListResponse
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.EndlessRecyclerOnScrollListener
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
import quintype.com.templatecollectionwithrx.utils.widgets.RecyclerItemDecorator
import quintype.com.templatecollectionwithrx.utils.widgets.RecyclerviewGridItemDecorator
import quintype.com.templatecollectionwithrx.viewmodels.StoriesListViewModel


/**
 * Created TemplateCollectionWithRx by rakshith on 9/28/18.
 */

class TagListFragment : BaseFragment() {
    var isGrid: Boolean = false
    var isGridFirstTime: Boolean = true
    var isListFirstTime: Boolean = true

    companion object {
        var mTagName: String? = null
        const val TAG_NAME = "TAG_NAME"

        var mStoriesList: ArrayList<Story>? = null

        fun newInstance(tagName: String?): TagListFragment {
            val tagListFragment = TagListFragment()
            val args = Bundle()
            args.putString(TAG_NAME, tagName)
            tagListFragment.arguments = args
            return tagListFragment
        }
    }

    private lateinit var storiesListViewModel: StoriesListViewModel
    var searchListAdapter: SearchListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_tag_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mTagName = arguments?.getString(TAG_NAME)

        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        storiesListViewModel = ViewModelProviders.of(this).get(StoriesListViewModel::class.java)


        if (!TextUtils.isEmpty(mTagName)) {
            tag_list_swipeContainer.setOnRefreshListener {
                observeViewModel(storiesListViewModel, mTagName as String, 0, true)
            }

            observeViewModel(storiesListViewModel, mTagName as String, 0, false)

            custom_tool_bar_ll_main_container.visibility = View.VISIBLE
            custom_tool_bar_ll_main_container.setBackgroundColor(resources.getColor(R.color.colorPrimary))

            custom_tool_bar_iv_back_image.visibility = View.VISIBLE
            custom_tool_bar_iv_share_image.visibility = View.VISIBLE
            custom_tool_bar_tv_title.visibility = View.VISIBLE

            changeLayout()
            custom_tool_bar_iv_share_image.setOnClickListener {
                changeLayout()
            }

            custom_tool_bar_tv_title.text = mTagName
            custom_tool_bar_iv_back_image.setOnClickListener {
                OnBackPressed()
            }
        }
    }

    private fun changeLayout() {
        mStoriesList = ArrayList<Story>()

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        tag_list_recycler_view.layoutManager = linearLayoutManager

        searchListAdapter = null
        if (isGrid) {
            custom_tool_bar_iv_share_image.setImageDrawable(resources.getDrawable(R.drawable.ic_list_icon))
//            observeViewModel(storiesListViewModel, mTagName as String, 0, false, isGrid)
            val gridLayoutManager = GridLayoutManager(tag_list_recycler_view?.context, 2)
            /**
             * Added this to differentiate display child item with 2 grid
             */
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }
            if (isGridFirstTime) {
                tag_list_recycler_view.addItemDecoration(RecyclerviewGridItemDecorator(4))
                isGridFirstTime = false
            }
            tag_list_recycler_view.layoutManager = gridLayoutManager
            isGrid = false
        } else if (!isGrid) {
            custom_tool_bar_iv_share_image.setImageDrawable(resources.getDrawable(R.drawable.ic_grid_icon))
//            observeViewModel(storiesListViewModel, mTagName as String, 0, false, isGrid)

            val linearLayoutManager = LinearLayoutManager(activity)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            if (isListFirstTime) {
                tag_list_recycler_view.addItemDecoration(RecyclerItemDecorator(false, 8, 8, 8, 8))
                isListFirstTime = false
            }
            tag_list_recycler_view.layoutManager = linearLayoutManager
            isGrid = true
        }

    }

    private fun getEndlessScrollListener(): RecyclerView.OnScrollListener {
        return object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore(currentPage: Int) {
                tag_list_pb_end_progress.visibility = View.VISIBLE

                observeViewModel(storiesListViewModel, mTagName as String, currentPage, false)
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun observeViewModel(viewModel: StoriesListViewModel, searchTerm: String, mPageNumber: Int, refreshList: Boolean) {
        if (NetworkUtils.isConnected(activity?.applicationContext!!)) {
            viewModel.getStoriesListResponse(searchTerm, mPageNumber).subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : ResourceSubscriber<TagListResponse>() {
                        override fun onComplete() {
                            Log.d("Rakshith", " tag list api call completed..")
                            tag_list_progress_bar.visibility = View.GONE
                            tag_list_pb_end_progress.visibility = View.GONE

                            if (mStoriesList?.size as Int > 0) {
                                if (searchListAdapter == null) {
                                    searchListAdapter = SearchListAdapter(mStoriesList as ArrayList<Story>, fragmentCallbacks)
                                    tag_list_recycler_view?.adapter = searchListAdapter
                                    tag_list_recycler_view.addOnScrollListener(getEndlessScrollListener())
                                } else {
                                    searchListAdapter?.notifyAdapter(mStoriesList as ArrayList<Story>)
                                }
                            } else {
                                showNoDataMessage()
                            }
                        }

                        override fun onNext(tagListResponse: TagListResponse?) {
                            hideRetryLayout()

                            if (refreshList) {
                                tag_list_swipeContainer.setRefreshing(false)
                                mStoriesList?.clear()
                                searchListAdapter = null
                            }

                            tag_list_empty_text_view.visibility = View.GONE
                            for (index in 0 until tagListResponse?.stories?.size as Int)
                                mStoriesList?.add(tagListResponse?.stories?.get(index) as Story)

                        }

                        override fun onError(t: Throwable?) {
                            tag_list_swipeContainer.setRefreshing(false)
                            tag_list_pb_end_progress.visibility = View.GONE

                            showRetryLayout(viewModel, searchTerm, mPageNumber, refreshList, activity?.getText(R.string.oops))
                            Log.d("Rakshith", " tag list api call failed error is ${t?.message}")
                        }
                    })
        } else {
            /*Not connected to Network, show retry layout and hide the rest*/
            showRetryLayout(viewModel, searchTerm, mPageNumber, refreshList, activity?.getText(R.string.no_internet))
        }


    }

    private fun showRetryLayout(viewModel: StoriesListViewModel, searchTerm: String, mPageNumber: Int, refreshList: Boolean, errorMessage: CharSequence?) {
        tag_list_progress_bar.visibility = View.GONE
        tag_list_swipeContainer.visibility = View.GONE

        retry_container.visibility = View.VISIBLE
        error_message.text = errorMessage
        retry_button.setOnClickListener { v ->
            observeViewModel(viewModel, searchTerm, mPageNumber, refreshList)
        }
    }

    private fun hideRetryLayout() {
        retry_container.visibility = View.GONE
        tag_list_swipeContainer.visibility = View.VISIBLE
    }

    private fun showNoDataMessage() {
        tag_list_progress_bar.visibility = View.GONE
        tag_list_swipeContainer.visibility = View.GONE
        retry_container.visibility = View.GONE

        tag_list_empty_text_view.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mStoriesList?.clear()
        searchListAdapter = null
        storiesListViewModel.mCompositeDisposable.dispose()
    }
}