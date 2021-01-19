package com.example.studyspaces.data.studyspace.info;

import android.graphics.Bitmap;
import android.os.Parcelable;

import java.util.List;

public class Photo{
    public static final String TABLE_NAME = "Photos";
    public static final String KEY_IMAGE = "Image";
    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_AREAID = "AreaID";
    public static final String KEY_REVIEWID = "ReviewID";

    public Bitmap image;
    public String description;

    public int areaID;
    public int reviewID;

    public Photo(){

    }

    public Photo(Bitmap image, String description){
        this.image = getResizedBitmap(image, 256);
        this.description = description;
    }

    public void setAreaID(StudyArea studyArea){
        this.areaID = studyArea.areaID;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
