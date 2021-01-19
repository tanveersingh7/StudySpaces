package com.example.studyspaces.space;

public class StudySpaceItem  {

    private int mImageResource;
    private String mTitle;
    private String mShortDesc;
    private String mRating;

    public StudySpaceItem(int imageResource, String title, String shortDesc, String rating) {
        mImageResource = imageResource;
        mTitle = title;
        mShortDesc = shortDesc;
        mRating = rating;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmShortDesc() {
        return mShortDesc;
    }

    public String getmRating() {
        return mRating;
    }
}
