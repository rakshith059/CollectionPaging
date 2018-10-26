package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import kotlinx.android.synthetic.main.author_list_fragment.*
import kotlinx.android.synthetic.main.retry_layout.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.SearchListAdapter
import quintype.com.templatecollectionwithrx.models.search.SearchStoryList
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils
import quintype.com.templatecollectionwithrx.viewmodels.SearchListViewModel
import com.google.android.youtube.player.internal.i
import kotlinx.android.synthetic.main.custom_tool_bar.*
import kotlinx.android.synthetic.main.fragment_story_detail.*
import quintype.com.templatecollectionwithrx.utils.EndlessRecyclerOnScrollListener


/**
 * Created TemplateCollectionWithRx by rakshith on 9/28/18.
 */

class AuthorListFragment : BaseFragment() {
    companion object {
        var mAuthorName: String? = null
        var mAuthorImage: String? = null
        const val AUTHOR_NAME = "AUTHOR_NAME"
        const val AUTHOR_IMAGE = "AUTHOR_IMAGE"

        var mStoriesList: ArrayList<Story>? = null

        fun newInstance(authorName: String?, authorImageUrl: String?): AuthorListFragment {
            val authorStoriesListFragment = AuthorListFragment()
            val args = Bundle()
            args.putString(AUTHOR_NAME, authorName)
            args.putString(AUTHOR_IMAGE, authorImageUrl)
            authorStoriesListFragment.arguments = args
            return authorStoriesListFragment
        }
    }

    private lateinit var searchListViewModel: SearchListViewModel
    var searchListAdapter: SearchListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.author_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mStoriesList = ArrayList<Story>()

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        fragment_author_list_rv_recycler_view.layoutManager = layoutManager

        mAuthorName = arguments?.getString(AUTHOR_NAME)
        mAuthorImage = arguments?.getString(AUTHOR_IMAGE)

        author_list_fragment_tv_author_name?.text = mAuthorName

        if (mAuthorImage != null) {
            fragment_author_list_app_bar_layout?.visibility = View.VISIBLE
            fragment_author_list_iv_hero_image?.hierarchy = Utilities.getFriscoRoundImageHierarchy(activity?.applicationContext as Context, Constants.CIRCLE_IMAGE_BORDER_WIDTH_3F, resources?.getColor(R.color.black_opacity_25))
            fragment_author_list_iv_hero_image.setImageURI(mAuthorImage)
        }

        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        searchListViewModel = ViewModelProviders.of(this).get(SearchListViewModel::class.java)
        if (!TextUtils.isEmpty(mAuthorName)) {
            author_list_swipeContainer.setOnRefreshListener {
                observeViewModel(searchListViewModel, mAuthorName as String, 0, true)
            }
            observeViewModel(searchListViewModel, mAuthorName as String, 0, false)

            (activity as AppCompatActivity).setSupportActionBar(fragment_author_list_toolbar)
//        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            fragment_author_list_collapsing_toolbar_layout?.setContentScrimColor(resources.getColor(R.color.colorPrimary))

            custom_tool_bar_iv_back_image.visibility = View.VISIBLE
            custom_tool_bar_iv_share_image.visibility = View.INVISIBLE

            custom_tool_bar_tv_title.text = mAuthorName
            custom_tool_bar_iv_back_image.setOnClickListener {
                OnBackPressed()
            }

            /**
             * checking whether a toolbar is collapsed or not
             */
            fragment_author_list_app_bar_layout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                // toolbar is collapsed
                if (Math.abs(verticalOffset) == appBarLayout.totalScrollRange)
                    custom_tool_bar_tv_title.visibility = View.VISIBLE
                // toolbar is expanded
                else
                    custom_tool_bar_tv_title.visibility = View.INVISIBLE
            }
        }
    }

    private fun getEndlessScrollListener(): RecyclerView.OnScrollListener {
        return object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore(currentPage: Int) {
                observeViewModel(searchListViewModel, mAuthorName as String, currentPage, false)

            }
        }
    }

    @SuppressLint("CheckResult")
    private fun observeViewModel(viewModel: SearchListViewModel, searchTerm: String, mPageNumber: Int, refreshList: Boolean) {
        if (NetworkUtils.isConnected(activity?.applicationContext!!)) {
            viewModel?.getSearchListResponse(searchTerm, mPageNumber)?.subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : ResourceSubscriber<SearchStoryList>() {
                        override fun onComplete() {
                            Log.d("Rakshith", " tag list api call completed..")
                            author_list_progress_bar.visibility = View.GONE

                            if (mStoriesList?.size!! > 0) {
                                if (searchListAdapter == null) {
                                    searchListAdapter = SearchListAdapter(mStoriesList as ArrayList<Story>, fragmentCallbacks)
                                    fragment_author_list_rv_recycler_view?.adapter = searchListAdapter
                                    fragment_author_list_rv_recycler_view.addOnScrollListener(getEndlessScrollListener())
                                } else {
                                    searchListAdapter?.notifyAdapter(mStoriesList as ArrayList<Story>)
                                }
                            } else {
                                showNoDataMessage()
                            }
                        }

                        override fun onNext(storiesSearchListResponse: SearchStoryList?) {
                            hideRetryLayout()

                            if (refreshList) {
                                author_list_swipeContainer.setRefreshing(false)
                                mStoriesList?.clear()
                                searchListAdapter = null
                            }
                            val storiesByAuthor = storiesSearchListResponse?.getResults()?.total as Int
                            var storiesByAuthorString: String? = null
                            if (storiesByAuthor == 1)
                                storiesByAuthorString = "$storiesByAuthor ${resources.getString(R.string.story_by)} $mAuthorName"
                            else if (storiesByAuthor > 1)
                                storiesByAuthorString = "$storiesByAuthor ${resources.getString(R.string.stories_by)} $mAuthorName"
                            author_list_fragment_tv_total_stories?.text = storiesByAuthorString

                            for (index in 0 until storiesSearchListResponse?.getResults()?.stories?.size as Int)
                                mStoriesList?.add(storiesSearchListResponse?.getResults()?.stories?.get(index) as Story)
                        }

                        override fun onError(t: Throwable?) {
                            author_list_progress_bar.visibility = View.GONE

                            showRetryLayout(viewModel, searchTerm, mPageNumber, refreshList, activity?.getText(R.string.oops))
                            Log.d("Rakshith", " tag list api call failed error is ${t?.message}")
                        }
                    })
        } else {
            /*Not connected to Network, show retry layout and hide the rest*/
            showRetryLayout(viewModel, searchTerm, mPageNumber, refreshList, activity?.getText(R.string.no_internet))
        }
    }

    private fun showRetryLayout(viewModel: SearchListViewModel, searchTerm: String, mPageNumber: Int, refreshList: Boolean, errorMessage: CharSequence?) {
        author_list_progress_bar.visibility = View.GONE
        author_list_swipeContainer.visibility = View.GONE

        retry_container.visibility = View.VISIBLE
        error_message.text = errorMessage
        retry_button.setOnClickListener { v ->
            observeViewModel(viewModel, searchTerm, mPageNumber, refreshList)
        }
    }

    private fun hideRetryLayout() {
        retry_container.visibility = View.GONE
        author_list_swipeContainer.visibility = View.VISIBLE
    }

    private fun showNoDataMessage() {
        author_list_progress_bar.visibility = View.GONE
        author_list_swipeContainer.visibility = View.GONE
        retry_container.visibility = View.GONE

        author_list_empty_text_view.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mStoriesList?.clear()
        searchListAdapter = null
        searchListViewModel.mCompositeDisposable.dispose()
    }
}