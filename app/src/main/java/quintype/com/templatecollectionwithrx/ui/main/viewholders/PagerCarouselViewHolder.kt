package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Image
import quintype.com.templatecollectionwithrx.models.Story

class PagerCarouselViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var ivHeroImage: ImageView? = null
    var tvTitle: TextView? = null

    init {
        ivHeroImage = itemView?.findViewById<ImageView>(R.id.pager_carousel_title_inside_image_row_iv_hero_icon)
        tvTitle = itemView?.findViewById<TextView>(R.id.pager_carousel_title_inside_image_row_tv_title)
    }

    fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        tvTitle?.text = collectionItem?.headline

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)
    }

}
