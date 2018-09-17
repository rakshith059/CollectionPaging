package quintype.com.templatecollectionwithrx.ui.main.viewholders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import quintype.com.templatecollectionwithrx.R;
import quintype.com.templatecollectionwithrx.models.NavMenu;

public class SectionChildViewHolder extends ChildViewHolder {

    public TextView mSubsectionTextView;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public SectionChildViewHolder(View itemView) {
        super(itemView);
        mSubsectionTextView = (TextView) itemView.findViewById(R.id.child_section_name);
    }

    public void bind(NavMenu subSection) {
        mSubsectionTextView.setText(subSection.title());
    }
}
