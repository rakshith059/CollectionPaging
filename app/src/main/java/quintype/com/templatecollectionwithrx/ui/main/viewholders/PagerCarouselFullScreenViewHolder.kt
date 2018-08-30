package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Image
import quintype.com.templatecollectionwithrx.models.Story

class PagerCarouselFullScreenViewHolder(itemView: View?) : BaseTitleInsideImageBlockSectionViewHolder(itemView) {
    var ivHeroImage: ImageView? = null

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)

        ivHeroImage = itemView?.findViewById<ImageView>(R.id.pager_carousel_title_inside_image_row_iv_hero_icon)
        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)
    }
}
