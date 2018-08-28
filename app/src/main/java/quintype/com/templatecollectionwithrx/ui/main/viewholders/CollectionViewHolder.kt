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
import quintype.com.templatecollectionwithrx.utils.Constants

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */
class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var rvInnerCollection: RecyclerView? = null

    fun bind(collectionItem: BulkTableModel) {
        val tvCollectionName = itemView?.findViewById<TextView>(R.id.default_collection_row_tv_collection_name)

        var showCollectionName: Boolean
        var associatedMetadataShowCollectionName = collectionItem?.mOuterCollectionAssociatedMetadata?.associatedMetadataShowCollectionName
        if (associatedMetadataShowCollectionName != null) {
            showCollectionName = associatedMetadataShowCollectionName
        } else showCollectionName = true
        
        if (showCollectionName) {
            tvCollectionName?.visibility = View.VISIBLE
            tvCollectionName?.text = collectionItem.outerCollectionName
        } else
            tvCollectionName?.visibility = View.GONE

        rvInnerCollection = itemView?.findViewById<RecyclerView>(R.id.default_collection_row_rv_inner_collection)

        val collectionList = ArrayList<CollectionInnerListModel>()
        val collectionInnerList = collectionItem.innerCollectionResponse?.items
        if (collectionInnerList?.size != null)
            prepareLayoutEngine(collectionInnerList, collectionItem.mOuterCollectionAssociatedMetadata, collectionList)

        val innerCollectionAdapter = InnerCollectionAdapter(collectionList)

        rvInnerCollection?.adapter = innerCollectionAdapter
    }

    private fun prepareLayoutEngine(collectionInnerList: List<CollectionItem>, mOuterCollectionAssociatedMetadata: AssociatedMetadata?, collectionList: ArrayList<CollectionInnerListModel>) {
        for (index in 0 until collectionInnerList.size) {
            val associatedMetadataLayout = mOuterCollectionAssociatedMetadata?.associatedMetadataLayout
            if (associatedMetadataLayout != null)
                when (associatedMetadataLayout) {
                    Constants.HALF_IMAGE_SLIDER -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList, collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_SLIDER, mOuterCollectionAssociatedMetadata))
//                        else
//                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.TWO_COLUMN_GRID -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.FULL_SCREEN_SIMPLE_SLIDER -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_UNDERLINE_SECTION, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story as Story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
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
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER, mOuterCollectionAssociatedMetadata))

                    }
                    Constants.FULL_SCREEN_LINEAR_GALLERY_SLIDER -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.TWO_COLUMN -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.L_SHAPED_ONE_WIDGET -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.FULL_IMAGE_SLIDER -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList, collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_SLIDER, mOuterCollectionAssociatedMetadata))
//                        else
//                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.TWO_COLUMN_CAROUSEL -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.TWO_COLUMN_HIGHLIGHT -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.ONE_COLUMN_STORY_LIST -> {
                        collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, mOuterCollectionAssociatedMetadata))
                    }
                    Constants.FOUR_COLUMN_GRID -> {
                        val layoutManager = LinearLayoutManager(rvInnerCollection?.context)
                        layoutManager.orientation = LinearLayout.HORIZONTAL
                        rvInnerCollection?.layoutManager = layoutManager
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, mOuterCollectionAssociatedMetadata))
                    }
                    else -> {
                        if (index == 0)
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, null))
                        else
                            collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, null))
                    }
                }
            else {
                if (index == 0)
                    collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION, null))
                else
                    collectionList.add(CollectionInnerListModel(collectionInnerList.get(index).story, Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD, null))
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