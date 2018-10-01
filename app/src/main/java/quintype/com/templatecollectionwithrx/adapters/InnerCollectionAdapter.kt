package quintype.com.templatecollectionwithrx.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.CollectionInnerListModel
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.fragments.StoryPagerFragment
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.*
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.TitleBelowImageBlockSectionViewHolder.Companion.mFragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks
import quintype.com.templatecollectionwithrx.utils.widgets.NetworkUtils

class InnerCollectionAdapter(collectionItem: ArrayList<CollectionInnerListModel>, mFragmentCallbacks: FragmentCallbacks?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
    private var mCollectionItem = collectionItem
    var fragmentCallbacks = mFragmentCallbacks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Constants.VIEWHOLDER_TYPE_TITLE_BELOW_IMAGE_HEADER_BLOCK_SECTION -> return TitleBelowImageBlockSectionViewHolder.create(parent, mFragmentCallbacks)
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
        if (mCollectionItem.size != null)
            return mCollectionItem.size
        else return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val collectionItemStory = mCollectionItem.get(position).story as Story
        val collectionAssociatedMetadata = mCollectionItem.get(position).associatedMetadata
        val collectionName = mCollectionItem.get(position).outerCollectionName

        if (holder is TitleBelowImageBlockSectionViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata, this)
        } else if (holder is TitleBelowImageUnderlineSectionViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata, this)
        } else if (holder is LeftImageChildViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata, this)
        } else if (holder is TitleInsideImageViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata, this)
        } else if (holder is RightImageChildViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata, this)
        } else if (holder is TitleImageSliderViewHolder) {
            holder.bind(mCollectionItem.get(position).collectionItemList, collectionAssociatedMetadata, collectionName, this)
        } else if (holder is TitleInsideImageGridViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata, this)
        } else if (holder is TitleInsideImageHorizontalViewHolder) {
            holder.bind(collectionItemStory, collectionAssociatedMetadata, this)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val viewHolderType = mCollectionItem[position].viewHolderType
        if (viewHolderType != null)
            return viewHolderType
        else return Constants.VIEWHOLDER_TYPE_STORY
    }

    override fun onClick(v: View?) {
        val mContext = v?.context as Context
        if (NetworkUtils.isConnected(mContext)) {
            val storyList: ArrayList<Story> = ArrayList()
            val itemPosition: Int = v.tag as Int

            for (index in 0 until mCollectionItem.size) {
                storyList.add(mCollectionItem.get(index).story as Story)
            }
            when (v.id) {
                R.id.title_below_image_block_section_header_row_cl_main_container,
                R.id.left_image_child_row_cv_main_container,
                R.id.right_image_child_row_cv_main_container,
                R.id.title_inside_image_header_row_cl_main_container,
                R.id.title_inside_image_grid_row_cl_main_container,
                R.id.title_below_image_block_section_horizontal_scroll_row_cl_main_container,
                R.id.pager_carousel_full_screen_simple_slider_row_cl_main_container,
                R.id.pager_carousel_title_inside_image_row_cl_main_container,
                R.id.pager_carousel_half_slider_row_cl_main_container,
                R.id.title_below_image_underline_section_header_row_cl_main_container ->
                    fragmentCallbacks?.addFragment(StoryPagerFragment.newInstance(storyList, itemPosition), "InnerCollectionAdapter")
            }
        } else
            Toast.makeText(mContext, mContext.resources.getString(R.string.no_internet), Toast.LENGTH_LONG).show()
    }
}
