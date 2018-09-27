//package quintype.com.templatecollectionwithrx.adapters;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//import quintype.com.templatecollectionwithrx.R;
//
//public class TableElementAdapter extends RecyclerView.Adapter<TableElementAdapter.CellViewHolder> {
//
//    private static final Comparator<String> COMPARATOR = new Comparator<String>() {
//        @Override
//        public int compare(String t1, String t2) {
//            return t1.compareToIgnoreCase(t2);
//        }
//    };
//    private List<String> data = new ArrayList<>();
//    private String[] headers, rowData;
//    private int rowIndex = 0;
//    private int pageCount = 1;
//    private int columnCount, rowCount;
//    private Context mContext;
//
//    /**
//     * @param context   the context in which this adapter is being used
//     * @param data      the data to be set into the table
//     * @param hasHeader whether the data has the first line as the header for the subsequent lines
//     * @param spanCount how many columns in each row
//     */
//    public TableElementAdapter(Context context, List<String> data, boolean hasHeader, int spanCount) {
//        columnCount = spanCount;
//        mContext = context;
//        this.data.clear();
//        this.data.addAll(data);
//        //separating the header from the actual data
//        if (hasHeader) {
//            headers = this.data.get(0).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
////            headers = this.data.get(0).split("\\t", -1);
//            this.data.remove(0);
//        }
//        //sorting the data alphabetically
////        Collections.sort(this.data, COMPARATOR);
//    }
//
//    /**
//     * changes the number of rows shown per page
//     *
//     * @param entryCount number of rows to be shown
//     */
//    public void setEntryCount(int entryCount) {
//        rowCount = entryCount;
//        rowIndex = (pageCount - 1) * entryCount; //reset the first item in the page
//        notifyDataSetChanged();
//    }
//
//    public void setData(List<String> data) {
//        this.data = data;
//        //sorting the data alphabetically
//        Collections.sort(this.data, COMPARATOR);
//        //resetting the adapter position and notifying DS changed
//        rowIndex = 0;
//        pageCount = 1;
//        notifyDataSetChanged();
//    }
//
//    public int getDataSize() {
//        return data.size();
//    }
//
//    /**
//     * moves to the next page of the data according to the number of rows shown per page
//     */
//    public void incrementPageCount() {
//        //check if the page can be increased
//        if (pageCount * rowCount < data.size()) {
//            rowIndex = rowCount * pageCount++; //set the first item in the page
//            notifyDataSetChanged();
//        }
//    }
//
//    /**
//     * moves to the previous page of the data according to the number of rows shown per page
//     */
//    public void decrementPageCount() {
//        //check if the page can be decreased
//        if (pageCount > 1) {
//            rowIndex = rowCount * (--pageCount - 1); //set the first item in the page
//            notifyDataSetChanged();
//        }
//    }
//
//    public int getPageCount() {
//        return pageCount;
//    }
//
//    @Override
//    public CellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_itemview, parent, false);
//        return new CellViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(CellViewHolder holder, int position) {
//        if (headers != null && position < columnCount) {
//            //formatting the header items and displaying them
//            holder.textView.setBackgroundColor(Color.BLACK);
//            holder.textView.setTextColor(Color.WHITE);
//            holder.textView.setText(headers[position]);
//        } else {
//            //if there are no headers or if the row is any one other than the header row
//            //get the current cell position first
//            int cellIndex = position % columnCount;
//
//            //if the cell is the first in a row, first get all the values for all the cells in
//            //that row and increment rowCount
//            if (cellIndex == 0 && rowIndex < data.size())
//                // the following regex means split all comma separated values
//                // except if bounded by quotes
//                rowData = data.get(rowIndex++).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
////                rowData = data.get(rowIndex++).split("\\t", -1);
//
//            //formatting the background color for the row according to rowCount
//            if (rowIndex % 2 == 0) holder.textView.setBackgroundColor(Color.LTGRAY);
//            else
//                holder.textView.setBackgroundColor(
//                        ContextCompat.getColor(mContext, R.color.concrete));
//
//            //formatting the textColor to make it different from that of the header
//            holder.textView.setTextColor(
//                    ContextCompat.getColor(mContext, R.color.light_black));
//
//            //finally, setting the cell data
//            if (rowData.length > 1)
//                holder.textView.setText(rowData[cellIndex].replace("\"", ""));
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemCount() {
//        //number of cells in each column in the current page
//        int cellCount = Math.min(rowCount, (data.size() - (pageCount - 1) * rowCount));
//        //total number of cells in the current page
//        return columnCount * (headers != null ? cellCount + 1 : cellCount);
//    }
//
//    protected class CellViewHolder extends RecyclerView.ViewHolder {
//        TextView textView;
//
//        CellViewHolder(View itemView) {
//            super(itemView);
//            textView = ((TextView) itemView.findViewById(R.id.tv_cell_data));
//        }
//    }
//}
