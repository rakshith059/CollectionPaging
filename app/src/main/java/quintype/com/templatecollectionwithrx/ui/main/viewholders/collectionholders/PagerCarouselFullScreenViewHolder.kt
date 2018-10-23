package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.view.View
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

class PagerCarouselFullScreenViewHolder(itemView: View?) : BaseTitleInsideImageBlockSectionViewHolder(itemView) {
    var ivHeroImage: SimpleDraweeView? = null

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)

        ivHeroImage = itemView?.findViewById(R.id.pager_carousel_title_inside_image_row_iv_hero_icon)

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.pager_carousel_title_inside_image_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        ivHeroImage?.setImageURI(heroImageURL)

//        Glide.with(itemView?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage as ImageView)
    }
}
