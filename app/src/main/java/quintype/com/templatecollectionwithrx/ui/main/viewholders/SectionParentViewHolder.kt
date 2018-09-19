package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.DrawerSectionsAdapter
import quintype.com.templatecollectionwithrx.models.NavMenuGroup

open class SectionParentViewHolder(itemView: View?) : ParentViewHolder(itemView) {

    companion object {
        //viewType position constants
        val HEADER_VIEW = 0
        val OTHER_VIEW = 1
        val FOOTER_VIEW = 2
    }

    //Constants required for expand button rotation animation
    private val INITIAL_POSITION = 0.0f
    private val ROTATED_POSITION = 180f

    var mSectionName: TextView? = null
    var mNotificationCount: TextView? = null
    var mExpandBtn: ImageButton? = null
    var mListner: DrawerSectionsAdapter.OnDrawerItemSelectedListener? = null

    constructor(itemView: View?, listener: DrawerSectionsAdapter.OnDrawerItemSelectedListener?, viewType: Int) : this(itemView) {
        if (viewType != HEADER_VIEW && viewType != FOOTER_VIEW) {
            //initialize all the member variables
            mExpandBtn = itemView?.findViewById<View>(R.id.section_expand_button) as ImageButton
            mSectionName = itemView.findViewById<View>(R.id.section_name) as TextView
            mNotificationCount = itemView.findViewById<View>(R.id.notification_count) as TextView
            mListner = listener
        }
    }

    override fun setExpanded(expanded: Boolean) {
        super.setExpanded(expanded)
        if (mExpandBtn != null) {
            if (expanded) {
                this.mExpandBtn!!.rotation = ROTATED_POSITION
            } else {
                mExpandBtn!!.rotation = INITIAL_POSITION
            }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onExpansionToggled(expanded: Boolean) {
        super.onExpansionToggled(expanded)
        var rotateAnimation: RotateAnimation? = null
        if (mExpandBtn != null)
            if (expanded) { // rotate clockwise
                rotateAnimation = RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f)
            } else { // rotate counterclockwise
                rotateAnimation = RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f)
            }

        rotateAnimation?.duration = 200
        rotateAnimation?.fillAfter = true
        mExpandBtn?.startAnimation(rotateAnimation)
    }

    //to make sure that clicking on a parent section name does not expand the parent.
    //only clicking on the expand button should expand a parent.
    override fun shouldItemViewClickToggleExpansion(): Boolean {
        return false
    }

    fun bind(menuItem: NavMenuGroup) {
        if (mSectionName != null) {
            //set the name of the menu item in the drawer
            mSectionName!!.text = menuItem.getName(mSectionName!!.context)
            //if a section doesn't have subsections, don't show the expand button
            if (menuItem.childItemList.size == 0) {
                mExpandBtn?.setVisibility(View.GONE)
            } else
                mExpandBtn?.setVisibility(View.VISIBLE)

            //clickListener for the expand button
            mExpandBtn?.setOnClickListener(View.OnClickListener {
                if (isExpanded) {
                    //if the parent is expanded, collapse it
                    collapseView()
                } else {
                    //else, expand it
                    expandView()
                }
            })
        }
    }
}