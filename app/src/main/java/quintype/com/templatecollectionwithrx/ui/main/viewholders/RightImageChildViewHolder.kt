package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.Story

class RightImageChildViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        var tvStoryTitle = itemView.findViewById<TextView>(R.id.right_image_child_row_tv_title)
        var ivStoryHeroImage = itemView.findViewById<ImageView>(R.id.right_image_child_row_iv_hero_icon)

        tvStoryTitle?.text = collectionItem?.headline

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(ivStoryHeroImage.context)
                .load(heroImageURL)
                .into(ivStoryHeroImage)
    }

    companion object {
        fun create(parent: ViewGroup): RightImageChildViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.right_image_child_row, parent, false)
            return RightImageChildViewHolder(view)
        }
    }
}
