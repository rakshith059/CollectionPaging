package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar

class PagerCarouselHalfScreenViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)

        var ivHeroImage = itemView?.findViewById<SimpleDraweeView>(R.id.pager_carousel_half_slider_row_iv_hero_icon)
        var tvTitle = itemView?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)
        tvTitle?.maxLines = 2
        tvTitle?.minLines = 2
        tvTitle?.ellipsize = TextUtils.TruncateAt.END

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.pager_carousel_half_slider_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        ivHeroImage?.setImageURI(heroImageURL)

//        Glide.with(itemView?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage as ImageView)

        var rbCustomRatingBar = itemView?.findViewById<CustomRatingBar>(R.id.section_block_title_author_row_item_rating_bar)

        var reviewRatingValue: Float? = collectionItem?.storyMetaData?.reviewRating?.value()?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.INVISIBLE
        }
    }
}