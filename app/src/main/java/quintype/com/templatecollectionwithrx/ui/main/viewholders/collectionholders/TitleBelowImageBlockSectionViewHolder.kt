package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.constraint.ConstraintLayout
import android.view.View
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks


class TitleBelowImageBlockSectionViewHolder(var view: View, val mFragmentCallbacks: FragmentCallbacks?) : BaseTitleBelowImageBlockSectionViewHolder(view) {

    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
        var ivHeroImage = view.findViewById<SimpleDraweeView>(R.id.title_below_image_block_section_header_row_iv_hero_icon)

        var clMainContainer = view.findViewById<ConstraintLayout>(R.id.title_below_image_block_section_header_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        ivHeroImage?.setImageURI(heroImageURL)
    }
}