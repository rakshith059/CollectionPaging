package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.utils.widgets.ExpandableLayoutCallBackInterface
import quintype.com.templatecollectionwithrx.models.story.StoryElement
import android.graphics.drawable.Drawable
import android.text.Html
import quintype.com.templatecollectionwithrx.utils.widgets.ExpandableLayout
import android.widget.TextView


class ElementStorySummaryHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var snapshotHeaderText: TextView? = null
    var snapshotContentText: TextView? = null
    var snapshotExpandableLayout: ExpandableLayout? = null
    var plusImage: Drawable? = null
    var minusImage: Drawable? = null

    companion object {
        fun create(parent: ViewGroup): ElementStorySummaryHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_summary_element_holder, parent, false)

            val holder: ElementStorySummaryHolder = ElementStorySummaryHolder(view);
            holder.snapshotContentText = view.findViewById(R.id.snapshot_content_text);
            holder.snapshotHeaderText = view.findViewById(R.id.snapshot_header_text);
            holder.snapshotExpandableLayout = view.findViewById(R.id.sm_story_snapshot_layout_expandable);
            holder.plusImage = parent.getContext().getResources().getDrawable(R.drawable.ic_plus);
            holder.minusImage = parent.getContext().getResources().getDrawable(R.drawable.ic_minus);

            return holder
        }
    }

    fun bind(element: StoryElement) {
        var summaryContent = element.text()
        if (summaryContent != null)
            snapshotContentText?.text = Html.fromHtml(summaryContent)
        snapshotExpandableLayout?.show(object : ExpandableLayoutCallBackInterface {
            override fun isLayoutOpen(isOpen: Boolean) {
                if (isOpen) {
                    snapshotHeaderText?.setCompoundDrawablesWithIntrinsicBounds(null, null, minusImage, null)
                } else {
                    snapshotHeaderText?.setCompoundDrawablesWithIntrinsicBounds(null, null, plusImage, null)
                }
            }
        })
    }
}
