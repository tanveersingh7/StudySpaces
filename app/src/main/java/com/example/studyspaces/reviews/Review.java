package com.example.studyspaces.reviews;

public class Review {

    public static final String TABLE_NAME = "Review";
    public static final String KEY_REVIEWID = "ReviewID";
    public static final String KEY_REVIEWCONTENT = "ReviewContent";
    public static final String KEY_REVIEWTITLE = "ReviewTitle";
    public static final String KEY_STAR = "Star";
    public static final String KEY_AREAID = "AreaID";

    public int stars;
    public String reviewContent;
    public String reviewTitle;
    public int areaID;
    public int reviewID;

    public Review(int stars, String reviewContent, String reviewTitle){
        this.stars = stars;
        this.reviewContent = reviewContent;
        this.reviewTitle = reviewTitle;
    }

    public Review(){

    }

    public int getStars(){
        return this.stars;
    }

    public String getStarsAsString(){
        return String.valueOf(this.stars);
    }

    public String getReviewContent(){
        return this.reviewContent;
    }

    public String getReviewTitle() {
        return this.reviewTitle;
    }

}
