package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.*
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */


class HomeCollectionAdapter(linkedCollectionList: List<BulkTableModel>, fragmentCallbacks: FragmentCallbacks?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var collectionList = linkedCollectionList
    var mFragmentCallbacks = fragmentCallbacks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Constants.TYPE_OUTER_COLLECTION ->
                return CollectionViewHolder.create(parent, mFragmentCallbacks)
            Constants.TYPE_OUTER_STORY ->
                return StoryViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD ->
                return LeftImageChildViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD ->
                return RightImageChildViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION ->
                return TitleBelowImageBlockSectionViewHolder.create(parent, mFragmentCallbacks)
        }
        return StoryViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return collectionList?.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CollectionViewHolder) {
            holder.bind(collectionList.get(position))
        } else if (holder is StoryViewHolder) {
            holder.bind(collectionList.get(position))
        } else if (holder is LeftImageChildViewHolder) {
            holder.bind(collectionList.get(position).story as Story, collectionList.get(position).mOuterCollectionAssociatedMetadata)
        } else if (holder is RightImageChildViewHolder) {
            holder.bind(collectionList.get(position).story as Story, collectionList.get(position).mOuterCollectionAssociatedMetadata)
        } else if (holder is TitleBelowImageBlockSectionViewHolder) {
            holder.bind(collectionList.get(position).story as Story, collectionList.get(position).mOuterCollectionAssociatedMetadata)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var itemStory = collectionList.get(position).story
        if (itemStory == null)
            return Constants.TYPE_OUTER_COLLECTION
        else
            return Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION
//        else {
//            if (position % 2 == 0)
//                return Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD
//            else
//                return Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD
////            return TYPE_OUTER_STORY
//        }
    }

    fun notifyAdapter(linkedCollectionList: List<BulkTableModel>) {
        collectionList = linkedCollectionList
        notifyDataSetChanged()
    }
}