package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import com.example.androidcore.models.collection.AssociatedMetadata
import com.example.androidcore.models.story.Story

class TitleInsideImageViewHolder(itemView: View?) : BaseTitleInsideImageBlockSectionViewHolder(itemView) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
        var ivHeroImage = itemView?.findViewById<SimpleDraweeView>(R.id.title_inside_image_header_row_iv_hero_icon)

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.title_inside_image_header_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key
        ivHeroImage?.setImageURI(heroImageURL)

//        Glide.with(itemView?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage as ImageView)
    }

    companion object {
        fun create(parent: ViewGroup): TitleInsideImageViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_inside_image_header_row, parent, false)
            return TitleInsideImageViewHolder(view)
        }
    }
}
