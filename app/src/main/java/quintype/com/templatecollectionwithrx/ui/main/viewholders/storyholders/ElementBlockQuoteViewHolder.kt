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

class ElementBlockQuoteViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): ElementBlockQuoteViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.story_block_quote_element_holder, parent, false)
            return ElementBlockQuoteViewHolder(view)
        }
    }

    fun bind(storyElement: StoryElement) {
        var tvTextContent = itemView?.findViewById<TextView>(R.id.block_quote_holder_text_content)
        var tvTextAttribute = itemView?.findViewById<TextView>(R.id.block_quote_holder_attribute_text)
        tvTextContent?.text = storyElement?.subTypeMeta()?.content
        tvTextAttribute?.text = storyElement?.subTypeMeta()?.attribution
    }
}
