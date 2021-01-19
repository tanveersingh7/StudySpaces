package com.example.studyspaces.data.studyspace.info;

/*
Class that contains information of each study space that is to be displayed on the search page.
 */
public class StudySpace {
    private int mImageResource;
    private String desc1;
    private String desc2;

    public StudySpace(int image, String desc1, String desc2) {
        mImageResource = image;
        this.desc1 = desc1;
        this.desc2 = desc2;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getDesc1() {
        return this.desc1;
    }

    public String getDesc2() {
        return this.desc2;
    }
}
