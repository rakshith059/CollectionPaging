package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.CollectionInnerListModel
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.TitleImageSliderViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.*
import quintype.com.templatecollectionwithrx.utils.Constants

class InnerCollectionAdapter(collectionItem: ArrayList<CollectionInnerListModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mCollectionItem = collectionItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION -> return TitleBelowImageBlockSectionViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_UNDERLINE_SECTION -> return TitleBelowImageUnderlineSectionViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD -> return LeftImageChildViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_FULL_IMAGE_SLIDER -> return TitleImageSliderViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_HALF_IMAGE_SLIDER -> return TitleImageSliderViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_FULL_SCREEN_SIMPLE_SLIDER -> return TitleImageSliderViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_HEADER -> return TitleInsideImageViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD -> return RightImageChildViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_TITLE_INSIDE_IMAGE_GRID -> return TitleInsideImageGridViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HORIZONTAL -> return TitleInsideImageHorizontalViewHolder.create(parent)
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
        var collectionName = mCollectionItem?.get(position)?.outerCollectionName

        if (holder is TitleBelowImageBlockSectionViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is TitleBelowImageUnderlineSectionViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is LeftImageChildViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is TitleInsideImageViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is RightImageChildViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is TitleImageSliderViewHolder) {
            holder.bind(mCollectionItem.get(position).collectionItemList, collectionAssociatedMetadata, collectionName)
        } else if (holder is TitleInsideImageGridViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata)
        } else if (holder is TitleInsideImageHorizontalViewHolder) {
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
