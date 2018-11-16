package quintype.com.templatecollectionwithrx.adapters

import android.content.Context
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem
import quintype.com.templatecollectionwithrx.R
import quintype.com.templatecollectionwithrx.models.NavMenu
import quintype.com.templatecollectionwithrx.models.NavMenuGroup
import quintype.com.templatecollectionwithrx.ui.main.viewholders.SectionChildViewHolder
import quintype.com.templatecollectionwithrx.ui.main.viewholders.SectionParentViewHolder
import quintype.com.templatecollectionwithrx.utils.Constants
import quintype.com.templatecollectionwithrx.utils.Utilities

class DrawerSectionsAdapter(context: Context, parentItemList: MutableList<ParentListItem>) : ExpandableRecyclerAdapter<SectionParentViewHolder, SectionChildViewHolder>(parentItemList) {

    private val TYPE_HEADER = 1000
    private val TYPE_FOOTER = 2000
    private var selectedNavMenuGroup: NavMenuGroup? = null
    private val mParentItemList = parentItemList
    private val mInflater = LayoutInflater.from(context)
    private var mListener: OnDrawerItemSelectedListener? = null
    private val mContext = context

    init {
        //Check if the calling activity/fragment has implemented the item selection listener.
        //otherwise, item clicks cannot be handled.
        if (context is OnDrawerItemSelectedListener)
            mListener = context
        else
            throw RuntimeException(context.toString() + " must implement " +
                    "OnAddItemsInteractionListener")
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nav_header_main,
                    viewGroup, false) //Inflating the layout

