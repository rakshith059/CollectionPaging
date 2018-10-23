package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import android.widget.TextView
import com.example.androidcore.models.story.StoryElement
import quintype.com.templatecollectionwithrx.utils.Utilities


class ElementStoryQAHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var tvQuestion: TextView? = null
    var tvAnswer: TextView? = null

    companion object {
        fun create(parent: ViewGroup): ElementStoryQAHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_question_answer_element_holder, parent, false)
            val holder = ElementStoryQAHolder(view)
            holder.tvQuestion = view.findViewById(R.id.story_question_answer_element_holder_tv_question)
            holder.tvAnswer = view.findViewById(R.id.story_question_answer_element_holder_tv_answer)
            return holder
        }
    }

    fun bind(element: StoryElement) {
        var mQuestion = element.subTypeMeta().question
        var mAnswer = element.subTypeMeta().answer
        if (mQuestion != null)
            tvQuestion?.text = Utilities.parseHtml(mQuestion)
        if (mAnswer != null)
            tvAnswer?.text = Utilities.parseHtml(mAnswer)
    }
}
