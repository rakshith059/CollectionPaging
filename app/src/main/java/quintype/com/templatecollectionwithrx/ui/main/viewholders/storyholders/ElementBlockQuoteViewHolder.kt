package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Utilities

class ElementBlockQuoteViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): ElementBlockQuoteViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.story_block_quote_element_holder, parent, false)
            return ElementBlockQuoteViewHolder(view)
        }
    }

    fun bind(storyElement: StoryElement) {
        var tvTextContent = itemView?.findViewById<TextView>(R.id.block_quote_holder_text_content)
        var tvTextAttribute = itemView?.findViewById<TextView>(R.id.block_quote_holder_attribute_text)
        if (storyElement?.subTypeMeta()?.content != null)
            tvTextContent?.text = Utilities.parseHtml(storyElement.subTypeMeta()?.content as String)
        if (storyElement?.subTypeMeta()?.attribution != null)
            tvTextAttribute?.text = storyElement.subTypeMeta()?.attribution
    }
}
