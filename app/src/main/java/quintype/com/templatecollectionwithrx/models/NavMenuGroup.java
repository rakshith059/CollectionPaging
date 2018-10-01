package quintype.com.templatecollectionwithrx.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

import quintype.com.templatecollectionwithrx.R;

public class NavMenuGroup implements ParentListItem, Parcelable {

    public static final Creator<NavMenuGroup> CREATOR = new Creator<NavMenuGroup>() {
        @Override
        public NavMenuGroup createFromParcel(Parcel source) {
            return new NavMenuGroup(source);
        }

        @Override
        public NavMenuGroup[] newArray(int size) {
            return new NavMenuGroup[size];
        }
    };
    //to set the right tab in the viewPager
    int position;
    boolean selected;
    //the parent menu item
    private NavMenu menuItem;
    //the child menu items
    private List<NavMenu> mSubsections = new ArrayList<>();
    //to differentiate dummy elements
    private String dummyValue = null;

    public NavMenuGroup() {
    }

    protected NavMenuGroup(Parcel in) {
        this.position = in.readInt();
        this.menuItem = in.readParcelable(NavMenu.class.getClassLoader());
        this.selected = in.readByte() != 0;
        this.mSubsections = in.createTypedArrayList(NavMenu.CREATOR);
        this.dummyValue = in.readString();
    }

    public String getDummyValue() {
        return dummyValue;
    }

    public void setDummyValue(String dummyValue) {
        this.dummyValue = dummyValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addSubsection(NavMenu subsection) {
        mSubsections.add(subsection);
    }

    public NavMenu getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(NavMenu menuItem) {
        this.menuItem = menuItem;
    }

    public String getName(Context context) {
        if (menuItem.id().equals(NavMenu.HOME.id())) {
            return context.getResources().getString(R.string.home_title);
        }else{
            return menuItem.title();
        }
    }

    @Override
    public List<NavMenu> getChildItemList() {
        return mSubsections;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.position);
        dest.writeParcelable(this.menuItem, flags);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.mSubsections);
        dest.writeString(this.dummyValue);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
