package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.services.StoryService

class StoryViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    fun getStoryDetailBySlug(storySlug: String) {
        StoryService.getInstance(compositeDisposable).getStoryResponse(storySlug)
    }
}
