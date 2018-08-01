package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.BulkTableModel
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
            holder.bind(collectionList.get(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        var itemType = collectionList.get(position).story
        if (itemType == null)
            return TYPE_COLLECTION
        else return TYPE_STORY
    }
}