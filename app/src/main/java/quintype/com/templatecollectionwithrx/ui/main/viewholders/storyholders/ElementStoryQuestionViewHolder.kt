package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.story.StoryElement
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
