package quintype.com.templatecollectionwithrx.models.storypresenter;

import android.view.View;

import quintype.com.templatecollectionwithrx.models.story.StoryElement;

public interface StoryElementBinder {
    void bind(StoryElement var1);

    View getView();

    boolean recreate();
}
