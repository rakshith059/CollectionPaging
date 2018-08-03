package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.CollectionInnerListModel
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.CollectionViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.DefaultStoryViewHolder

class InnerCollectionAdapter(collectionItem: ArrayList<CollectionInnerListModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mCollectionItem = collectionItem
    val TYPE_COLLECTION = 1000
    val TYPE_STORY = 1001

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_COLLECTION -> return CollectionViewHolder.create(parent)
            TYPE_STORY -> return DefaultStoryViewHolder.create(parent)
        }
        return DefaultStoryViewHolder.create(parent)
//        if (viewType == 0)
//            return CollectionViewHolder.create(parent)
//        else
//            return DefaultStoryViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        if (mCollectionItem?.size != null)
            return mCollectionItem?.size as Int
        else return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var collectionItemStory = mCollectionItem?.get(position)?.story as Story

        if (holder is CollectionViewHolder) {
            holder.bind(collectionItemStory)
        } else if (holder is DefaultStoryViewHolder) {
            holder.bind(collectionItemStory)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var collectionItemPosition = mCollectionItem?.get(position)?.position

        if (collectionItemPosition == 0)
            return TYPE_COLLECTION
        else
            return TYPE_STORY
//        var associatedMetadataLayout = mCollectionItem.mOuterCollectionAssociatedMetadata?.associatedMetadataLayout
//        if (associatedMetadataLayout != null)
//            when (associatedMetadataLayout) {
//                Constants.HALF_IMAGE_SLIDER -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.TWO_COLUMN_GRID -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.FULL_SCREEN_SIMPLE_SLIDER -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.THREE_COLUMN -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.FULL_SCREEN_LINEAR_GALLERY_SLIDER -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.TWO_COLUMN -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.L_SHAPED_ONE_WIDGET -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.FULL_IMAGE_SLIDER -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.TWO_COLUMN_CAROUSEL -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.TWO_COLUMN_HIGHLIGHT -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//                Constants.FOUR_COLUMN_GRID -> {
//                    var collectionInnerList = mCollectionItem.innerCollectionResponse?.items
//                    if (collectionInnerList != null)
//                        for (index in 0 until collectionInnerList?.size) {
//                            if (index == 0)
//                                return TYPE_COLLECTION
//                            else
//                                return TYPE_STORY
//                        }
//                }
//            }
        return TYPE_STORY
    }
}
