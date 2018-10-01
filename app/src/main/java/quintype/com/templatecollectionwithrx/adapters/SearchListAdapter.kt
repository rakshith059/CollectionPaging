package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.story.Story
import quintype.com.templatecollectionwithrx.ui.main.fragments.StoryPagerFragment
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.LeftImageChildViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.collectionholders.RightImageChildViewHolder
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.FragmentCallbacks

class SearchListAdapter(var mStoriesList: ArrayList<Story>, val mFragmentCallbacks: FragmentCallbacks?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        when (viewType) {
//            Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD -> return LeftImageChildViewHolder.create(parent)
//            Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD -> return RightImageChildViewHolder.create(parent)
//        }
        return LeftImageChildViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return mStoriesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mStory = mStoriesList.get(position)
        if (holder is LeftImageChildViewHolder)
            holder.bind(mStory, null, this)
        else if (holder is RightImageChildViewHolder)
            holder.bind(mStory, null, this)
    }

    fun notifyAdapter(mStoriesList: ArrayList<Story>) {
        this.mStoriesList = mStoriesList
        notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        val itemPosition: Int = v?.tag as Int

        when (v.id) {
            R.id.left_image_child_row_cv_main_container ->
                mFragmentCallbacks?.replaceFragment(StoryPagerFragment.newInstance(mStoriesList, itemPosition), TAG)
        }
    }

    override fun getItemViewType(position: Int): Int {
//        if (position % 2 == 0)
        return Constants.VIEWHOLDER_TYPE_LEFT_IMAGE_CHILD
//        else
//            return Constants.VIEWHOLDER_TYPE_RIGHT_IMAGE_CHILD
    }

    companion object {
        val TAG: String = SearchListAdapter.javaClass.simpleName
    }
}
