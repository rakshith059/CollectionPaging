package com.example.androidcore.models.storypresenter;

import android.view.View;

import com.example.androidcore.models.story.StoryElement;

public interface StoryElementBinder {
    void bind(StoryElement var1);

    View getView();

    boolean recreate();
}
