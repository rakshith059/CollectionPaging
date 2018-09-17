package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants

open class BaseTitleBelowImageUnderlineSectionViewHolder(itemView: View?) : BaseAuthorAndPublishedDateViewHolder(itemView) {
    private var isShowSectionName = false

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)
        var view = this.itemView.rootView

        var tvTitle = view?.findViewById<TextView>(R.id.section_underline_title_author_row_tv_title)

        var llUnderlineSection = view?.findViewById<LinearLayout>(R.id.section_underline_title_author_row_ll_section)
        var tvSection = view?.findViewById<TextView>(R.id.section_underline_title_author_row_tv_section_name)

        var clSectionBlockMainContainer = view?.findViewById<ConstraintLayout>(R.id.section_underline_title_author_row_main_container)
        var tvAuthorName = view?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        var tvPublishedDate = view?.findViewById<TextView>(R.id.author_image_row_tv_published_date)

        tvTitle?.text = collectionItem?.headline

        if (collectionAssociatedMetadata != null) {
            isShowSectionName = collectionAssociatedMetadata.associatedMetadataShowSectionTag
            val assosiatedMetadataTheme = collectionAssociatedMetadata.associatedMetadataTheme

            if (isShowSectionName) {
                var sectionName: String? = null
                var storySection = collectionItem.sections?.first()
                if (storySection?.displayName != null)
                    sectionName = storySection?.displayName
                else if (storySection?.name != null)
                    sectionName = storySection?.name

                if (sectionName != null) {
                    llUnderlineSection?.visibility = View.VISIBLE
                    tvSection?.text = sectionName
                } else
                    llUnderlineSection?.visibility = View.GONE
            }
            if (assosiatedMetadataTheme != null) {
                var mContext = itemView.context
                var themePrimaryColor = mContext.resources.getColor(R.color.theme_primary_color)
                var themeSecondaryColor = mContext.resources.getColor(R.color.theme_secondary_color)

                when (assosiatedMetadataTheme) {
                    Constants.ASSOISATED_THEME_DARK -> {
                        tvTitle?.setTextColor(themePrimaryColor)
                        tvAuthorName?.setTextColor(themePrimaryColor)
                        tvPublishedDate?.setTextColor(themePrimaryColor)
                        clSectionBlockMainContainer?.setBackgroundColor(themeSecondaryColor)
                    }
                    Constants.ASSOISATED_THEME_LIGHT -> {
                        tvTitle?.setTextColor(themeSecondaryColor)
                        tvAuthorName?.setTextColor(themeSecondaryColor)
                        tvPublishedDate?.setTextColor(themeSecondaryColor)
                        clSectionBlockMainContainer?.setBackgroundColor(themePrimaryColor)
                    }
                }
            }
//            else {
//                var themePrimaryColor = itemView.context.resources.getColor(R.color.theme_primary_color)
//                var themeSecondaryColor = itemView.context.resources.getColor(R.color.theme_secondary_color)
//                tvTitle?.setTextColor(themePrimaryColor)
//                tvAuthorName?.setTextColor(themePrimaryColor)
//                tvPublishedDate?.setTextColor(themePrimaryColor)
//                clSectionBlockMainContainer?.setBackgroundColor(themeSecondaryColor)
//            }
        }
    }
}
