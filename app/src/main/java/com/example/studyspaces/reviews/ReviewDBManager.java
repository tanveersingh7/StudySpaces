package com.example.studyspaces.reviews;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studyspaces.Config;

import java.util.ArrayList;

public class ReviewDBManager {
    public class ReviewDBHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;

        public ReviewDBHelper(Context context){
            super(context, Config.REVIEW_DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //create table
            String CREATE_TABLE_REVIEW = "CREATE TABLE IF NOT EXISTS " + Review.TABLE_NAME +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +Review.KEY_REVIEWID + " INTEGER, "
                    +Review.KEY_REVIEWCONTENT + " TEXT, "
                    +Review.KEY_REVIEWTITLE + " TEXT, "
                    +Review.KEY_STAR + " INTEGER, "
                    +Review.KEY_AREAID + " INTEGER)";
            db.execSQL(CREATE_TABLE_REVIEW);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
        }
    }
    private ReviewDBHelper reviewDBHelper;
    private SQLiteDatabase reviewDB;

    public ReviewDBManager(Context context){
        reviewDBHelper = new ReviewDBHelper(context);
        reviewDB = reviewDBHelper.getWritableDatabase();
    }

    //insert review
    public void add(Review review){
        reviewDB.beginTransaction();
        reviewDB.execSQL("INSERT INTO " + Review.TABLE_NAME +
                " VALUES (null,?,?,?,?,?)", new Object[]{review.reviewID, review.reviewContent, review.reviewTitle, review.stars, review.areaID});
        reviewDB.setTransactionSuccessful();
        reviewDB.endTransaction();
    }

    public Cursor queryTheCursor(int id) {
        Cursor c = reviewDB.rawQuery("SELECT * FROM "
                + Review.TABLE_NAME, null);
        return c;
    }

    // return a list of reviews based on the studyarea id.
    public ArrayList<Review> getReviewListByAreaID(int areaid){
        ArrayList<Review> reviews = new ArrayList<>();
        Cursor c = queryTheCursor(areaid);
        while(c.moveToNext()){
            int nextAreaID = c.getInt(c.getColumnIndex(Review.KEY_AREAID));
            if(nextAreaID == areaid) {
                Review review = new Review();
                review.reviewID = c.getInt(c.getColumnIndex(Review.KEY_REVIEWID));
                review.areaID = c.getInt(c.getColumnIndex(Review.KEY_REVIEWID));
                review.reviewContent = c.getString(c.getColumnIndex(Review.KEY_REVIEWCONTENT));
                review.reviewTitle = c.getString(c.getColumnIndex(Review.KEY_REVIEWTITLE));
                review.stars = c.getInt(c.getColumnIndex(Review.KEY_STAR));

                reviews.add(review);
            }
        }
        c.close();
        return reviews;
    }

    public void closeDB(){
        reviewDB.close();
    }
}
