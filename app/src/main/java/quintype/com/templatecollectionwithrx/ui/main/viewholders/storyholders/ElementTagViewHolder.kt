package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.fragments.TagListFragment
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.widgets.FlowLayout


class ElementTagViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    private var tagsLayout: FlowLayout? = null
    var mFragmentCallbacks: FragmentCallbacks? = null

    companion object {
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): ElementTagViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_tag_view_holder, parent, false)
            val elementTagViewHolder = ElementTagViewHolder(view)
//            elementTagViewHolder.story = story
            elementTagViewHolder.tagsLayout = view.findViewById(R.id.story_tag_view_holder_flow_layout)
            elementTagViewHolder.mFragmentCallbacks = mFragmentCallbacks
            return elementTagViewHolder
        }
    }

    fun bind(mStory: Story) {
        itemView.visibility = View.GONE
        tagsLayout?.visibility = View.GONE

        if (mStory.hasTags()) {
            itemView.visibility = View.VISIBLE
            tagsLayout?.visibility = View.VISIBLE
            tagsLayout?.removeAllViews()
            for (storyTag in mStory.tags()) {
                val tagView = LayoutInflater.from(tagsLayout?.context).inflate(R.layout.tag_item_layout, tagsLayout, false) as FrameLayout
                val tvTagName = tagView.findViewById<TextView>(R.id.tag_name)
                tvTagName.text = storyTag.name
//                tagView.tag = tag
                tagView.setOnClickListener {
                    mFragmentCallbacks?.addFragment(TagListFragment.newInstance(storyTag.name),
                            TagListFragment::class.java?.getName() + " : " + storyTag.name)
//                    fragmentCallbacks.clickAnalyticsEvent(Constants
//                            .CATEGORY_STORY_DETAIL_SCREEN, Constants.EVENT_TAG_CLICK, tag
//                            .name(), 0)
                }
                tagsLayout?.addView(tagView)
            }
        }
    }
}
