package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.InnerCollectionAdapter
import quintype.com.templatecollectionwithrx.models.*
import quintype.com.templatecollectionwithrx.models.collection.AssociatedMetadata
import quintype.com.templatecollectionwithrx.models.collection.CollectionItem
import quintype.com.templatecollectionwithrx.utils.Constants

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */
class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var rvInnerCollection: RecyclerView? = null

    fun bind(collectionItem: BulkTableModel) {
        val llCollectionName = itemView?.findViewById<LinearLayout>(R.id.default_collection_row_ll_collection_name)
        val tvCollectionName = itemView?.findViewById<TextView>(R.id.default_collection_row_tv_collection_name)

        var showCollectionName: Boolean
        var associatedMetadataShowCollectionName = collectionItem?.mOuterCollectionAssociatedMetadata?.associatedMetadataShowCollectionName
        if (associatedMetadataShowCollectionName != null) {
            showCollectionName = associatedMetadataShowCollectionName
        } else showCollectionName = true

        if (showCollectionName) {
            llCollectionName?.visibility = View.VISIBLE
            tvCollectionName?.text = collectionItem.outerCollectionName
        } else
            llCollectionName?.visibility = View.GONE

        rvInnerCollection = itemView?.findViewById<RecyclerView>(R.id.default_collection_row_rv_inner_collection)

        val collectionList = ArrayList<CollectionInnerListModel>()
        val collectionInnerList = collectionItem.innerCollectionResponse?.items
        if (collectionInnerList?.size != null)
            prepareLayoutEngine(collectionInnerList, collectionItem.mOuterCollectionAssociatedMetadata, collectionList, collectionItem.outerCollectionName)

        val innerCollectionAdapter = InnerCollectionAdapter(collectionList)

        rvInnerCollection?.adapter = innerCollectionAdapter
    }

    private fun prepareLayoutEngine(collectionInnerList: List<CollectionItem>, mOuterCollectionAssociatedMetadata: AssociatedMetadata?, collectionList: ArrayList<CollectionInnerListModel>, outerCollectionName: String?) {
        for (index in 0 until collectionInnerList.size) {
            val associatedMetadataLayout = mOuterCollectionAssociatedMetadata?.associatedMetadataLayout
            val layoutManager = LinearLayoutManager(rvInnerCollection?.context)

            if (associatedMetadataLayout != null)
                when (associatedMetadataLayout) {
                    Constants.HALF_IMAGE_SLIDER -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager

                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList, collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_HALF_IMAGE_SLIDER, mOuterCollectionAssociatedMetadata, outerCollectionName))
//                        else
//                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.TWO_COLUMN_GRID -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER, mOuterCollectionAssociatedMetadata, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    Constants.FULL_SCREEN_SIMPLE_SLIDER -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList, collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_FULL_SCREEN_SIMPLE_SLIDER, mOuterCollectionAssociatedMetadata, outerCollectionName))

//                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_UNDERLINE_SECTION, mOuterCollectionAssociatedMetadata))
//                        else
//                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.THREE_COLUMN -> {
                        val gridLayoutManager = GridLayoutManager(rvInnerCollection?.context, 2)

                        /**
                         * Added this to differentiate the header and child items
                         * if position is 0 then display the header item else display child item with 2 grid
                         */
                        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                when (position) {
                                    0 -> return 2
                                    else -> return 1
                                }
                            }
                        }
                        rvInnerCollection?.layoutManager = gridLayoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_GRID, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    Constants.FULL_SCREEN_LINEAR_GALLERY_SLIDER -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    Constants.TWO_COLUMN -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER, mOuterCollectionAssociatedMetadata, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    Constants.L_SHAPED_ONE_WIDGET -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    Constants.FULL_IMAGE_SLIDER -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList, collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_FULL_IMAGE_SLIDER, mOuterCollectionAssociatedMetadata, outerCollectionName))
//                        else
//                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.TWO_COLUMN_CAROUSEL -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    Constants.TWO_COLUMN_HIGHLIGHT -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    Constants.ONE_COLUMN_STORY_LIST -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    Constants.FOUR_COLUMN_GRID -> {
                        layoutManager.orientation = LinearLayout.HORIZONTAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HORIZONTAL, mOuterCollectionAssociatedMetadata, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HORIZONTAL, mOuterCollectionAssociatedMetadata, outerCollectionName))
                    }
                    else -> {
                        layoutManager.orientation = LinearLayout.VERTICAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, null, outerCollectionName))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, null, outerCollectionName))
                    }
                }
            else {
                layoutManager.orientation = LinearLayout.VERTICAL
                rvInnerCollection?.layoutManager = layoutManager
                if (index == 0)
                    collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, null, outerCollectionName))
                else
                    collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, null, outerCollectionName))
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CollectionViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.default_collection_row, parent, false)
            return CollectionViewHolder(view)
        }
    }
}