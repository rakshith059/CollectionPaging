package quintype.com.templatecollectionwithrx.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import quintype.com.templatecollectionwithrx.models.story.SlugStory
import quintype.com.templatecollectionwithrx.services.StoryService

class StoryViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    fun getStoryDetailBySlug(storySlug: String): LiveData<SlugStory> {
        return StoryService.getInstance(compositeDisposable).getStoryResponse(storySlug)
    }
}
