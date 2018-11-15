package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.fragments.StoryPagerFragment
import quintype.com.templatecollectionwithrx.ui.main.viewholders.NativeAdsViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.*
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */


class HomeCollectionAdapter(linkedCollectionList: List<BulkTableModel>, fragmentCallbacks: FragmentCallbacks?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
    var collectionList = linkedCollectionList
    var duplicateCollectionList = ArrayList<BulkTableModel>(collectionList)

    init {
        createDuplicateListWithAds()
    }

    private fun createDuplicateListWithAds() {
        for (index in 0 until collectionList.size) {
            if (index % 6 == 1)
                duplicateCollectionList.add(index, BulkTableModel(Constants.NATIVE_AD, null,
                        null, null,
                        null, null,
                        null, null))
        }
    }

    var mFragmentCallbacks = fragmentCallbacks
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Constants.TYPE_NATIVE_ADS -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.native_ad_view_holder, parent, false)
                return NativeAdsViewHolder(view)
            }
            Constants.TYPE_OUTER_COLLECTION -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.default_collection_row, parent, false)
                return CollectionViewHolder(view, mFragmentCallbacks)
            }
            Constants.TYPE_OUTER_STORY ->
                return StoryViewHolder.create(parent)
            Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.left_image_child_row, parent, false)
                return LeftImageChildViewHolder(view)
            }
            Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.right_image_child_row, parent, false)
                return RightImageChildViewHolder(view)
            }
            Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.title_below_image_block_section_header_row, parent, false)
                return TitleBelowImageBlockSectionViewHolder(view, mFragmentCallbacks)
            }
        }
        return StoryViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return duplicateCollectionList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CollectionViewHolder) {
            holder.bind(duplicateCollectionList.get(position))
        } else if (holder is StoryViewHolder) {
            holder.bind(duplicateCollectionList.get(position), this)
        } else if (holder is LeftImageChildViewHolder) {
            holder.bind(duplicateCollectionList.get(position).story as Story, duplicateCollectionList.get(position).mOuterCollectionAssociatedMetadata, this)
        } else if (holder is RightImageChildViewHolder) {
            holder.bind(duplicateCollectionList.get(position).story as Story, duplicateCollectionList.get(position).mOuterCollectionAssociatedMetadata, this)
        } else if (holder is TitleBelowImageBlockSectionViewHolder) {
            holder.bind(duplicateCollectionList.get(position).story as Story, duplicateCollectionList.get(position).mOuterCollectionAssociatedMetadata, this)
        } else if (holder is NativeAdsViewHolder) {
            holder.bind(this)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var itemStory = duplicateCollectionList.get(position).story

//        if (itemSlug?.equals(Constants.NATIVE_AD) as Boolean)
//            return Constants.TYPE_NATIVE_ADS
//        else
        if (position % 6 == 1) {
            return Constants.TYPE_NATIVE_ADS
        } else if (itemStory == null)
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
        duplicateCollectionList = ArrayList(collectionList)
        notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        val storyList: ArrayList<Story> = ArrayList()
        val itemPosition: Int = v?.tag as Int
        for (index in 0 until duplicateCollectionList.size) {
            if (duplicateCollectionList[index].story != null)
                storyList.add(duplicateCollectionList.get(index).story as Story)
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
