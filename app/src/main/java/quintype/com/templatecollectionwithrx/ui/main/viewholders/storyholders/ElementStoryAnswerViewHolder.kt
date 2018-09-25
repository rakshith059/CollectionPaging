package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Utilities

class ElementStoryAnswerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var tvAnswer: TextView? = null

    companion object {
        fun create(parent: ViewGroup): ElementStoryAnswerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_answer_view_holder, parent, false)
            val elementStoryAnswerViewHolder = ElementStoryAnswerViewHolder(view)
            elementStoryAnswerViewHolder.tvAnswer = view?.findViewById(R.id.story_answer_element_holder_tv_question)
            return elementStoryAnswerViewHolder
        }
    }

    fun bind(element: StoryElement) {
        var mAnswer = element.text()
        if (mAnswer != null) {
            tvAnswer?.visibility = View.VISIBLE
            tvAnswer?.text = Utilities.parseHtml(mAnswer)
        } else
            tvAnswer?.visibility = View.GONE
    }
}
