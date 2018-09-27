package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Utilities

class ElementStoryBigFactViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var tvBigFactContent: TextView? = null
    var tvBigFactAttribution: TextView? = null

    companion object {
        fun create(parent: ViewGroup): ElementStoryBigFactViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_big_fact_view_holder, parent, false)

            val elementStoryBigFactViewHolder = ElementStoryBigFactViewHolder(view)
            elementStoryBigFactViewHolder.tvBigFactContent = view.findViewById<TextView>(R.id.story_big_fact_view_holder_tv_content)
            elementStoryBigFactViewHolder.tvBigFactAttribution = view.findViewById<TextView>(R.id.story_big_fact_view_holder_tv_attribution)
            return elementStoryBigFactViewHolder
        }
    }

    fun bind(element: StoryElement) {
        val mBigFactContent = element.subTypeMeta()?.content
        val mBigFactAttribute = element.subTypeMeta()?.attribution
        if (!TextUtils.isEmpty(mBigFactContent)) {
            tvBigFactContent?.text = Utilities.parseHtml(mBigFactContent as String)
            tvBigFactContent?.visibility = View.VISIBLE
        } else
            tvBigFactContent?.visibility = View.GONE

        if (!TextUtils.isEmpty(mBigFactAttribute)) {
            tvBigFactAttribution?.text = Utilities.parseHtml(mBigFactAttribute as String)
            tvBigFactAttribution?.visibility = View.VISIBLE
        } else
            tvBigFactAttribution?.visibility = View.GONE
    }
}
