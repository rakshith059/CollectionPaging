package quintype.com.templatecollectionwithrx.ui.main.viewholders

import android.view.View
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.adapters.DrawerSectionsAdapter
import com.example.androidcore.models.NavMenu
import quintype.com.templatecollectionwithrx.ui.main.navmenu.NavMenuGroup

class SectionChildViewHolder(itemView: View?) : ChildViewHolder(itemView) {

    public var mSubsectionTextView: TextView? = null
    var parent: NavMenuGroup? = null
    var mListener: DrawerSectionsAdapter.OnDrawerItemSelectedListener? = null

    constructor(itemView: View?, listener: DrawerSectionsAdapter.OnDrawerItemSelectedListener?) : this(itemView) {
        mSubsectionTextView = itemView?.findViewById<View>(R.id.child_section_name) as TextView
        mListener = listener
    }

    fun bind(subSection: NavMenu) {
        mSubsectionTextView?.text = subSection.title()
    }
}