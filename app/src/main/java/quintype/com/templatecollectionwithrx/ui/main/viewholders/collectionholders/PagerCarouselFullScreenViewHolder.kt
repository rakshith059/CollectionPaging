package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

class PagerCarouselFullScreenViewHolder(itemView: View?) : BaseTitleInsideImageBlockSectionViewHolder(itemView) {
    var ivHeroImage: ImageView? = null

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)

        ivHeroImage = itemView?.findViewById<ImageView>(R.id.pager_carousel_title_inside_image_row_iv_hero_icon)
        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)
    }
}
