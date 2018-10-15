package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement

class ElementQuoteViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var tvTextContent: TextView? = null
    var tvTextAttribution: TextView? = null

    companion object {

        fun create(parent: ViewGroup): ElementQuoteViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_quote_element_holder, parent, false)
            val elementQuoteHolder = ElementQuoteViewHolder(view)
            elementQuoteHolder.tvTextContent = view?.findViewById(R.id.quote_holder_text_content)
            elementQuoteHolder.tvTextAttribution = view?.findViewById(R.id.quote_holder_attribute_text)
            return ElementQuoteViewHolder(view)
        }
    }

    fun bind(storyElement: StoryElement) {

        val subtypeContent = storyElement.subTypeMeta()?.content
        val subtypeAttribution = storyElement.subTypeMeta()?.attribution
//        val quoteText = subtypeContent + "\n" + subtypeAttribution

//        val styledResultText: SpannableString = SpannableString(quoteText);
//        if (!TextUtils.isEmpty(subtypeAttribution)) {
//            //format the quote text
//            styledResultText.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), //align right
//                    subtypeContent?.length as Int + 1, //to account for the new line after subtype content
//                    subtypeContent.length + 1 + subtypeAttribution?.length as Int,
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            styledResultText.setSpan(StyleSpan(Typeface.ITALIC), //make attribution italic
//                    subtypeContent?.length as Int + 1,
//                    subtypeContent.length + 1 + subtypeAttribution?.length as Int,
//                    0);

        if (subtypeContent != null)
            tvTextContent?.text = subtypeContent
        if (subtypeAttribution != null)
            tvTextAttribution?.text = subtypeAttribution
    }
}
