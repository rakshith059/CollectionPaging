package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.content.Intent
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
import quintype.com.templatecollectionwithrx.WebActivity
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.utils.Constants


class TitleBelowImageBlockSectionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private var isShowAuthorName = true
    private var isShowTimeToPublish = false
    private var isShowSectionName = false

    fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.title_below_image_block_section_header_row_iv_hero_icon)
        var tvTitle = itemView?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)

        var tvSection = itemView?.findViewById<TextView>(R.id.section_block_title_author_row_tv_section_name)

        var clSectionBlockMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.section_block_title_author_row_main_container)
        var clMainContainer = itemView?.findViewById<LinearLayout>(R.id.author_image_row_main_container)
        var ivAuthorIcon = itemView?.findViewById<CircleImageView>(R.id.author_image_row_iv_author_icon)
        var tvAuthorName = itemView?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        var tvPublishedDate = itemView?.findViewById<TextView>(R.id.author_image_row_tv_published_date)

//        var mSectionBlockTitleAuthorFragment = itemView?.findViewById<SectionBlockTitleAuthorFragment>(R.id.section_block_title_author_row_fragment)

        tvTitle?.text = collectionItem.headline

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(ivHeroImage?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage)

        ivHeroImage.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(ivHeroImage.context, WebActivity::class.java)
                ivHeroImage.context.startActivity(intent)
            }
        })
//        mSectionBlockTitleAuthorFragment?.setAssosiatedMetaData(ivHeroImage?.context as Context, collectionAssociatedMetadata, collectionItem)

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

    companion object {
        //        var mSectionBlockTitleAuthorFragment: SectionBlockTitleAuthorFragment? = null
        fun create(parent: ViewGroup): TitleBelowImageBlockSectionViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_below_image_block_section_header_row, parent, false)

//            mSectionBlockTitleAuthorFragment = SectionBlockTitleAuthorFragment(view?.context as Context)
            return TitleBelowImageBlockSectionViewHolder(view)
        }
    }

}
