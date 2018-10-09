package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */

class LeftImageChildViewHolder(itemView: View) : BaseTitleBelowImageUnderlineSectionViewHolder(itemView) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)
//        var tvStoryTitle = itemView.findViewById<TextView>(R.id.left_image_child_row_tv_title)
        var ivStoryHeroImage = itemView.findViewById<SimpleDraweeView>(R.id.left_image_child_row_iv_hero_icon)

        var cvMainContainer = itemView?.findViewById<CardView>(R.id.left_image_child_row_cv_main_container)
        cvMainContainer?.setOnClickListener(listner)
        cvMainContainer?.tag = adapterPosition

//        tvStoryTitle?.text = collectionItem?.headline

        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        ivStoryHeroImage.setImageURI(heroImageURL)

//        Glide.with(ivStoryHeroImage.context)
//                .load(heroImageURL)
//                .into(ivStoryHeroImage)
    }

    companion object {
        fun create(parent: ViewGroup): LeftImageChildViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.left_image_child_row, parent, false)
            return LeftImageChildViewHolder(view)
        }
    }
}