package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities

class ElementStoryQuestionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var tvQuestion: TextView? = null

    companion object {
        fun create(parent: ViewGroup): ElementStoryQuestionViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_question_view_holder, parent, false)
            val elementStoryQuestionViewHolder = ElementStoryQuestionViewHolder(view)
            elementStoryQuestionViewHolder.tvQuestion = view?.findViewById(R.id.story_question_element_holder_tv_question)
            return elementStoryQuestionViewHolder
        }
    }

    fun bind(element: StoryElement) {
        var mQuestion = element.text()
        if (mQuestion != null) {
            tvQuestion?.visibility = View.VISIBLE
            tvQuestion?.text = Utilities.parseHtml(mQuestion)
        } else
            tvQuestion?.visibility = View.GONE
    }
}
