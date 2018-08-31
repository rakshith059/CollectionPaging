package quintype.com.templatecollectionwithrx.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import quintype.com.templatecollectionwithrx.models.BulkTableModel
import quintype.com.templatecollectionwithrx.ui.main.viewholders.CollectionViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.StoryViewHolder

/**
 * Created TemplateCollectionWithRx by rakshith on 7/31/18.
 */


class HomeCollectionAdapter(linkedCollectionList: List<BulkTableModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var collectionList = linkedCollectionList
    val TYPE_OUTER_COLLECTION = 1000
    val TYPE_OUTER_STORY = 1001

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_OUTER_COLLECTION -> {
                return CollectionViewHolder.create(parent)
            }
            TYPE_OUTER_STORY -> {
                return StoryViewHolder.create(parent)
            }
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
        }
    }

    override fun getItemViewType(position: Int): Int {
        var itemType = collectionList.get(position).story
        if (itemType == null)
            return TYPE_OUTER_COLLECTION
        else return TYPE_OUTER_STORY
    }
}