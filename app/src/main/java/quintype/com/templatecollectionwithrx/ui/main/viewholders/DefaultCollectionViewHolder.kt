package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.InnerCollectionAdapter
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.CollectionInnerListModel
import quintype.com.templatecollectionwithrx.models.Story

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */
class DefaultCollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(collectionItem: BulkTableModel) {
//        var ivHeroImage = itemView?.findViewById<ImageView>(R.id.deafult_collection_row_iv_hero_icon)
//        var tvTitle = itemView?.findViewById<TextView>(R.id.default_collection_row_tv_title)
        var tvCollectionName = itemView?.findViewById<TextView>(R.id.default_collection_row_tv_collection_name)
//
//        tvTitle?.text = collectionItem?.innerCollectionResponse?.items?.get(0)?.story?.headline
        tvCollectionName?.text = collectionItem?.outerCollectionName
////        ivHeroImage?.setImageResource(collectionItem?.innerCollectionResponse?.items?.get(0)?.story?.)
//
//        val heroImageURL = "https://" + "images.assettype.com" + "/" + collectionItem?.innerCollectionResponse?.items?.get(0)?.story?.heroImageS3Key
//
//        Glide.with(ivHeroImage?.context as Context)
//                .load(heroImageURL)
//                .into(ivHeroImage as ImageView)

        var rvInnerCollection = itemView?.findViewById<RecyclerView>(R.id.default_collection_row_rv_inner_collection)

        var layoutManager = LinearLayoutManager(rvInnerCollection?.context)
        rvInnerCollection?.layoutManager = layoutManager

        var collectionInnerList = collectionItem?.innerCollectionResponse?.items
        var collectionList = ArrayList<CollectionInnerListModel>()
        if (collectionInnerList?.size != null)
            for (index in 0 until collectionInnerList?.size) {
                collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, index))
            }

        var innerCollectionAdapter = InnerCollectionAdapter(collectionList)

        rvInnerCollection?.adapter = innerCollectionAdapter
        innerCollectionAdapter.notifyDataSetChanged()
    }

    companion object {
        fun create(parent: ViewGroup): DefaultCollectionViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.default_collection_row, parent, false)
            return DefaultCollectionViewHolder(view)
        }
    }
}