package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.models.storypresenter.ElementViewType
import quintype.com.templatecollectionwithrx.models.storypresenter.StoryPresenter
import quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders.ElementImageViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders.ElementStoryViewHolder
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

class StoryDetailAdapter(story: Story?, fragmentCallbacks: FragmentCallbacks?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mStory: Story? = story
    private var mFragmentCallbacks = fragmentCallbacks

    var storyPresenter: StoryPresenter? = null

    init {
        storyPresenter = StoryPresenter.create(mStory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ElementViewType.TEXT -> return ElementStoryViewHolder.create(parent, mFragmentCallbacks)
            ElementViewType.IMAGE -> return ElementImageViewHolder.create(parent, mFragmentCallbacks)
        }
        return ElementStoryViewHolder.create(parent, mFragmentCallbacks)
    }

    override fun getItemCount(): Int {
        return storyPresenter?.elementCount as Int
    }

    override fun getItemViewType(position: Int): Int {
        return storyPresenter?.getElementViewType(position) as Int
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var storyElement = storyPresenter?.getElement(position) as StoryElement
        if (holder is ElementStoryViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementImageViewHolder)
            holder.bind(storyElement)
    }
}