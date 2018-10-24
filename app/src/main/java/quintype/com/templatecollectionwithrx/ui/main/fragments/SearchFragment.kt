package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.app.AlertDialog
import android.app.DialogFragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.facebook.common.util.UriUtil
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import kotlinx.android.synthetic.main.search_list_fragment.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.SearchListAdapter
import quintype.com.templatecollectionwithrx.models.search.SearchStoryList
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.viewmodels.SearchListViewModel


class SearchFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_list_fragment_iv_back ->
                OnBackPressed()
            R.id.search_list_fragment_iv_voice_search ->
                openSpeechToTextDialog()
        }
    }

    private fun openSpeechToTextDialog() {
        val builder = AlertDialog.Builder(search_list_fragment_iv_voice_search?.context)
        val dialogLayout = LayoutInflater.from(search_list_fragment_iv_voice_search?.context).inflate(R.layout.speech_dialog_fragment, null)
        val ivSpeechIcon = dialogLayout.findViewById<SimpleDraweeView>(R.id.speech_dialog_fragment_iv_ripple)

        var uri: Uri = Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(R.drawable.ic_ripple.toString())
                .build()

        var controller: DraweeController = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build()
        ivSpeechIcon.setController(controller);

        builder.setView(dialogLayout)
        builder.show()
    }

    companion object {
        var mStoriesList: ArrayList<Story>? = null

        fun newInstance(): SearchFragment {
            val searchStoriesListFragment = SearchFragment()
            val args = Bundle()
            searchStoriesListFragment.arguments = args
            return searchStoriesListFragment
        }
    }

    private lateinit var searchListViewModel: SearchListViewModel
    var searchListAdapter: SearchListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.search_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mStoriesList = ArrayList<Story>()

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        search_list_fragment_rv_recycler_view.layoutManager = layoutManager

        search_list_fragment_fl_main_container?.visibility = View.GONE
        search_list_fragment_ll_recent_search?.visibility = View.VISIBLE
        search_list_fragment_tv_no_recent_history?.visibility = View.VISIBLE

        search_list_fragment_tet_search?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                val searchTerm = search_list_fragment_tet_search?.text?.toString() as String
                observeViewModel(searchListViewModel, searchTerm, 0)

                true
            }
            false
        }

        search_list_fragment_iv_back?.setOnClickListener(this)
        search_list_fragment_iv_voice_search?.setOnClickListener(this)

//        mAuthorName = arguments?.getString(AUTHOR_NAME)
//        mAuthorImage = arguments?.getString(AUTHOR_IMAGE)
//
//        author_list_fragment_tv_author_name?.text = mAuthorName
//
//        if (mAuthorImage != null) {
//            fragment_author_list_app_bar_layout?.visibility = View.VISIBLE
//            fragment_author_list_iv_hero_image?.hierarchy = Utilities.getFriscoRoundImageHierarchy(activity?.applicationContext as Context, Constants.CIRCLE_IMAGE_BORDER_WIDTH_3F, resources?.getColor(R.color.black_opacity_25))
//            fragment_author_list_iv_hero_image.setImageURI(mAuthorImage)
//        }

        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        searchListViewModel = ViewModelProviders.of(this).get(SearchListViewModel::class.java)
//        if (!TextUtils.isEmpty(mAuthorName)) {
//            storiesListViewModel.getStoriesListResponse(mTagName as String, 0)

        search_list_fragment_rv_recycler_view?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                        observeViewModel(searchListViewModel, "modi", currentPage)
                    }
                }
            }
        })
//        }
    }

    private fun observeViewModel(viewModel: SearchListViewModel, searchTerm: String, mPageNumber: Int) {
        viewModel?.getSearchListResponse(searchTerm, mPageNumber)?.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ResourceSubscriber<SearchStoryList>() {
                    override fun onComplete() {
                        Log.d("Rakshith", " tag list api call completed..")

                        search_list_fragment_fl_main_container?.visibility = View.VISIBLE
                        search_list_fragment_ll_recent_search?.visibility = View.GONE
                        search_list_fragment_tv_no_recent_history?.visibility = View.GONE

                        if (searchListAdapter == null) {
                            searchListAdapter = SearchListAdapter(mStoriesList as ArrayList<Story>, fragmentCallbacks)
                            search_list_fragment_rv_recycler_view?.adapter = searchListAdapter
                        } else {
                            searchListAdapter?.notifyAdapter(mStoriesList as ArrayList<Story>)
                        }
                    }

                    override fun onNext(storiesSearchListResponse: SearchStoryList?) {

//                        val storiesByAuthor = storiesSearchListResponse?.getResults()?.total as Int
//                        var storiesByAuthorString: String? = null
//                        if (storiesByAuthor == 1)
//                            storiesByAuthorString = "$storiesByAuthor ${resources.getString(R.string.story_by)} $mAuthorName"
//                        else if (storiesByAuthor > 1)
//                            storiesByAuthorString = "$storiesByAuthor ${resources.getString(R.string.stories_by)} $mAuthorName"
//                        author_list_fragment_tv_total_stories?.text = storiesByAuthorString


                        for (index in 0 until storiesSearchListResponse?.getResults()?.stories?.size as Int)
                            mStoriesList?.add(storiesSearchListResponse?.getResults()?.stories?.get(index) as Story)
                    }

                    override fun onError(t: Throwable?) {
                        Log.d("Rakshith", " tag list api call failed error is ${t?.message}")
                    }
                })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mStoriesList?.clear()
        searchListAdapter = null
        searchListViewModel.mCompositeDisposable.dispose()
    }
}
