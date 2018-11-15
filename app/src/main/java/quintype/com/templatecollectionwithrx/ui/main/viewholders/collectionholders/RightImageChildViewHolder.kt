package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.v7.widget.CardView
import android.view.View
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

class RightImageChildViewHolder(val view: View) : BaseTitleBelowImageUnderlineSectionViewHolder(view) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
//        var tvStoryTitle = itemView.findViewById<TextView>(R.id.right_image_child_row_tv_title)
        var ivStoryHeroImage = view.findViewById<SimpleDraweeView>(R.id.right_image_child_row_iv_hero_icon)

        var cvMainContainer = view.findViewById<CardView>(R.id.right_image_child_row_cv_main_container)
        cvMainContainer?.setOnClickListener(listner)
        cvMainContainer?.tag = adapterPosition

//        tvStoryTitle?.text = collectionItem?.headline

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        ivStoryHeroImage.setImageURI(heroImageURL)
    }
}