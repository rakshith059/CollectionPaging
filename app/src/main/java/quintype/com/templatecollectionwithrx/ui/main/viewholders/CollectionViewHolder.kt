package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.InnerCollectionAdapter
import quintype.com.templatecollectionwithrx.models.*
import quintype.com.templatecollectionwithrx.utils.Constants

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */
class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(collectionItem: BulkTableModel) {
        var tvCollectionName = itemView?.findViewById<TextView>(R.id.default_collection_row_tv_collection_name)
        tvCollectionName?.text = collectionItem?.outerCollectionName

        var rvInnerCollection = itemView?.findViewById<RecyclerView>(R.id.default_collection_row_rv_inner_collection)

        var layoutManager = LinearLayoutManager(rvInnerCollection?.context)
        rvInnerCollection?.layoutManager = layoutManager

        var collectionList = ArrayList<CollectionInnerListModel>()
        var collectionInnerList = collectionItem?.innerCollectionResponse?.items
        if (collectionInnerList?.size != null)
            prepareLayoutEngine(collectionInnerList, collectionItem?.mOuterCollectionAssociatedMetadata, collectionList)

        var innerCollectionAdapter = InnerCollectionAdapter(collectionList)

        rvInnerCollection?.adapter = innerCollectionAdapter
        innerCollectionAdapter.notifyDataSetChanged()
    }

    private fun prepareLayoutEngine(collectionInnerList: List<CollectionItem>, mOuterCollectionAssociatedMetadata: AssociatedMetadata?, collectionList: ArrayList<CollectionInnerListModel>) {
        for (index in 0 until collectionInnerList?.size) {
            var associatedMetadataLayout = mOuterCollectionAssociatedMetadata?.associatedMetadataLayout
            if (associatedMetadataLayout != null)
                when (associatedMetadataLayout) {
                    Constants.HALF_IMAGE_SLIDER ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))

                    Constants.TWO_COLUMN_GRID ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD))
                    Constants.FULL_SCREEN_SIMPLE_SLIDER ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
                    Constants.THREE_COLUMN ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
                    Constants.FULL_SCREEN_LINEAR_GALLERY_SLIDER ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
                    Constants.TWO_COLUMN ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD))
                    Constants.L_SHAPED_ONE_WIDGET ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
                    Constants.FULL_IMAGE_SLIDER ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
                    Constants.TWO_COLUMN_CAROUSEL ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
                    Constants.TWO_COLUMN_HIGHLIGHT ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
                    Constants.FOUR_COLUMN_GRID ->
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
                }
            else
                if (index == 0)
                    collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER))
                else
                    collectionList.add(CollectionInnerListModel(collectionInnerList?.get(index)?.story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD))
        }
    }

    companion object {
        fun create(parent: ViewGroup): CollectionViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.default_collection_row, parent, false)
            return CollectionViewHolder(view)
        }
    }
}