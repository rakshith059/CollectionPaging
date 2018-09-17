package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

/**
 * Created TemplateCollectionWithRx by rakshith on 9/12/18.
 */

class ElementStoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): ElementStoryViewHolder {
            var view: View = LayoutInflater.from(parent.context).inflate(R.layout.story_text_element_holder, parent, false)
            return ElementStoryViewHolder(view)
        }
    }

    fun bind(storyElement: StoryElement) {
        var tvText = itemView?.findViewById<TextView>(R.id.story_text_element_holder_tv_text)
        if (storyElement.text() != null)
            tvText?.text = Html.fromHtml(storyElement?.text())
    }
}