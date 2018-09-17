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

class ElementBlurbViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): ElementTextViewHolder {
            var view: View = LayoutInflater.from(parent.context).inflate(R.layout.story_blurb_element_holder, parent, false)
            return ElementTextViewHolder(view)
        }
    }

    fun bind(storyElement: StoryElement) {
        var tvText = itemView?.findViewById<TextView>(R.id.story_blurb_element_holder_tv_text)
        if (storyElement.text() != null)
            tvText?.text = Html.fromHtml(storyElement?.text())
    }
}
