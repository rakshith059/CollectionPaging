package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.WebActivity
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata

class TitleBelowImageUnderlineSectionViewHolder(itemView: View?) : BaseTitleBelowImageUnderlineSectionViewHolder(itemView) {
    override fun bind(collectionItem: Story, collectionAssociatedMetadata: AssociatedMetadata?) {
        super.bind(collectionItem, collectionAssociatedMetadata)

        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.title_below_image_underline_section_header_row_iv_hero_icon)
        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(ivHeroImage?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage)

        ivHeroImage.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(ivHeroImage.context, WebActivity::class.java)
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
