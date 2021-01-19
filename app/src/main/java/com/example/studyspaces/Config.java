package com.example.studyspaces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.studyspaces.data.studyspace.info.Photo;
import com.example.studyspaces.data.studyspace.info.StudyArea;
import com.example.studyspaces.reviews.Review;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Config {
    public static String GetFullPath(String filename) {
        return Environment.getExternalStorageDirectory() + "/CSCI5115/" + filename;
    }

    public static boolean DoesFileExist(String filepath){
        File file = new File(filepath);
        return file.exists();
    }

    public static final String MAIN_DATABASE_NAME = "studyareas_1.db";
    public static final String PHOTO_DATABASE_NAME = "photos_1.db";
    public static final String REVIEW_DATABASE_NAME = "review.db";

    public static byte[] Bitmap2ByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public static Bitmap ByteArray2Bitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static StudyArea selectedStudyArea;
    public static Photo fullDisplayImg;
    public static Review fullDisplayReview;
    public static boolean AddPhotoFromReview = false;
}
