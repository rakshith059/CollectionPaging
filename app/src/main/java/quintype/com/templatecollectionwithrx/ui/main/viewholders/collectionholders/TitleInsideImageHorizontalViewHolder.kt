package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Utilities
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar

class TitleInsideImageHorizontalViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
        var ivHeroImage = itemView?.findViewById<SimpleDraweeView>(R.id.title_below_image_block_section_horizontal_scroll_row_iv_hero_icon)
        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.title_below_image_block_section_horizontal_scroll_row_cl_main_container)
        var rbCustomRatingBar = itemView?.findViewById<CustomRatingBar>(R.id.section_block_title_author_row_item_rating_bar)
        var tvStoryTitle = itemView?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        ivHeroImage?.setImageURI(heroImageURL)

//        Glide.with(ivHeroImage?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage)

//        ivHeroImage.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                var intent = Intent(ivHeroImage.context, WebActivity::class.java)
//                ivHeroImage.context.startActivity(intent)
//            }
//        })

        var reviewRatingValue: Float? = collectionItem?.storyMetaData?.reviewRating?.value()?.toFloat()

        if (reviewRatingValue != null && reviewRatingValue > 0f) {
            rbCustomRatingBar?.visibility = View.VISIBLE
            rbCustomRatingBar?.score = reviewRatingValue
        } else {
            rbCustomRatingBar?.visibility = View.INVISIBLE
        }

        tvStoryTitle?.maxLines = 2
        tvStoryTitle?.minLines = 2
        tvStoryTitle?.ellipsize = TextUtils.TruncateAt.END
        var screenWidth = Utilities.getScreenWidth(itemView.context)
        var viewWidth = screenWidth - 100
        var viewHeight = 700

        clMainContainer?.getLayoutParams()?.width = viewWidth
        clMainContainer?.getLayoutParams()?.height = viewHeight
        clMainContainer?.requestLayout()
    }

    companion object {
        fun create(parent: ViewGroup): TitleInsideImageHorizontalViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_below_image_block_section_horizontal_scroll_row, parent, false)
            return TitleInsideImageHorizontalViewHolder(view)
        }
    }
}

