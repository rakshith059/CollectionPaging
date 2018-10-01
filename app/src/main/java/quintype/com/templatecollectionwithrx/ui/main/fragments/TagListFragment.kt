package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.main_fragment.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.SearchListAdapter
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.viewmodels.StoriesListViewModel


/**
 * Created TemplateCollectionWithRx by rakshith on 9/28/18.
 */

class TagListFragment : BaseFragment() {
    companion object {
        var mTagName: String? = null
        const val TAG_NAME = "TAG_NAME"

        val mStoriesList = ArrayList<Story>()

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
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(getActivity())
        main_fragment_rv_collection_list.layoutManager = layoutManager

        mTagName = arguments?.getString(TAG_NAME)

        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        storiesListViewModel = ViewModelProviders.of(this).get(StoriesListViewModel::class.java)
        if (!TextUtils.isEmpty(mTagName)) {
            storiesListViewModel.getStoriesListResponse(mTagName as String, 0)
            observeViewModel(storiesListViewModel)

            main_fragment_rv_collection_list?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                            storiesListViewModel.getStoriesListResponse(mTagName as String, currentPage)
                        }
                    }
                }
            })
        }
    }

    private fun observeViewModel(viewModel: StoriesListViewModel) {
        viewModel.getStoriesListObservable()?.observe(this, Observer<Story>() {
            mStoriesList.add(it as Story)

            if (searchListAdapter == null) {
                searchListAdapter = SearchListAdapter(mStoriesList, fragmentCallbacks)
                main_fragment_rv_collection_list?.adapter = searchListAdapter
            } else {
                searchListAdapter?.notifyAdapter(mStoriesList)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mStoriesList.clear()
        storiesListViewModel.mCompositeDisposable.dispose()
    }
}