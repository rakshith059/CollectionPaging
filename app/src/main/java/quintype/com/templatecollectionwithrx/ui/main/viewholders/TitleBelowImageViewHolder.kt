package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Story

class TitleBelowImageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var isShowAuthorName = true
    var isShowCollectionName = true
    var isShowTimeToPublish = true

    fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.title_below_image_header_row_iv_hero_icon)
        var tvTitle = itemView?.findViewById<TextView>(R.id.title_below_image_header_row_tv_title)

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.author_image_row_main_container)
        var ivAuthorIcon = itemView?.findViewById<ImageView>(R.id.author_image_row_iv_author_icon)
        var tvAuthorName = itemView?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        var tvPublishedDate = itemView?.findViewById<TextView>(R.id.author_image_row_tv_published_date)

        tvTitle?.text = collectionItem?.headline

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(ivHeroImage?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage)

        if (collectionAssociatedMetadata != null) {
            isShowAuthorName = collectionAssociatedMetadata.associatedMetadataShowAuthorName
            isShowCollectionName = collectionAssociatedMetadata.associatedMetadataShowCollectionName
            isShowTimeToPublish = collectionAssociatedMetadata.associatedMetadataShowTimeToPublish

            if (isShowAuthorName) {
                val authorName = collectionItem.authorName
                if (authorName != null) {
                    tvAuthorName?.text = authorName

                    ivAuthorIcon?.visibility = View.VISIBLE
                    tvAuthorName?.visibility = View.VISIBLE
                    clMainContainer?.visibility = View.VISIBLE

                    val heroImageURL = collectionItem.authors?.get(0)?.avatarUrl
                    Glide.with(ivAuthorIcon?.context as Context)
                            .load(heroImageURL)
                            .into(ivAuthorIcon)
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
        }
    }

    companion object {
        fun create(parent: ViewGroup): TitleBelowImageViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_below_image_header_row, parent, false)
            return TitleBelowImageViewHolder(view)
        }
    }

}
