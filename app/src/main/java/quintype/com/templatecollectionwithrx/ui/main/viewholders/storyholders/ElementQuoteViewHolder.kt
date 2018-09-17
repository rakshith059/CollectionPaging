package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.AlignmentSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

class ElementQuoteViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup, mFragmentCallbacks: FragmentCallbacks?): ElementQuoteViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.story_quote_element_holder, parent, false)
            return ElementQuoteViewHolder(view)
        }
    }

    fun bind(storyElement: StoryElement) {
        var tvTextContent = itemView?.findViewById<TextView>(R.id.quote_holder_text_content)

        val subtypeContent = storyElement?.subTypeMeta()?.content
        val subtypeAttribution = storyElement?.subTypeMeta()?.attribution
        val quoteText = subtypeContent + "\n" + subtypeAttribution

        val styledResultText: SpannableString = SpannableString(quoteText);
        if (!TextUtils.isEmpty(subtypeAttribution)) {
            //format the quote text
            styledResultText.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), //align right
                    subtypeContent?.length as Int + 1, //to account for the new line after subtype content
                    subtypeContent.length + 1 + subtypeAttribution?.length as Int,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledResultText.setSpan(StyleSpan(Typeface.ITALIC), //make attribution italic
                    subtypeContent?.length as Int + 1,
                    subtypeContent.length + 1 + subtypeAttribution?.length as Int,
                    0);

            tvTextContent?.text = quoteText
        }
    }
}
