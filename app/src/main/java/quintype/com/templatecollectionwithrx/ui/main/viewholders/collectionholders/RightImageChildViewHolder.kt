package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

class RightImageChildViewHolder(itemView: View?) : BaseTitleBelowImageUnderlineSectionViewHolder(itemView) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)
//        var tvStoryTitle = itemView.findViewById<TextView>(R.id.right_image_child_row_tv_title)
        var ivStoryHeroImage = itemView.findViewById<ImageView>(R.id.right_image_child_row_iv_hero_icon)

//        tvStoryTitle?.text = collectionItem?.headline

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

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
