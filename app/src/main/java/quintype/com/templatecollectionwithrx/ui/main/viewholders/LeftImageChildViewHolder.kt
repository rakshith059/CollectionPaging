package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.Story

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */

class LeftImageChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(collectionItem: Story) {
        var tvStoryTitle = itemView.findViewById<TextView>(R.id.left_image_child_row_tv_title)
        var ivStoryHeroImage = itemView.findViewById<ImageView>(R.id.left_image_child_row_iv_hero_icon)

        tvStoryTitle?.text = collectionItem?.headline

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(ivStoryHeroImage.context)
                .load(heroImageURL)
                .into(ivStoryHeroImage)
    }
//    fun bind(collectionItem: BulkTableModel) {
//        var tvStoryTitle = itemView.findViewById<TextView>(R.id.default_story_row_tv_title)
//        var ivStoryHeroImage = itemView.findViewById<ImageView>(R.id.default_story_row_iv_hero_icon)
//
//        tvStoryTitle?.text = collectionItem.story?.headline
//
//        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.story?.heroImageS3Key
//
//        Glide.with(ivStoryHeroImage.context)
//                .load(heroImageURL)
//                .into(ivStoryHeroImage)
//    }

    companion object {
        fun create(parent: ViewGroup): LeftImageChildViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.left_image_child_row, parent, false)
            return LeftImageChildViewHolder(view)
        }
    }
}