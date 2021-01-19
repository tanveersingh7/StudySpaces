package com.example.studyspaces.data.studyspace.info;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studyspaces.Config;

import java.util.ArrayList;

public class PhotoDBManager {
    public class PhotoDBHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;

        public PhotoDBHelper(Context context) {
            super(context, Config.PHOTO_DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createSQL = "CREATE TABLE IF NOT EXISTS " + Photo.TABLE_NAME +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Photo.KEY_IMAGE + " BLOB, " +
                    Photo.KEY_AREAID + " INTEGER, " +
                    Photo.KEY_REVIEWID + " INTEGER, " +
                    Photo.KEY_DESCRIPTION + " TEXT)";
            System.out.println(createSQL);
            db.execSQL(createSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    private PhotoDBHelper photoDBHelper;
    private SQLiteDatabase photoDB;

    public PhotoDBManager(Context context){
        photoDBHelper = new PhotoDBHelper(context);
        photoDB = photoDBHelper.getWritableDatabase();
    }

    public void add(Photo photo){
        photoDB.beginTransaction();

        photoDB.execSQL("INSERT INTO " + Photo.TABLE_NAME +
                " VALUES(null,?,?,?,?)", new Object[]{Config.Bitmap2ByteArray(photo.image), photo.areaID, photo.reviewID, photo.description
        });

        photoDB.setTransactionSuccessful();
        photoDB.endTransaction();
    }

    public Cursor queryTheCursor() {
        Cursor c = photoDB.rawQuery("SELECT * FROM " + Photo.TABLE_NAME, null);
        return c;
    }

    // return a list containing all the study areas, here we can control which attribution to return
    // for instance, in the recommandaton and search part, we do not need the photo and reviews.
    public ArrayList<Photo> getPhotosByAreaID(int areaID){
        ArrayList<Photo> photos = new ArrayList<>();
        Cursor c = queryTheCursor();
        while(c.moveToNext()){
            int nextAreaID = c.getInt(c.getColumnIndex(Photo.KEY_AREAID));
            if(nextAreaID == areaID) {
                Photo photo = new Photo();
                photo.image = Config.ByteArray2Bitmap(c.getBlob(c.getColumnIndex(Photo.KEY_IMAGE)));
                photo.description = c.getString(c.getColumnIndex(Photo.KEY_DESCRIPTION));
                photo.areaID = c.getInt(c.getColumnIndex(Photo.KEY_AREAID));
                photo.reviewID = c.getInt(c.getColumnIndex(Photo.KEY_REVIEWID));
                photos.add(photo);
            }
        }
        c.close();
        return photos;
    }


    public ArrayList<Photo> getPhotosByReviewID(int reviewID){
        ArrayList<Photo> photos = new ArrayList<>();
        Cursor c = queryTheCursor();
        while(c.moveToNext()){
            int nextReviewID = c.getInt(c.getColumnIndex(Photo.KEY_REVIEWID));
            if(nextReviewID == reviewID) {
                Photo photo = new Photo();
                photo.image = Config.ByteArray2Bitmap(c.getBlob(c.getColumnIndex(Photo.KEY_IMAGE)));
                photo.description = c.getString(c.getColumnIndex(Photo.KEY_DESCRIPTION));
                photo.areaID = c.getInt(c.getColumnIndex(Photo.KEY_AREAID));
                photo.reviewID = c.getInt(c.getColumnIndex(Photo.KEY_REVIEWID));
                photos.add(photo);
            }
        }
        c.close();
        return photos;
    }

    public void closeDB(){
        photoDB.close();
    }
}
