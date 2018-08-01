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
import quintype.com.templatecollectionwithrx.models.BulkTableModel

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */
class DefaultCollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    var view = LayoutInflater.from(itemView.context).inflate(R.layout.default_collection_row, itemView, false)
//    var ivHeroImage = view?.findViewById<ImageView>(R.id.deafult_collection_row_iv_hero_icon)
//    var tvTitle = view?.findViewById<TextView>(R.id.default_collection_row_tv_title)

    fun bind(collectionItem: BulkTableModel) {
        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.deafult_collection_row_iv_hero_icon)
        var tvTitle = itemView?.findViewById<TextView>(R.id.default_collection_row_tv_title)
        var tvCollectionName = itemView?.findViewById<TextView>(R.id.default_collection_row_tv_collection_name)

        tvTitle?.text = collectionItem?.innerCollectionResponse?.items?.get(0)?.story?.headline
        tvCollectionName?.text = collectionItem?.outerCollectionName
//        ivHeroImage?.setImageResource(collectionItem?.innerCollectionResponse?.items?.get(0)?.story?.)

        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem?.innerCollectionResponse?.items?.get(0)?.story?.heroImageS3Key

        Glide.with(ivHeroImage?.context as Context)
                .load(heroImageURL)
                .into(ivHeroImage as ImageView)
    }

    companion object {
        fun create(parent: ViewGroup): DefaultCollectionViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.default_collection_row, parent, false)
            return DefaultCollectionViewHolder(view)
        }
    }
}