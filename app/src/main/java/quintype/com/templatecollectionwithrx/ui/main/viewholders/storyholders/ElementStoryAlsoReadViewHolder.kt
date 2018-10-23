package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.story.Story
import com.example.androidcore.models.story.StoryElement
import quintype.com.templatecollectionwithrx.ui.main.fragments.StoryDetailFragment
import quintype.com.templatecollectionwithrx.ui.main.fragments.StoryPagerFragment
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import java.util.ArrayList

class ElementStoryAlsoReadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mStory: Story? = null
    var mAlsoReadHeadline: TextView? = null
    var mFragmentCallback: FragmentCallbacks? = null
    var mTemplateType: String? = null
    var mContext: Context? = null

    fun bind(element: StoryElement?) {
        mContext = itemView.context
        itemView.visibility = View.GONE
        mAlsoReadHeadline?.visibility = View.GONE
        setAlsoReadText(element)
    }

    private fun setAlsoReadText(element: StoryElement?) {
        val linkedStoryId = element?.subTypeMeta()?.linkedStoryId
        if (linkedStoryId != null) {
            val linkedStory = mStory?.linkedStories?.get(linkedStoryId)
            if (linkedStory?.headline() != null) {
                val spannableString = SpannableString(mContext?.resources?.getString(R.string.also_read) + ": " + linkedStory.headline())
                spannableString.setSpan(ForegroundColorSpan(mContext?.resources?.getColor(R.color.black_opacity_75) as Int), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                mAlsoReadHeadline?.text = spannableString

                itemView.visibility = View.VISIBLE
                mAlsoReadHeadline?.visibility = View.VISIBLE
                mAlsoReadHeadline?.setOnClickListener({ alsoReadSlugClick(linkedStory) })
            }
        }
    }

    private fun alsoReadSlugClick(linkedStory: Story) {
        mFragmentCallback?.addFragment(StoryPagerFragment.newInstance(getFilteredStoryList(linkedStory), 0),
                StoryDetailFragment::class.java.simpleName)
    }

    companion object {
        fun create(parent: ViewGroup, story: Story?, fragmentCallbacks: FragmentCallbacks?): ElementStoryAlsoReadViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_story_also_read_card, parent, false)
            val elementAlsoRead = ElementStoryAlsoReadViewHolder(view)
            elementAlsoRead.mStory = story
            elementAlsoRead.mFragmentCallback = fragmentCallbacks
            elementAlsoRead.mAlsoReadHeadline = view.findViewById(R.id.news_story_also_read_headline)
            return elementAlsoRead
        }
    }

    private fun getFilteredStoryList(mStory: Story): ArrayList<Story> {
        val filteredStories = ArrayList<Story>()
        filteredStories.add(mStory)
        return filteredStories
    }
}
