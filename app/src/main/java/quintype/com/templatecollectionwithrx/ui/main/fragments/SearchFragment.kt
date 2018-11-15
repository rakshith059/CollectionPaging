package quintype.com.templatecollectionwithrx.ui.main.fragments

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import kotlinx.android.synthetic.main.author_list_fragment.*
import kotlinx.android.synthetic.main.search_list_fragment.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.SearchListAdapter
import quintype.com.templatecollectionwithrx.models.search.SearchStoryList
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.widgets.RecyclerItemDecorator
import quintype.com.templatecollectionwithrx.viewmodels.SearchListViewModel
import java.util.*


class SearchFragment : BaseFragment(), View.OnClickListener {
    private val REQ_CODE_SPEECH_INPUT: Int = 5001

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.search_list_fragment_iv_back ->
                OnBackPressed()
            R.id.search_list_fragment_iv_voice_search ->
                openSpeechToTextDialog()
        }
    }

    private fun openSpeechToTextDialog() {
        convertSpeechToText()
    }

    private fun convertSpeechToText() {
        var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, resources.getString(R.string.speech_prompt))

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (exception: Exception) {
            Toast.makeText(search_list_fragment_tet_search?.context, resources.getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                var result: ArrayList<String> = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val searchTerm = result.get(0)
                search_list_fragment_tet_search.setText(result.get(0))

                observeViewModel(searchListViewModel, searchTerm, 0)
            }
        }
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
        search_list_fragment_rv_recycler_view.addItemDecoration(RecyclerItemDecorator(false, 8, 8, 8, 8))
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
                        && totalItemCount >= Constants.STORY_LIMIT) {
                    val currentPage = totalItemCount / Constants.STORY_LIMIT

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
        if (searchTerm != null)
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
        else
            Toast.makeText(search_list_fragment_iv_voice_search.context, resources.getString(R.string.empty_search_term), Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mStoriesList?.clear()
        searchListAdapter = null
        searchListViewModel.mCompositeDisposable.dispose()
    }
}
