package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Image
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.utils.Constants

class PagerCarouselViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {
    var ivHeroImage: ImageView? = null
//    var tvTitle: TextView? = null
//
//    //can move this to custom class
//    private var isShowAuthorName = true
//    private var isShowTimeToPublish = false
//    private var isShowSectionName = false

//    init {
////        tvTitle = itemView?.findViewById<TextView>(R.id.pager_carousel_title_inside_image_row_tv_title)
//    }

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)

        ivHeroImage = itemView?.findViewById<ImageView>(R.id.pager_carousel_title_inside_image_row_iv_hero_icon)
        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)


        //can move this to custom class
//        var tvSection = itemView?.findViewById<TextView>(R.id.section_block_title_author_row_tv_section_name)
//
//        var clSectionBlockMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.section_block_title_author_row_main_container)
//        var clMainContainer = itemView?.findViewById<LinearLayout>(R.id.author_image_row_main_container)
//        var ivAuthorIcon = itemView?.findViewById<CircleImageView>(R.id.author_image_row_iv_author_icon)
//        var tvAuthorName = itemView?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
//        var tvPublishedDate = itemView?.findViewById<TextView>(R.id.author_image_row_tv_published_date)
//        tvTitle = itemView.findViewById(R.id.section_block_title_author_row_tv_title)

//        tvTitle?.text = collectionItem?.headline


//        tvTitle?.setTextColor(itemView.context.resources.getColor(R.color.white))
//        tvAuthorName?.setTextColor(itemView.context.resources.getColor(R.color.white))
//        tvPublishedDate?.setTextColor(itemView.context.resources.getColor(R.color.white))


//        if (collectionAssociatedMetadata != null) {
//            isShowAuthorName = collectionAssociatedMetadata.associatedMetadataShowAuthorName
//            isShowTimeToPublish = collectionAssociatedMetadata.associatedMetadataShowTimeToPublish
//            isShowSectionName = collectionAssociatedMetadata.associatedMetadataShowSectionTag
//
//            val assosiatedMetadataTheme = collectionAssociatedMetadata.associatedMetadataTheme
//
//            if (isShowAuthorName) {
//                val authorName = collectionItem.authorName
//                if (authorName != null) {
//                    tvAuthorName?.text = authorName
//                    tvAuthorName?.visibility = View.VISIBLE
//                    clMainContainer?.visibility = View.VISIBLE
//
//                    val heroImageURL = collectionItem.authors?.first()?.avatarUrl
//                    if (heroImageURL != null) {
//                        ivAuthorIcon?.visibility = View.VISIBLE
//                        Glide.with(ivAuthorIcon?.context as Context)
//                                .load(heroImageURL)
//                                .into(ivAuthorIcon)
//                    }
//                }
//            }
//            if (isShowTimeToPublish) {
//                val publishedDate = collectionItem.publishedAt.toString()
//                if (publishedDate != null) {
//                    tvPublishedDate?.text = publishedDate
//
//                    clMainContainer?.visibility = View.VISIBLE
//                    tvPublishedDate?.visibility = View.VISIBLE
//                }
//            }
//            if (isShowSectionName) {
//                val sectionName = collectionItem.sections?.first()?.displayName
//                if (sectionName != null) {
//                    tvSection?.visibility = View.VISIBLE
//                    tvSection?.text = sectionName
//                } else
//                    tvSection?.visibility = View.GONE
//            }
//            if (assosiatedMetadataTheme != null) {
//                var mContext = itemView.context
//                var themePrimaryColor = mContext.resources.getColor(R.color.theme_primary_color)
//                var themeSecondaryColor = mContext.resources.getColor(R.color.theme_secondary_color)
//
//                when (assosiatedMetadataTheme) {
//                    Constants.ASSOISATED_THEME_DARK -> {
//                        tvTitle?.setTextColor(themePrimaryColor)
//                        tvAuthorName?.setTextColor(themePrimaryColor)
//                        tvPublishedDate?.setTextColor(themePrimaryColor)
//                        clSectionBlockMainContainer?.setBackgroundColor(themeSecondaryColor)
//                    }
//                    Constants.ASSOISATED_THEME_LIGHT -> {
//                        tvTitle?.setTextColor(themeSecondaryColor)
//                        tvAuthorName?.setTextColor(themeSecondaryColor)
//                        tvPublishedDate?.setTextColor(themeSecondaryColor)
//                        clSectionBlockMainContainer?.setBackgroundColor(themePrimaryColor)
//                    }
//                }
//            }
////            else {
////                var themePrimaryColor = itemView.context.resources.getColor(R.color.theme_primary_color)
////                var themeSecondaryColor = itemView.context.resources.getColor(R.color.theme_secondary_color)
////                tvTitle?.setTextColor(themePrimaryColor)
////                tvAuthorName?.setTextColor(themePrimaryColor)
////                tvPublishedDate?.setTextColor(themePrimaryColor)
////                clSectionBlockMainContainer?.setBackgroundColor(themeSecondaryColor)
////            }
//        }
    }
}
