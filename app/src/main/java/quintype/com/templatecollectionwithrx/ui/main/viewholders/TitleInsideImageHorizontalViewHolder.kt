package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.WebActivity
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.widgets.CustomRatingBar

class TitleInsideImageHorizontalViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.title_below_image_block_section_horizontal_scroll_row_iv_hero_icon)
        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.title_below_image_block_section_horizontal_scroll_row_cl_main_container)
        var cbRatingBar = itemView?.findViewById<CustomRatingBar>(R.id.section_block_title_author_row_item_rating_bar)
        var tvStoryTitle = itemView?.findViewById<TextView>(R.id.section_block_title_author_row_tv_title)

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

        cbRatingBar?.visibility = View.GONE
        tvStoryTitle?.maxLines = 2
        var screenWidth = Constants.getScreenWidth(itemView.context)
        var viewWidth = screenWidth - 100

        clMainContainer?.getLayoutParams()?.width = viewWidth
        clMainContainer?.requestLayout()
    }

    companion object {
        fun create(parent: ViewGroup): TitleInsideImageHorizontalViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_below_image_block_section_horizontal_scroll_row, parent, false)
            return TitleInsideImageHorizontalViewHolder(view)
        }
    }
}
