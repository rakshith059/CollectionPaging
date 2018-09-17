package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.story.SlugStory
import quintype.com.templatecollectionwithrx.services.StoryService

class StoryViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    private var storyObservable: LiveData<SlugStory>? = null

    fun getStoryDetailBySlug(storySlug: String) {
        storyObservable = StoryService.getInstance(compositeDisposable).getStoryResponse(storySlug)
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getStoryObservable(): LiveData<SlugStory>? {
        return storyObservable
    }
}
