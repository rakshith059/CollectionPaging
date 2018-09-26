package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.models.storypresenter.ElementViewType
import quintype.com.templatecollectionwithrx.models.storypresenter.StoryPresenter
import quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders.*
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

class StoryDetailAdapter(story: Story?, fragmentCallbacks: FragmentCallbacks?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mStory: Story? = story
    private var mFragmentCallbacks = fragmentCallbacks

    var storyPresenter: StoryPresenter? = null

    init {
        storyPresenter = StoryPresenter.create(mStory)
//        storyPresenter?.insertElementBinder(0, ElementStoryHeroImageViewHolder::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ElementViewType.TEXT -> return ElementTextViewHolder.create(parent, mFragmentCallbacks)
            ElementViewType.IMAGE -> return ElementImageViewHolder.create(parent, mFragmentCallbacks)
            ElementViewType.GIF -> return ElementImageViewHolder.create(parent, mFragmentCallbacks)
            ElementViewType.BLURB -> return ElementBlurbViewHolder.create(parent)
            ElementViewType.QUOTE -> return ElementQuoteViewHolder.create(parent)
            ElementViewType.BLOCK_QUOTE -> return ElementBlockQuoteViewHolder.create(parent)
            ElementViewType.MEDIA -> return ElementWebViewHolder.create(parent)
            ElementViewType.AUDIO -> return ElementWebViewHolder.create(parent)
            ElementViewType.VIDEO -> return ElementWebViewHolder.create(parent)
            ElementViewType.SUMMARY -> return ElementStorySummaryHolder.create(parent)
            ElementViewType.QUESTION_ANSWER -> return ElementStoryQAHolder.create(parent)
            ElementViewType.BIG_FACT -> return ElementStoryBigFactViewHolder.create(parent)
            ElementViewType.JS_EMBED -> return ElementStoryJSEmbedViewHolder.create(parent)
            ElementViewType.SLIDESHOW -> return ElementStorySlideShowViewHolder.create(parent)
            ElementViewType.QUESTION -> return ElementStoryQuestionViewHolder.create(parent)
            ElementViewType.ANSWER -> return ElementStoryAnswerViewHolder.create(parent)
            ElementViewType.ALSO_READ -> return ElementStoryAlsoReadViewHolder.create(parent, mStory, mFragmentCallbacks)
            ElementViewType.TABLE -> return ElementStoryTableViewHolder.create(parent)
            ElementViewType.TWEET -> return ElementStoryTweetViewHolder.create(parent)
        }
        return ElementTextViewHolder.create(parent, mFragmentCallbacks)
    }

    override fun getItemCount(): Int {
        return storyPresenter?.elementCount as Int
    }

    override fun getItemViewType(position: Int): Int {
        return storyPresenter?.getElementViewType(position) as Int
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var storyElement = storyPresenter?.getElement(position) as StoryElement
        if (holder is ElementTextViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementImageViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementBlurbViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementBlockQuoteViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementQuoteViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementWebViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementStorySummaryHolder)
            holder.bind(storyElement)
        else if (holder is ElementStoryQAHolder)
            holder.bind(storyElement)
        else if (holder is ElementStoryBigFactViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementStoryJSEmbedViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementStorySlideShowViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementStoryQuestionViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementStoryAnswerViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementStoryAlsoReadViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementStoryTableViewHolder)
            holder.bind(storyElement)
        else if (holder is ElementStoryTweetViewHolder)
            holder.bind(storyElement)
    }
}