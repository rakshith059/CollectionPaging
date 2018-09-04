package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.view.View
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

open class BaseTitleInsideImageBlockSectionViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)

        var view = this.itemView.rootView

        var tvTitle = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)
        var tvAuthorName = view?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        var tvPublishedDate = view?.findViewById<TextView>(R.id.author_image_row_tv_published_date)

        if (collectionAssociatedMetadata != null) {
            var mContext = itemView.context
            var themePrimaryColor = mContext.resources.getColor(R.color.theme_primary_color)

            tvTitle?.setTextColor(themePrimaryColor)
            tvAuthorName?.setTextColor(themePrimaryColor)
            tvPublishedDate?.setTextColor(themePrimaryColor)
        }
    }

}
