package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.story.Story

class StoryViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView) {
    fun bind(collectionItem: BulkTableModel, listner: View.OnClickListener) {
        super.bind(collectionItem.story as Story, collectionItem.mOuterCollectionAssociatedMetadata, listner)
//        var tvStoryTitle = itemView.findViewById<TextView>(R.id.story_row_tv_title)
        val ivStoryHeroImage = itemView.findViewById<SimpleDraweeView>(R.id.title_inside_image_header_row_iv_hero_icon)

//        tvStoryTitle?.text = collectionItem?.story?.headline

        val heroImageURL = cdnHostName + collectionItem.story?.heroImageS3Key
        ivStoryHeroImage.setImageURI(heroImageURL)

//        Glide.with(ivStoryHeroImage.context)
//                .load(heroImageURL)
//                .into(ivStoryHeroImage)
    }

    companion object {

        fun create(parent: ViewGroup): StoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.title_inside_image_header_row, parent, false)
            return StoryViewHolder(view)
        }
    }
}
