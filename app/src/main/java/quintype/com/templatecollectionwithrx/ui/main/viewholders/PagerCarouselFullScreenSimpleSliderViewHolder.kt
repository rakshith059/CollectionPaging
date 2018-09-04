package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

class PagerCarouselFullScreenSimpleSliderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, collectionName: String?) {
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.pager_carousel_full_screen_simple_slider_row_iv_hero_icon)
        var tvCollectionName = itemView?.findViewById<TextView>(R.id.collection_item_read_story_row_tv_collection_name)
        var tvStoryHeadline = itemView?.findViewById<TextView>(R.id.collection_item_read_story_row_tv_story_headline)
        var tvReadStory = itemView?.findViewById<TextView>(R.id.collection_item_read_story_row_tv_read_story_text)

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)

        tvCollectionName?.text = collectionName
        tvStoryHeadline?.text = collectionItem?.headline
    }
}