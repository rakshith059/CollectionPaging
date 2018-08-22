package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.CollectionInnerListModel
import quintype.com.templatecollectionwithrx.models.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.TitleBelowImageViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.LeftImageChildViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.RightImageChildViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.TitleInsideImageViewHolder
import quintype.com.templatecollectionwithrx.utils.Constants

class InnerCollectionAdapter(collectionItem: ArrayList<CollectionInnerListModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mCollectionItem = collectionItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER -> return TitleBelowImageViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD -> return LeftImageChildViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER -> return TitleInsideImageViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD -> return RightImageChildViewHolder.create(parent)
        }
        return LeftImageChildViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        if (mCollectionItem?.size != null)
            return mCollectionItem?.size
        else return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var collectionItemStory = mCollectionItem?.get(position)?.story as Story
        var collectionAssociatedMetadata = mCollectionItem?.get(position)?.associatedMetadata

        if (holder is TitleBelowImageViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is LeftImageChildViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is TitleInsideImageViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is RightImageChildViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var viewHolderType = mCollectionItem?.get(position)?.viewHolderType
        if (viewHolderType != null)
            return viewHolderType
        else return Constants.VIEWHOLDER_TYPE_STORY
    }
}
