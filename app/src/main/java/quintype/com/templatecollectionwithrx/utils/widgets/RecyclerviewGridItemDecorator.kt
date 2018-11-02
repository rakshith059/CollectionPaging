package quintype.com.templatecollectionwithrx.utils.widgets

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class RecyclerviewGridItemDecorator(val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = padding / 2
        outRect.bottom = padding / 2
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.right = padding
        }
//        else {
        outRect.left = padding / 2
//        }
    }
}