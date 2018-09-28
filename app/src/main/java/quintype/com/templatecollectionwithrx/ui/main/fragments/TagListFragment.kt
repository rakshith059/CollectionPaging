//package quintype.com.templatecollectionwithrx.ui.main.fragments
//
//import android.content.Context
//import android.os.Bundle
//import android.os.Parcelable
//import android.support.design.widget.Snackbar
//import android.support.v4.widget.SwipeRefreshLayout
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.FrameLayout
//import android.widget.ProgressBar
//import android.widget.Toast
//import quintype.com.templatecollectionwithrx.R
//import quintype.com.templatecollectionwithrx.models.story.Story
//import quintype.com.templatecollectionwithrx.models.story.Tag
//import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
//
//
///**
// * Created TemplateCollectionWithRx by rakshith on 9/28/18.
// */
//
//class TagListFragment : BaseFragment() {
//    internal var tagListAdapter: SearchListAdapter? = null
//    internal var recyclerOnScrollListener: RecyclerView.OnScrollListener? = null
//    internal var rvRecyclerView: RecyclerView? = null
//    internal var linearLayoutManager: LinearLayoutManager? = null
//    internal var llMainContainer: FrameLayout? = null
//    internal var progressBar: ProgressBar? = null
//    internal var swipeContainer: SwipeRefreshLayout? = null
//    private var tag: Tag? = null
//
//    /**
//     * To get the tag name from the container activity
//     *
//     * @return the tag name with which the stories in this fragment are tagged
//     */
//    val tagName: String
//        get() = tag?.name
//
//    private//                                recyclerOnScrollListener = getEndlessScrollListener();
//    //                                rvRecyclerView.addOnScrollListener(recyclerOnScrollListener);
//    //                                rvRecyclerView.setAdapter(storiesListAdapter);
//    //                                Constants.showProgressBar(false);
//    //                                storiesListAdapter.add(stories);
//    val endlessScrollListener: RecyclerView.OnScrollListener
//        get() = object : EndlessRecyclerOnScrollListener() {
//            fun onLoadMore(currentPage: Int) {
//                Timber.i("Loading more Tag stories ")
//
//                Quintype.story()
//                        .getStories()
//                        .tag(tag!!.name())
//                        .offset(Constants.DEFAULT_STORY_LIMIT * currentPage)
//                        .limit(Constants.DEFAULT_STORY_LIMIT)
//                        .execute(object : Callback<List<Story>>() {
//                            fun onSuccess(stories: List<Story>) {
//                                if (!isDetached && rvRecyclerView != null) {
//                                    tagListAdapter.add(stories)
//                                }
//                            }
//
//                            fun onFailure(t: Throwable) {
//
//                            }
//                        })
//            }
//        }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (arguments != null) {
//            tag = arguments?.getParcelable<Parcelable>(ARG_TAG)
//        }
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_tag_list, container, false)
//        rvRecyclerView = view.findViewById(R.id.fragment_tag_list_rv_recyclerview)
//        llMainContainer = view.findViewById(R.id.fragment_list_ll_main_container)
//        progressBar = view.findViewById(R.id.pb_tag_fragment)
//
//        swipeContainer = view.findViewById(R.id.swipeContainer)
//        // Setup refresh listener which triggers new data loading
//        swipeContainer?.setOnRefreshListener {
//            // Your code to refresh the list here.
//            // Make sure you call swipeContainer.setRefreshing(false)
//            // once the network request has completed successfully.
//            loadStoryIfInternetAvailable()
//        }
//        return view
//    }
//
//    private fun loadStoryIfInternetAvailable() {
//        if (NetworkUtils.isConnected(activity?.applicationContext as Context)) {
//            startLoading()
//        } else {
//            swipeContainer?.isRefreshing = false
//            val snackbar = Snackbar.make(llMainContainer, resources.getString(R
//                    .string.no_internet), Snackbar.LENGTH_INDEFINITE)
//            snackbar.setAction(resources.getString(R.string.retry), object : View.OnClickListener {
//                override fun onClick(view: View) {
//                    snackbar.dismiss()
//                    loadStoryIfInternetAvailable()
//                }
//            })
//            snackbar.show()
//        }
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        loadStoryIfInternetAvailable()
//
//        linearLayoutManager = LinearLayoutManager(activity)
//        linearLayoutManager?.orientation = LinearLayoutManager.VERTICAL
//        val r = activity?.resources
////        rvRecyclerView?.addItemDecoration(RecyclerItemDecorator(false, Utilities.dpToPx(r,
////                0), Utilities.dpToPx(r, Constants.PADDING_TOP), Utilities
////                .dpToPx(r, 0),
////                Utilities.dpToPx(r, Constants.PADDING_BOTTOM)))
//        rvRecyclerView!!.layoutManager = linearLayoutManager
//    }
//
//    fun startLoading() {
//
//        Quintype.story()
//                .getStories()
//                .tag(tag!!.name())
//                .limit(Constants.DEFAULT_STORY_LIMIT)
//                .execute(object : Callback<List<Story>>() {
//                    fun onSuccess(stories: List<Story>) {
//                        if (!isDetached && rvRecyclerView != null) {
//                            tagListAdapter = SearchListAdapter(activity, fragmentCallbacks,
//                                    Constants.CATEGORY_TAG_LIST_SCREEN)
//                            tagListAdapter.add(stories)
//                            rvRecyclerView!!.adapter = tagListAdapter
//                            rvRecyclerView!!.addOnScrollListener(endlessScrollListener)
//                            swipeContainer.isRefreshing = false
//                            progressBar.setVisibility(View.GONE)
//                        }
//                    }
//
//                    fun onFailure(t: Throwable) {
//                        if (!isDetached && rvRecyclerView != null) {
//                            swipeContainer.isRefreshing = false
//                            progressBar.setVisibility(View.GONE)
//                            Toast.makeText(activity!!.applicationContext, R.string
//                                    .fetch_failed, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                })
//    }
//
//    companion object {
//
//        val ARG_TAG = "tag_argument"
//
//        fun newInstance(tag: Tag): TagListFragment {
//            val fragment = TagListFragment()
//            val args = Bundle()
//            args.putParcelable(ARG_TAG, tag)
//            fragment.arguments = args
//            return fragment
//        }
//    }
//}