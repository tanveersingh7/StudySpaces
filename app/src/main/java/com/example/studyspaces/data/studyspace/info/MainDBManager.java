package com.example.studyspaces.data.studyspace.info;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studyspaces.Config;
import com.example.studyspaces.reviews.Review;
import com.example.studyspaces.reviews.ReviewDBManager;

import java.util.ArrayList;

public class MainDBManager {
    public class MainDBHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;

        public MainDBHelper(Context context) {
            super(context, Config.MAIN_DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // todo add all detail to database
            String createSQL = "CREATE TABLE IF NOT EXISTS " + StudyArea.TABLENAME +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    StudyArea.KEY_NAME + " TEXT, " +
                    StudyArea.KEY_LOCATION + " TEXT," +
                    StudyArea.KEY_AREAID + " INTEGER," +
                    StudyArea.KEY_MAXOCCUPANCY + " INTEGER," +
                    StudyArea.KEY_POWEROUTLET + " INTEGER," +
                    StudyArea.KEY_NOISELEVEL + " INTEGER," +
                    StudyArea.KEY_NETWORKSPEED + " INTEGER," +
                    StudyArea.KEY_LIGHTLEVEL + " INTEGER," +
                    StudyArea.KEY_WIFI + " INTEGER," +
                    StudyArea.KEY_WATERFOUNTAIN + " INTEGER," +
                    StudyArea.KEY_RESTROOM + " INTEGER," +
                    StudyArea.KEY_FOOD + " INTEGER," +
                    StudyArea.KEY_COFFEESHOP + " INTEGER," +
                    StudyArea.KEY_WHITEBOARD + " INTEGER," +
                    StudyArea.KEY_BIGSCREEN + " INTEGER)";
            System.out.println(createSQL);
            db.execSQL(createSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
        }
    }

    private MainDBHelper mainDBHelper;
    private SQLiteDatabase mainDB;
    public PhotoDBManager photoDBManager;
    public ReviewDBManager reviewDBManager;

    public MainDBManager(Context context, PhotoDBManager photoDBManager, ReviewDBManager reviewDBManager){
        this.mainDBHelper = new MainDBHelper(context);
        this.mainDB = mainDBHelper.getWritableDatabase();
        this.photoDBManager = photoDBManager;
        this.reviewDBManager = reviewDBManager;
    }

    public void add(StudyArea studyArea){
        mainDB.beginTransaction();

        mainDB.execSQL("INSERT INTO " + StudyArea.TABLENAME +
                        " VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new Object[]{studyArea.name, studyArea.location,studyArea.areaID,studyArea.maxOccupancy,studyArea.powerOutlet,
                        studyArea.noiseLevel,studyArea.networkSpeed,studyArea.lightLevel,studyArea.wiFi,studyArea.waterFountain,
                        studyArea.restroom,studyArea.food,studyArea.coffeeShop,studyArea.whiteBoard,studyArea.bigScreen
                });
        mainDB.setTransactionSuccessful();
        mainDB.endTransaction();
    }

    public Cursor queryTheCursor() {
        Cursor c = mainDB.rawQuery("SELECT * FROM " + StudyArea.TABLENAME, null);
        return c;
    }

    // return a list containing all the study areas, here we can control which attribution to return
    // for instance, in the recommandaton and search part, we do not need the photo and reviews.
    public ArrayList<StudyArea> Query(){
        ArrayList<StudyArea> studyAreas = new ArrayList<>();
        Cursor c = queryTheCursor();
        while(c.moveToNext()){
            StudyArea studyArea = new StudyArea();
            studyArea.name = c.getString(c.getColumnIndex(StudyArea.KEY_NAME));
            studyArea.location = c.getString(c.getColumnIndex(StudyArea.KEY_LOCATION));
            studyArea.areaID = c.getInt(c.getColumnIndex(StudyArea.KEY_AREAID));
            studyArea.maxOccupancy = c.getInt(c.getColumnIndex(StudyArea.KEY_MAXOCCUPANCY));
            studyArea.powerOutlet = c.getInt(c.getColumnIndex(StudyArea.KEY_POWEROUTLET));
            studyArea.noiseLevel = c.getInt(c.getColumnIndex(StudyArea.KEY_NOISELEVEL));
            studyArea.networkSpeed = c.getInt(c.getColumnIndex(StudyArea.KEY_NETWORKSPEED));
            studyArea.lightLevel = c.getInt(c.getColumnIndex(StudyArea.KEY_LIGHTLEVEL));
            studyArea.wiFi = c.getInt(c.getColumnIndex(StudyArea.KEY_WIFI)) == 1;
            studyArea.waterFountain = c.getInt(c.getColumnIndex(StudyArea.KEY_WATERFOUNTAIN)) == 1;
            studyArea.food = c.getInt(c.getColumnIndex(StudyArea.KEY_FOOD)) == 1;
            studyArea.coffeeShop = c.getInt(c.getColumnIndex(StudyArea.KEY_COFFEESHOP)) == 1;
            studyArea.bigScreen = c.getInt(c.getColumnIndex(StudyArea.KEY_BIGSCREEN)) == 1;
            studyArea.whiteBoard = c.getInt(c.getColumnIndex(StudyArea.KEY_WHITEBOARD)) == 1;
            studyArea.restroom = c.getInt(c.getColumnIndex(StudyArea.KEY_RESTROOM)) == 1;

            studyAreas.add(studyArea);

            // todo add photo and review
            ArrayList<Photo> photos = photoDBManager.getPhotosByAreaID(studyArea.areaID);
            studyArea.gallery = photos;

            ArrayList<Review> reviews = reviewDBManager.getReviewListByAreaID(studyArea.areaID);

//            for (Review review:reviews){
//                for (Photo photo:photos){
////                    if (photo.reviewID == review.reviewID){
////                        review.photo = photo;
////                        break;
////                    }
//                }
//            }
            studyArea.reviews = reviews;
        }
        c.close();
        return studyAreas;
    }

    public void closeDB(){
        mainDB.close();
    }
}
