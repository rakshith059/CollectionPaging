package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import com.example.androidcore.models.BulkTableModel
import com.example.androidcore.models.story.SlugStory
import com.example.androidcore.services.StoryService

class StoryViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    private var storyObservable: LiveData<SlugStory>? = null

    fun getStoryDetailBySlug(storySlug: String) =
            StoryService.getInstance(compositeDisposable).getStoryDetailResponse(storySlug)

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getStoryObservable(): LiveData<SlugStory>? {
        return storyObservable
    }
}
