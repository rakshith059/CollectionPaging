package quintype.com.templatecollectionwithrx.utils.widgets

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by rakshith on 10/29/18.
 */
class RecyclerItemDecorator() : RecyclerView.ItemDecoration() {
    internal var isStoryHasHeroImage: Boolean = false
    internal var paddingLeft: Int = 0
    internal var paddingTop: Int = 0
    internal var paddingRight: Int = 0
    internal var paddingBottom: Int = 0

    constructor(isStoryHasHeroImage: Boolean, paddingLeft: Int, paddingTop: Int, paddingRight: Int, paddingBottom: Int) : this() {
        this.isStoryHasHeroImage = isStoryHasHeroImage
        this.paddingLeft = paddingLeft
        this.paddingTop = paddingTop
        this.paddingRight = paddingRight
        this.paddingBottom = paddingBottom
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        if (isStoryHasHeroImage) {
            if (parent.getChildLayoutPosition(view) != 0) {
                if (parent.getChildLayoutPosition(view) == 1) {
                    outRect.top = paddingTop
                }
                outRect.left = paddingLeft
                outRect.right = paddingRight
                outRect.bottom = paddingBottom
            }
        } else {
            if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
                outRect.top = paddingTop
            }
            outRect.left = paddingLeft
            outRect.right = paddingRight
            outRect.bottom = paddingBottom
        }
    }
}