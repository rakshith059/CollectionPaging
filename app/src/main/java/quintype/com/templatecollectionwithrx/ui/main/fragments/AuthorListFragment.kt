package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
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
import kotlinx.android.synthetic.main.author_list_fragment.*
import kotlinx.android.synthetic.main.fragment_story_detail.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.SearchListAdapter
import quintype.com.templatecollectionwithrx.models.search.SearchStoryList
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities
import quintype.com.templatecollectionwithrx.viewmodels.SearchListViewModel


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
//            storiesListViewModel.getStoriesListResponse(mTagName as String, 0)
            observeViewModel(searchListViewModel, mAuthorName as String, 0)

            fragment_author_list_rv_recycler_view?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount

                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (firstVisibleItemPosition + visibleItemCount >= totalItemCount && firstVisibleItemPosition >= 0
                            && totalItemCount >= Constants.PAGE_LIMIT) {
                        val currentPage = totalItemCount / Constants.PAGE_LIMIT

                        if (totalItemCount - 1 == layoutManager.findLastVisibleItemPosition()) {
                            Log.d("Rakshith", "current page is ===  $currentPage")
//                            storiesListViewModel.getStoriesListResponse(mTagName as String, currentPage)
                            observeViewModel(searchListViewModel, mAuthorName as String, currentPage)
                        }
                    }
                }
            })
        }
    }

    private fun observeViewModel(viewModel: SearchListViewModel, searchTerm: String, mPageNumber: Int) {
        viewModel?.getSearchListResponse(searchTerm, mPageNumber)?.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ResourceSubscriber<SearchStoryList>() {
                    override fun onComplete() {
                        Log.d("Rakshith", " tag list api call completed..")
                        fragment_author_list_pb_progress.visibility = View.GONE
                        if (searchListAdapter == null) {
                            searchListAdapter = SearchListAdapter(mStoriesList as ArrayList<Story>, fragmentCallbacks)
                            fragment_author_list_rv_recycler_view?.adapter = searchListAdapter
                        } else {
                            searchListAdapter?.notifyAdapter(mStoriesList as ArrayList<Story>)
                        }
                    }

                    override fun onNext(storiesSearchListResponse: SearchStoryList?) {
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
                        fragment_author_list_pb_progress.visibility = View.GONE
                        Log.d("Rakshith", " tag list api call failed error is ${t?.message}")
                    }
                })

//        viewModel.getStoriesListObservable()?.observe(this, Observer<Story>() {
//            mStoriesList?.add(it as Story)
//
//            if (searchListAdapter == null) {
//                searchListAdapter = SearchListAdapter(mStoriesList as ArrayList<Story>, fragmentCallbacks)
//                main_fragment_rv_collection_list?.adapter = searchListAdapter
//            } else {
//                searchListAdapter?.notifyAdapter(mStoriesList as ArrayList<Story>)
//            }
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mStoriesList?.clear()
        searchListAdapter = null
        searchListViewModel.mCompositeDisposable.dispose()
    }
}