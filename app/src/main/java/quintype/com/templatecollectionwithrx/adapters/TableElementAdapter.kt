package quintype.com.templatecollectionwithrx.adapters

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import quintype.com.templatecollectionwithrx.R
import java.util.*

class TableElementAdapter
/**
 * @param context   the context in which this adapter is being used
 * @param data      the data to be set into the table
 * @param hasHeader whether the data has the first line as the header for the subsequent lines
 * @param spanCount how many columns in each row
 */
(private val mContext: Context, data: List<String>, hasHeader: Boolean, private val columnCount: Int) : RecyclerView.Adapter<TableElementAdapter.CellViewHolder>() {
    private var data: MutableList<String> = ArrayList()
    private var headers: Array<String>? = null
    private var rowData: Array<String>? = null
    private var rowIndex = 0
    var pageCount = 1
        private set
    private var rowCount: Int = 0

    val dataSize: Int
        get() = data.size

    init {
        this.data.clear()
        this.data.addAll(data)
        //separating the header from the actual data
        if (hasHeader) {
            headers = this.data[0].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)".toRegex()).toTypedArray()
            //            headers = this.data.get(0).split("\\t", -1);
            this.data.removeAt(0)
        }
        //sorting the data alphabetically
        //        Collections.sort(this.data, COMPARATOR);
    }

    /**
     * changes the number of rows shown per page
     *
     * @param entryCount number of rows to be shown
     */
    fun setEntryCount(entryCount: Int) {
        rowCount = entryCount
        rowIndex = (pageCount - 1) * entryCount //reset the first item in the page
        notifyDataSetChanged()
    }

    fun setData(data: MutableList<String>) {
        this.data = data
        //sorting the data alphabetically
        Collections.sort(this.data, COMPARATOR)
        //resetting the adapter position and notifying DS changed
        rowIndex = 0
        pageCount = 1
        notifyDataSetChanged()
    }

    /**
     * moves to the next page of the data according to the number of rows shown per page
     */
    fun incrementPageCount() {
        //check if the page can be increased
        if (pageCount * rowCount < data.size) {
            rowIndex = rowCount * pageCount++ //set the first item in the page
            notifyDataSetChanged()
        }
    }

    /**
     * moves to the previous page of the data according to the number of rows shown per page
     */
    fun decrementPageCount() {
        //check if the page can be decreased
        if (pageCount > 1) {
            rowIndex = rowCount * (--pageCount - 1) //set the first item in the page
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_cell_itemview, parent, false)
        return CellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        if (headers != null && position < columnCount) {
            //formatting the header items and displaying them
            holder.textView.setBackgroundColor(Color.BLACK)
            holder.textView.setTextColor(Color.WHITE)
            try {
                holder.textView.text = headers?.get(position)
            } catch (arrayIndexOutOfBoundException: ArrayIndexOutOfBoundsException) {
                arrayIndexOutOfBoundException.stackTrace
            }
        } else {
            //if there are no headers or if the row is any one other than the header row
            //get the current cell position first
            val cellIndex = position % columnCount

            //if the cell is the first in a row, first get all the values for all the cells in
            //that row and increment rowCount
            if (cellIndex == 0 && rowIndex < data.size)
            // the following regex means split all comma separated values
            // except if bounded by quotes
                rowData = data[rowIndex++].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)".toRegex()).toTypedArray()
            //                rowData = data.get(rowIndex++).split("\\t", -1);

            //formatting the background color for the row according to rowCount
            if (rowIndex % 2 == 0)
                holder.textView.setBackgroundColor(Color.LTGRAY)
            else
                holder.textView.setBackgroundColor(
                        ContextCompat.getColor(mContext, R.color.concrete))

            //formatting the textColor to make it different from that of the header
            holder.textView.setTextColor(
                    ContextCompat.getColor(mContext, R.color.light_black))

            //finally, setting the cell data
            if (rowData?.size as Int > 1)
                try {
                    holder.textView.text = rowData?.get(cellIndex)?.replace("\"", "")
                } catch (arrayIndexOutOfBoundException: ArrayIndexOutOfBoundsException) {
                    arrayIndexOutOfBoundException.stackTrace
                }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        //number of cells in each column in the current page
        val cellCount = Math.min(rowCount, data.size - (pageCount - 1) * rowCount)
        //total number of cells in the current page
        return columnCount * if (headers != null) cellCount + 1 else cellCount
    }

    inner class CellViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView: TextView

        init {
            textView = itemView.findViewById(R.id.tv_cell_data)
        }
    }

    companion object {
        private val COMPARATOR = Comparator<String> { t1, t2 -> t1.compareTo(t2, ignoreCase = true) }
    }
}
