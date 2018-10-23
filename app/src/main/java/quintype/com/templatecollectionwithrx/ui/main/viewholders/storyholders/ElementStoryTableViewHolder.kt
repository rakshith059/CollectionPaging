package quintype.com.templatecollectionwithrx.ui.main.viewholders.storyholders

import android.R.attr.data
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.TableElementAdapter
import com.example.androidcore.models.story.StoryElement
import java.util.*
import kotlin.collections.ArrayList
import android.R.attr.data
import android.content.Context


class ElementStoryTableViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), Filterable {
    var entrySpinner: AppCompatSpinner? = null
    var tableView: RecyclerView? = null
    var etSearch: EditText? = null
    var previousButton: ImageButton? = null
    var nextButton: ImageButton? = null
    var tvEntryIndex: TextView? = null
    var verticalDottedLine: View? = null

    private var adapter: TableElementAdapter? = null
    private var searchData: ArrayList<String> = ArrayList()
    private var data: ArrayList<String>? = null
    private var entryCount: Int = 0
    private var spinnerPos: Int = 0
    private var hasHeader: Boolean = false
    private var mContext: Context? = null

    companion object {
        fun create(parent: ViewGroup): ElementStoryTableViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_element_table_view_holder, parent, false)

            val elementStoryTableViewHolder = ElementStoryTableViewHolder(view)
            elementStoryTableViewHolder.entrySpinner = view.findViewById(R.id.entries_selector)
            elementStoryTableViewHolder.tableView = view.findViewById(R.id.rv_table_view)
            elementStoryTableViewHolder.etSearch = view.findViewById(R.id.table_view_search_et)
            elementStoryTableViewHolder.previousButton = view.findViewById(R.id.table_view_previous_button)
            elementStoryTableViewHolder.nextButton = view.findViewById(R.id.table_view_next_button)
            elementStoryTableViewHolder.tvEntryIndex = view.findViewById(R.id.tv_item_index)
            elementStoryTableViewHolder.verticalDottedLine = view.findViewById(R.id.table_element_vertical_dotted_line)

            return elementStoryTableViewHolder
        }
    }

    fun bind(element: StoryElement) {
        mContext = itemView.context

        //setting up the spinner for the user to select the number of entries to be shown per page
        val spinnerAdapter = ArrayAdapter(
                mContext,
                android.R.layout.simple_spinner_dropdown_item,
                mContext?.resources?.getStringArray(R.array.entry_counts))
        entrySpinner?.adapter = spinnerAdapter
        //making sure that the entry count per page doesn't change on scrolling
        entrySpinner?.setSelection(spinnerPos)

        //getting the string array extracted from the content in the element
        Arrays.asList(element.data().content.split("\r\n"))
        data = ArrayList(element.data().content.split("\r\n"))
        hasHeader = element.subTypeMeta().hasHeader
        //getting the number of columns in the table
//        val spanCount = data?.get(0)?.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")?.size

        //Spliting the String by "," and getting the number of column
        val spanCount = data?.get(0)?.split(",")?.size

        //        final int spanCount = data.get(0).split("\\t", -1).length;
        //setting up the recyclerView with a gridLayout with the extracted spanCount
        val layoutManager = GridLayoutManager(mContext, spanCount as Int)
        tableView?.layoutManager = layoutManager as RecyclerView.LayoutManager?
        //making sure that scrolling through the story doesn't reset the table
        if (adapter == null)
        //setting the adapter with the extracted string data, whether the data has a header, and
        //how many columns the table has
            adapter = TableElementAdapter(mContext as Context, data as ArrayList, hasHeader, spanCount)
        tableView?.adapter = adapter

        entrySpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //keeping track of the number of entries per page that the user selected
                spinnerPos = position
                //changing the number of rows on spinner item change
                entryCount = Integer.valueOf(entrySpinner?.adapter?.getItem(position).toString())
                adapter?.setEntryCount(entryCount)
                setEntriesDisplayedText()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        //filtering the data according to the searched text
        etSearch?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                filter.filter(s)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    /**
     * shows the index and count of entries that the current page is showing
     */
    private fun setEntriesDisplayedText() {
        val pc = (tableView?.adapter as TableElementAdapter).pageCount
        tvEntryIndex?.text = if (adapter?.dataSize === 0)
            mContext?.getString(R.string.empty_data)
        else
            String.format(
                    mContext?.getString(R.string.table_entry_count) as String,
                    (pc - 1) * entryCount + 1, //1st tuple on the current page
                    Math.min(pc * entryCount, adapter?.dataSize as Int)
                    //last tuple according to number
                    //of entries or total data items
            )
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            protected override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    //if the searched text is empty simply set the whole data
                    searchData.clear()

                    searchData.addAll(data as List<String>)
                    //but remove the header since we already passed it when we created the adapter
                    if (hasHeader) searchData.removeAt(0)
                } else {
                    //else populate the filtered data set by iterating through the whole data
                    //and set it
                    val filteredList: ArrayList<String> = ArrayList()
                    for (i in (if (hasHeader) 1 else 0) until data?.size as Int) {
                        if (data?.get(i)?.toLowerCase()?.contains(charString.toLowerCase()) as Boolean) {
                            filteredList.add(data?.get(i) as String)
                        }
                    }
                    searchData = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = searchData
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                searchData = results?.values as ArrayList<String>
                //finally send the filtered data to the adapter
                adapter?.setData(searchData)
                setEntriesDisplayedText()
            }
        }
    }
}
