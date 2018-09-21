package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants

class StoryViewHolder(itemView: View?) : BaseTitleBelowImageBlockSectionViewHolder(itemView), View.OnClickListener {
    fun bind(collectionItem: BulkTableModel) {
        super.bind(collectionItem.story as Story, collectionItem.mOuterCollectionAssociatedMetadata, this)
//        var tvStoryTitle = itemView.findViewById<TextView>(R.id.story_row_tv_title)
        var ivStoryHeroImage = itemView.findViewById<ImageView>(R.id.title_inside_image_header_row_iv_hero_icon)

//        tvStoryTitle?.text = collectionItem?.story?.headline

        val heroImageURL = cdnHostName + collectionItem.story?.heroImageS3Key

        Glide.with(ivStoryHeroImage.context)
                .load(heroImageURL)
                .into(ivStoryHeroImage)
    }

    companion object {

        fun create(parent: ViewGroup): StoryViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_inside_image_header_row, parent, false)
            return StoryViewHolder(view)
        }
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
