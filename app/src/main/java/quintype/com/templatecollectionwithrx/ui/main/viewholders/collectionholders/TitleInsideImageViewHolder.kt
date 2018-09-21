package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.Constants

class TitleInsideImageViewHolder(itemView: View?) : BaseTitleInsideImageBlockSectionViewHolder(itemView) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.title_inside_image_header_row_iv_hero_icon)

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.title_inside_image_header_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        Glide.with(itemView?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)
    }

    companion object {
        fun create(parent: ViewGroup): TitleInsideImageViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_inside_image_header_row, parent, false)
            return TitleInsideImageViewHolder(view)
        }
    }
}
