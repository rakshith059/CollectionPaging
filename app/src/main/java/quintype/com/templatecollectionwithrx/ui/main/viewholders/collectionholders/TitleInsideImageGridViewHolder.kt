package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

open class TitleInsideImageGridViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    open fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.title_inside_image_grid_row_iv_hero_icon)
        var tvStoryTitle = itemView?.findViewById<TextView>(R.id.title_inside_image_grid_row_tv_title)

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)

        tvStoryTitle?.text = collectionItem?.headline
    }

    companion object {
        fun create(parent: ViewGroup): TitleInsideImageGridViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_inside_image_grid_row, parent, false)
            return TitleInsideImageGridViewHolder(view)
        }
    }
}