            return HeaderViewHolder(v) // Returning the created object
        }

        if (viewType == TYPE_FOOTER) {
            val view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_privacy_policy,
                    viewGroup, false)
            return FooterViewHolder(view)
        }
        return super.onCreateViewHolder(viewGroup, viewType)
    }

    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup?): SectionParentViewHolder {
        val parentSection = mInflater?.inflate(R.layout.section_parent_list_item,
                parentViewGroup, false)
        return SectionParentViewHolder(parentSection, mListener, SectionParentViewHolder.OTHER_VIEW)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup?): SectionChildViewHolder {
        val childSection = mInflater?.inflate(R.layout.section_child_list_item, childViewGroup,
                false)
        return SectionChildViewHolder(childSection, mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position > 0) {
            super.onBindViewHolder(holder, position)
        }
    }

    override fun onBindChildViewHolder(childViewHolder: SectionChildViewHolder?, position: Int, childListItem: Any?) {
        val subsection = childListItem as NavMenu
        childViewHolder?.bind(subsection)
        //setting the click listener for the child sections
        childViewHolder?.mSubsectionTextView?.setOnClickListener {
            val parent: NavMenuGroup?
            for (i in 1 until parentItemList.size) {
                val item = parentItemList[i]
                //find out which parent this child belongs to
                if (subsection.parentId().toInt() == (item as NavMenuGroup).menuItem?.id()?.toInt()) {
                    parent = item
                    for (j in 0 until parent.childItemList.size) {
                        if (parent.childItemList.get(j)?.id()?.toInt() == subsection.id()?.toInt()) {
                            //set that position to the NavMenuGroup object and open the viewPager accordingly
                            parent.position = j
                            break
                        }
                    }

                    //setting the selected state for the parent of this child,
                    //same as that done in the ParentViewHolder
                    if (selectedNavMenuGroup != null) {
                        selectedNavMenuGroup?.isSelected = false
                    }
                    parent.isSelected = true
                    selectedNavMenuGroup = parent
                    notifyDataSetChanged()
                    mListener?.onDrawerItemSelected(parent)
                    break
                }
            }
        }
    }

    override fun onBindParentViewHolder(parentViewHolder: SectionParentViewHolder?, position: Int, parentListItem: ParentListItem?) {
        val section = parentListItem as NavMenuGroup
        if (!isPositionFooter(position)) {
            parentViewHolder?.bind(section)
            /*If the position is notifications then setting the name as Notifications*/
            if (Constants.NAVMENU_GROUP_NOTIFICATIONS_POSITION.equals(section.dummyValue)) {
                parentViewHolder?.mSectionName?.text = mContext?.getResources()?.getString(R.string.notification_title)
                //TODO : Not Implemeted local DB to store notifications.
                //val notificationCountValue = DataBaseHelper.getInstance(mContext).getOverAllUnreadNotificationCount()
                parentViewHolder?.mNotificationCount?.text = "0"
                parentViewHolder?.mNotificationCount?.setVisibility(View.VISIBLE)
            } else {
                parentViewHolder?.mNotificationCount?.setVisibility(View.GONE)
            }

            //This section sets the selected state of the navDrawer.
            val color: Int
            if (section.isSelected) {
                // Check if the section is selected and set the text color accordingly
                color = ContextCompat.getColor(mListener as Context, R.color.colorAccent)
                parentViewHolder?.mSectionName?.setTextColor(color)
            } else {
                color = ContextCompat.getColor(mListener as Context, R.color.colorPrimary)
                parentViewHolder?.mSectionName?.setTextColor(color)
            }

            //clickListener for the parent item name
            parentViewHolder?.mSectionName?.setOnClickListener {
                if (Constants.NAVMENU_GROUP_NOTIFICATIONS_POSITION.equals(section.dummyValue)) {
                    Log.d("DrawerSectionsAdapter", "Notifications menu clicked, ")
                    mListener?.onDrawerItemSelected(null)
                } else {
                    //deselect the previously selected menuGroup, select the current menuGroup
                    //and keep track of it. Then notifyDataSetChanged to change the selected
                    //state in the navDrawer.
                    if (selectedNavMenuGroup != null) {
                        selectedNavMenuGroup?.isSelected = false
                    }
                    section.isSelected = true
                    selectedNavMenuGroup = section
                    notifyDataSetChanged()

                    //In case any child of this parent was clicked previously, this line clears the
                    //position already set in the NavMenuGroup and resets it to -1.
                    section.position = Constants.NAVMENU_GROUP_PARENT_POSITION
                    mListener?.onDrawerItemSelected(section)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isPositionHeader(position))
            return TYPE_HEADER
        if (isPositionFooter(position))
            return TYPE_FOOTER
        return super.getItemViewType(position)
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == this.itemCount - 1
    }


    /**
     * Interface required for handling clicks on the drawer items
     */
    interface OnDrawerItemSelectedListener {
        fun onDrawerItemSelected(menuGroup: NavMenuGroup?)
    }


    private inner class HeaderViewHolder(v: View) : SectionParentViewHolder(v, mListener, SectionParentViewHolder.HEADER_VIEW) {
        var navHeaderLayout: RelativeLayout? = null

        init {
            navHeaderLayout = v.findViewById<View>(R.id.nav_drawer_header_container) as RelativeLayout?
            navHeaderLayout?.setOnClickListener {
                mListener?.onDrawerItemSelected(null)
            }
        }
    }

    private inner class FooterViewHolder(v: View) : SectionParentViewHolder(v, mListener, SectionParentViewHolder.FOOTER_VIEW) {
        var navFooterLayout: LinearLayout? = null
        var quintTypeLink: TextView? = null
        var disclaimerLink: TextView? = null

        init {
            navFooterLayout = v as LinearLayout
            quintTypeLink = navFooterLayout?.findViewById<View>(R.id.quintype_link_tv) as TextView
            disclaimerLink = navFooterLayout?.findViewById<View>(R.id.disclaimer_tv) as TextView

            quintTypeLink?.setOnClickListener {
                Utilities().loadURL(v.context, Uri.parse(v.context.resources.getString(R.string.quintype_link_text)))
            }
            disclaimerLink?.setOnClickListener {
                Utilities().loadURL(v.context, Uri.parse(v.context.resources.getString(R.string.disclaimer_link_text)))
            }

        }
    }
}