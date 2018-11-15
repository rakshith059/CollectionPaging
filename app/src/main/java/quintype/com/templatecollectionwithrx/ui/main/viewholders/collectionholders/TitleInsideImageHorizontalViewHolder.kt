package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Utilities
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar

class TitleInsideImageHorizontalViewHolder(val view: View) : BaseTitleBelowImageBlockSectionViewHolder(view) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
        val ivHeroImage = view.findViewById<SimpleDraweeView>(R.id.title_below_image_block_section_horizontal_scroll_row_iv_hero_icon)
        val clMainContainer = view.findViewById<ConstraintLayout>(R.id.title_below_image_block_section_horizontal_scroll_row_cl_main_container)
        val rbCustomRatingBar = view.findViewById<CustomRatingBar>(R.id.section_block_title_author_row_item_rating_bar)
        val tvStoryTitle = view.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        ivHeroImage?.setImageURI(heroImageURL)

        val reviewRatingValue: Float? = collectionItem?.storyMetaData?.reviewRating?.value()?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.INVISIBLE
        }

        tvStoryTitle?.maxLines = 2
        tvStoryTitle?.minLines = 2
        tvStoryTitle?.ellipsize = TextUtils.TruncateAt.END
        val screenWidth = Utilities.getScreenWidth(itemView.context)
        val viewWidth = screenWidth - 100
        val viewHeight = 700

        clMainContainer?.getLayoutParams()?.width = viewWidth
        clMainContainer?.getLayoutParams()?.height = viewHeight
        clMainContainer?.requestLayout()
    }
}