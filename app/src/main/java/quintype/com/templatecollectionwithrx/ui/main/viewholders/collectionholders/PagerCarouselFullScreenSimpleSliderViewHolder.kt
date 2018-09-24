package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.content.Context
import android.provider.SyncStateContract
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants

class PagerCarouselFullScreenSimpleSliderViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, collectionName: String?, listner: View.OnClickListener) {
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.pager_carousel_full_screen_simple_slider_row_iv_hero_icon)
        var tvCollectionName = itemView?.findViewById<TextView>(R.id.collection_item_read_story_row_tv_collection_name)
        var tvStoryHeadline = itemView?.findViewById<TextView>(R.id.collection_item_read_story_row_tv_story_headline)
        var tvReadStory = itemView?.findViewById<TextView>(R.id.collection_item_read_story_row_tv_read_story_text)

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.pager_carousel_full_screen_simple_slider_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val cdnHostName = Constants.getSharedPreferences(itemView.context, Constants.SP_CDN_IMAGE_NAME)
        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)

        tvCollectionName?.text = collectionName
        tvStoryHeadline?.text = collectionItem?.headline
    }
}