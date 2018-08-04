package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.Story

class StoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun bind(collectionItem: BulkTableModel) {
        var tvStoryTitle = itemView.findViewById<TextView>(R.id.story_row_tv_title)
        var ivStoryHeroImage = itemView.findViewById<ImageView>(R.id.story_row_iv_hero_icon)

        tvStoryTitle?.text = collectionItem?.story?.headline

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.story?.heroImageS3Key

        Glide.with(ivStoryHeroImage.context)
                .load(heroImageURL)
                .into(ivStoryHeroImage)
    }

    companion object {
        fun create(parent: ViewGroup): StoryViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.story_row, parent, false)
            return StoryViewHolder(view)
        }
    }
}
