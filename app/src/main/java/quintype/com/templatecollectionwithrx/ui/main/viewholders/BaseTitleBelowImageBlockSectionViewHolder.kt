package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar

/**
 * Created TemplateCollectionWithRx by rakshith on 8/27/18.
 */

open class BaseTitleBelowImageBlockSectionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private var isShowAuthorName = true
    private var isShowTimeToPublish = false
    private var isShowSectionName = false

    open fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        var view = this.itemView.rootView

        var tvTitle = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)
        var tvSection = view?.findViewById<TextView>(R.id.section_block_title_author_row_tv_section_name)
        var clSectionBlockMainContainer = view?.findViewById<ConstraintLayout>(R.id.section_block_title_author_row_main_container)
        var clMainContainer = view?.findViewById<LinearLayout>(R.id.author_image_row_main_container)
        var ivAuthorIcon = view?.findViewById<CircleImageView>(R.id.author_image_row_iv_author_icon)
        var tvAuthorName = view?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        var tvPublishedDate = view?.findViewById<TextView>(R.id.author_image_row_tv_published_date)
        var rbCustomRatingBar = view?.findViewById<CustomRatingBar>(R.id.section_block_title_author_row_item_rating_bar)

        tvTitle?.text = collectionItem?.headline

        var reviewRatingValue: Float? = collectionItem?.metadata?.reviewRating?.metadataReviewRatingValue?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.GONE
        }

        if (collectionAssociatedMetadata != null) {
            isShowAuthorName = collectionAssociatedMetadata.associatedMetadataShowAuthorName
            isShowTimeToPublish = collectionAssociatedMetadata.associatedMetadataShowTimeToPublish
            isShowSectionName = collectionAssociatedMetadata.associatedMetadataShowSectionTag

            val assosiatedMetadataTheme = collectionAssociatedMetadata.associatedMetadataTheme

            if (isShowAuthorName) {
                val authorName = collectionItem.authorName
                if (authorName != null) {
                    tvAuthorName?.text = authorName
                    tvAuthorName?.visibility = View.VISIBLE
                    clMainContainer?.visibility = View.VISIBLE

                    val heroImageURL = collectionItem.authors?.first()?.avatarUrl
                    if (heroImageURL != null) {
                        ivAuthorIcon?.visibility = View.VISIBLE
                        Glide.with(ivAuthorIcon?.context as Context)
                                .load(heroImageURL)
                                .into(ivAuthorIcon)
                    }
                }
            }
            if (isShowTimeToPublish) {
                val publishedDate = collectionItem.publishedAt.toString()
                if (publishedDate != null) {
                    tvPublishedDate?.text = publishedDate

                    clMainContainer?.visibility = View.VISIBLE
                    tvPublishedDate?.visibility = View.VISIBLE
                }
            }
            if (isShowSectionName) {
                val sectionName = collectionItem.sections?.first()?.displayName
                if (sectionName != null) {
                    tvSection?.visibility = View.VISIBLE
                    tvSection?.text = sectionName
                } else
                    tvSection?.visibility = View.GONE
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