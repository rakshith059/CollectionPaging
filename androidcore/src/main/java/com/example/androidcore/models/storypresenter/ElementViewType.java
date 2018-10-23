package com.example.androidcore.models.storypresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ElementViewType {
    int TEXT = 1;
    int IMAGE = 2;
    int YOUTUBE = 3;
    int SOUND_CLOUD = 4;
    int TITLE = 5;
    int SUMMARY = 6;
    int QUOTE = 7;
    int AUDIO = 8;
    int GIF = 9;
    int BLURB = 16;
    int BIG_FACT = 17;
    int SLIDESHOW = 18;
    int GALLERY = 19;
    int BLOCK_QUOTE = 20;
    int MEDIA = 21;
    int VIDEO = 22;
    int JS_EMBED = 23;
    int TWEET = 24;
    int QUESTION_ANSWER = 25;
    int BIT_GRAVITY_VIDEO = 32;
    int BRIGHTCOVE_VIDEO = 33;
    int POLLTYPE = 34;
    int ALSO_READ = 35;
    int QUESTION = 36;
    int ANSWER = 37;
    int TIME_STAMP = 38;
    int SORT_CARDS = 39;
    int NESTED_STORY = 40;
    int TABLE = 10;
    int UNKNOWN = 48;

    int STORY_HERO_AUTHOR = 51;
    int STORY_TAG = 52;

    public static class Process {
        public Process() {
        }

        public static int getType(int type) {
            return type;
        }
    }
}