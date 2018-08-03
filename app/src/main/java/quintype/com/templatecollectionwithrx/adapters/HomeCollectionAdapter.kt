package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.CollectionItem
import quintype.com.templatecollectionwithrx.ui.main.viewholders.DefaultCollectionViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.DefaultStoryViewHolder
import quintype.com.templatecollectionwithrx.utils.Constants

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */


class HomeCollectionAdapter(linkedCollectionList: List<BulkTableModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var collectionList = linkedCollectionList
    val TYPE_COLLECTION = 1000
    val TYPE_STORY = 1001

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_COLLECTION -> {
                return DefaultCollectionViewHolder.create(parent)
            }
            TYPE_STORY -> {
                return DefaultStoryViewHolder.create(parent)
            }
        }
        return DefaultStoryViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return collectionList?.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DefaultCollectionViewHolder) {
            holder.bind(collectionList.get(position))
        } else if (holder is DefaultStoryViewHolder) {
//            holder.bind(collectionList.get(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        var itemType = collectionList.get(position).story
        if (itemType == null)
            return TYPE_COLLECTION
        else return TYPE_STORY

//        var associatedMetadataLayout = collectionList.get(position).mOuterCollectionAssociatedMetadata?.associatedMetadataLayout
//        if (associatedMetadataLayout != null)
//            when (associatedMetadataLayout) {
//                Constants.HALF_IMAGE_SLIDER -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.TWO_COLUMN_GRID -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.FULL_SCREEN_SIMPLE_SLIDER -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.THREE_COLUMN -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.FULL_SCREEN_LINEAR_GALLERY_SLIDER -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items as List<CollectionItem>
//                    for (index in 0 until collectionInnerList?.size) {
//                        if (index == 0)
//                            return TYPE_COLLECTION
//                        else
//                            return TYPE_STORY
//                    }
//                }
//                Constants.TWO_COLUMN -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.L_SHAPED_ONE_WIDGET -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.FULL_IMAGE_SLIDER -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.TWO_COLUMN_CAROUSEL -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.TWO_COLUMN_HIGHLIGHT -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.FOUR_COLUMN_GRID -> {
//                    var collectionInnerList = collectionList.get(position).innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//            }
//        return TYPE_STORY
    }
}