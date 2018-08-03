package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.Story

class CollectionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(collectionItem: Story) {
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.collection_row_iv_hero_icon)
        var tvTitle = itemView?.findViewById<TextView>(R.id.collection_row_tv_title)

        tvTitle?.text = collectionItem?.headline

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem.heroImageS3Key

        Glide.with(ivHeroImage?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage)
    }

    companion object {
        fun create(parent: ViewGroup): CollectionViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.collection_row, parent, false)
            return CollectionViewHolder(view)
        }
    }

}
