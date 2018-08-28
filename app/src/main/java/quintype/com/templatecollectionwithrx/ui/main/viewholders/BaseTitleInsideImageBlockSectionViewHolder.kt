package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Story

open class BaseTitleInsideImageBlockSectionViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)

        var view = this.itemView.rootView

        var tvTitle = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)
        var tvSection = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_section_name)
        var clSectionBlockMainContainer = view?.findViewById<ConstraintLayout>(R.id.section_block_title_author_row_main_container)
        var tvAuthorName = view?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        var tvPublishedDate = view?.findViewById<TextView>(R.id.author_image_row_tv_published_date)

        if (collectionAssociatedMetadata != null) {
            val assosiatedMetadataTheme = collectionAssociatedMetadata.associatedMetadataTheme

            var mContext = itemView.context
            var themePrimaryColor = mContext.resources.getColor(R.color.theme_primary_color)
            var themeSecondaryColor = mContext.resources.getColor(R.color.theme_secondary_color)

            tvTitle?.setTextColor(themePrimaryColor)
            tvAuthorName?.setTextColor(themePrimaryColor)
            tvPublishedDate?.setTextColor(themePrimaryColor)
        }
    }

}
