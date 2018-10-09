package quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.facebook.drawee.view.SimpleDraweeView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.ui.main.activities.WebActivity
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.story.Story

class TitleBelowImageUnderlineSectionViewHolder(itemView: View?) : BaseTitleBelowImageUnderlineSectionViewHolder(itemView) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?, listner: View.OnClickListener) {
        super.bind(collectionItem, collectionAssociatedMetadata, listner)

        var ivHeroImage = itemView?.findViewById<SimpleDraweeView>(R.id.title_below_image_underline_section_header_row_iv_hero_icon)
        val heroImageURL = cdnHostName + collectionItem.heroImageS3Key

        var clMainContainer = itemView?.findViewById<ConstraintLayout>(R.id.title_below_image_underline_section_header_row_cl_main_container)
        clMainContainer?.setOnClickListener(listner)
        clMainContainer?.tag = adapterPosition

//        Glide.with(ivHeroImage?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage)

        ivHeroImage?.setImageURI(heroImageURL)

        ivHeroImage?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(ivHeroImage.context, WebActivity::class.java)
                ivHeroImage.context.startActivity(intent)
            }
        })
    }

    companion object {
        fun create(parent: ViewGroup): TitleBelowImageUnderlineSectionViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.title_below_image_underline_section_header_row, parent, false)

            return TitleBelowImageUnderlineSectionViewHolder(view)
        }
    }
}