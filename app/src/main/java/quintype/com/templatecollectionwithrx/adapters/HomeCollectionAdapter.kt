package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.fragments.StoryPagerFragment
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.*
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */


class HomeCollectionAdapter(linkedCollectionList: List<BulkTableModel>, fragmentCallbacks: FragmentCallbacks?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
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
            holder.bind(collectionList.get(position).story as Story, collectionList.get(position).mOuterCollectionAssociatedMetadata, this)
        } else if (holder is RightImageChildViewHolder) {
            holder.bind(collectionList.get(position).story as Story, collectionList.get(position).mOuterCollectionAssociatedMetadata, this)
        } else if (holder is TitleBelowImageBlockSectionViewHolder) {
            holder.bind(collectionList.get(position).story as Story, collectionList.get(position).mOuterCollectionAssociatedMetadata, this)
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

    override fun onClick(v: View?) {
        val storyList: ArrayList<Story> = ArrayList()
        val itemPosition: Int = v?.tag as Int
        for (index in 0 until collectionList.size) {
            if (collectionList[index].story != null)
                storyList.add(collectionList.get(index).story as Story)
        }
        when (v.id) {
            R.id.title_below_image_block_section_header_row_cl_main_container,
            R.id.left_image_child_row_cv_main_container,
            R.id.right_image_child_row_cv_main_container,
            R.id.title_below_image_underline_section_header_row_cl_main_container ->
                mFragmentCallbacks?.replaceFragment(StoryPagerFragment.newInstance(storyList, itemPosition), "HomeCollectionAdapter")
        }
    }
}
