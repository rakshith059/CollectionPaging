package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar


open class BaseAuthorAndPublishedDateViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    private var isShowAuthorName = true
    private var isShowTimeToPublish = false

    val cdnHostName = Utilities.getSharedPreferences(itemView?.context as Context, Constants.SP_CDN_IMAGE_NAME)

    open fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        val view = this.itemView.rootView

        val clMainContainer = view?.findViewById<LinearLayout>(R.id.author_image_row_main_container)
        val ivAuthorIcon = view?.findViewById<CircleImageView>(R.id.author_image_row_iv_author_icon)
        val tvAuthorName = view?.findViewById<TextView>(R.id.author_image_row_tv_author_name)
        val tvPublishedDate = view?.findViewById<TextView>(R.id.author_image_row_tv_published_date)
        val rbCustomRatingBar = view?.findViewById<CustomRatingBar>(R.id.section_block_title_author_row_item_rating_bar)

        val reviewRatingValue: Float? = collectionItem.storyMetaData?.reviewRating?.value()?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.GONE
        }

        if (collectionAssociatedMetadata != null) {
            isShowAuthorName = collectionAssociatedMetadata.associatedMetadataShowAuthorName
            isShowTimeToPublish = collectionAssociatedMetadata.associatedMetadataShowTimeToPublish

            if (isShowAuthorName) {
                val authorName = collectionItem.authors?.first()?.name
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
                tvPublishedDate?.text = Utilities.formatDate(publishedDate)

                clMainContainer?.visibility = View.VISIBLE
                tvPublishedDate?.visibility = View.VISIBLE
            }
        }
    }
}