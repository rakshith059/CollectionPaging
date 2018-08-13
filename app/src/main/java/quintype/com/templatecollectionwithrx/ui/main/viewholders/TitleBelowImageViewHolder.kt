package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.utils.Constants


class TitleBelowImageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private var isShowAuthorName = true
    private var isShowTimeToPublish = false
    private var isShowSectionName = false

    fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.title_below_image_header_row_iv_hero_icon)
        var tvTitle = itemView?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)

        var tvSection = itemView?.findViewById<TextView>(R.id.section_block_title_author_row_tv_section_name)

        var clSectionBlockMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.section_block_title_author_row_main_container)
        var clMainContainer = itemView?.findViewById<LinearLayout>(R.id.author_image_row_main_container)
        var ivAuthorIcon = itemView?.findViewById<CircleImageView>(R.id.author_image_row_iv_author_icon)
        var tvAuthorName = itemView?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        var tvPublishedDate = itemView?.findViewById<TextView>(R.id.author_image_row_tv_published_date)

        tvTitle?.text = collectionItem.headline

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(ivHeroImage?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage)

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
                var mWhiteColor = mContext.resources.getColor(R.color.white)
                var mBlackColor = mContext.resources.getColor(R.color.black)

                when (assosiatedMetadataTheme) {
                    Constants.ASSOISATED_THEME_DARK -> {
                        tvTitle?.setTextColor(mWhiteColor)
                        tvAuthorName?.setTextColor(mWhiteColor)
                        tvPublishedDate?.setTextColor(mWhiteColor)
                        clSectionBlockMainContainer?.setBackgroundColor(mBlackColor)
                    }
                    Constants.ASSOISATED_THEME_LIGHT -> {
                        tvTitle?.setTextColor(mBlackColor)
                        tvAuthorName?.setTextColor(mBlackColor)
                        tvPublishedDate?.setTextColor(mBlackColor)
                        clSectionBlockMainContainer?.setBackgroundColor(mWhiteColor)
                    }
                }
            } else {
                tvTitle?.setTextColor(itemView.context.resources.getColor(R.color.white))
                tvAuthorName?.setTextColor(itemView.context.resources.getColor(R.color.white))
                tvPublishedDate?.setTextColor(itemView.context.resources.getColor(R.color.white))
                clSectionBlockMainContainer?.setBackgroundColor(itemView.context.resources.getColor(R.color.black))
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): TitleBelowImageViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_below_image_header_row, parent, false)
            return TitleBelowImageViewHolder(view)
        }
    }

}
