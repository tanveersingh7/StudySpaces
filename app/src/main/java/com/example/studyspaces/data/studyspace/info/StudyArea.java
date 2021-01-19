package com.example.studyspaces.data.studyspace.info;

import com.example.studyspaces.reviews.Review;

import java.util.ArrayList;

public class StudyArea {
    // SQLiteDB related
    public static final String TABLENAME = "StudyArea";
    public static final String KEY_NAME = "Name";
    public static final String KEY_LOCATION = "Location";
    public static final String KEY_AREAID = "AreaID";
    public static final String KEY_MAXOCCUPANCY = "MaxOccupancy";
    public static final String KEY_POWEROUTLET = "PowerOutlet";
    public static final String KEY_NOISELEVEL = "NoiseLevel";
    public static final String KEY_NETWORKSPEED = "NetworkSpeed";
    public static final String KEY_LIGHTLEVEL = "LightLevel";
    public static final String KEY_WIFI = "WiFi";
    public static final String KEY_WATERFOUNTAIN = "WaterFountain";
    public static final String KEY_RESTROOM = "Restroom";
    public static final String KEY_FOOD = "Food";
    public static final String KEY_COFFEESHOP = "CoffeeShop";
    public static final String KEY_WHITEBOARD = "WhiteBoard";
    public static final String KEY_BIGSCREEN = "BigScreen";
    public static final String KEY_AVERAGEREVIEW = "AverageReview";

    // Attribution
    public String name;
    public String location;
    public int areaID;

    // Detail
    public int maxOccupancy;
    public int powerOutlet;
    public int noiseLevel;
    public int networkSpeed;
    public int lightLevel;

    public boolean wiFi;
    public boolean waterFountain;
    public boolean restroom;
    public boolean food;
    public boolean coffeeShop;
    public boolean whiteBoard;
    public boolean bigScreen;


    public float averageReview;

    // Gallery and reviews
    public ArrayList<Photo> gallery = new ArrayList<>();
    public ArrayList<Review> reviews = new ArrayList<>();

    public StudyArea(){

    }

    // todo add all detail
    public StudyArea(String name, String location, int areaID,
                     int maxOccupancy, int powerOutlet,int noiseLevel,int networkSpeed,int lightLevel,
                     boolean wiFi,boolean waterFountain,boolean restroom,boolean food,
                     boolean coffeeShop,boolean whiteBoard,boolean bigScreen){
        this.name = name;
        this.location = location;
        this.areaID = areaID;
        this.maxOccupancy = maxOccupancy;
        this.powerOutlet = powerOutlet;
        this.noiseLevel = noiseLevel;
        this.networkSpeed = networkSpeed;
        this.lightLevel = lightLevel;
        this.wiFi = wiFi;
        this.waterFountain = waterFountain;
        this.restroom = restroom;
        this.food = food;
        this.coffeeShop = coffeeShop;
        this.whiteBoard = whiteBoard;
        this.bigScreen = bigScreen;
    }

    public void AddPhoto(Photo photo){
        photo.areaID = this.areaID;
        this.gallery.add(photo);
    }

    public void AddReview(Review review){
        review.areaID = this.areaID;
        this.reviews.add(review);
        this.averageReview = getAverageReview();
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<Photo> getGallery(){
        return gallery;
    }

    public Photo getDisplayPhoto(){
        return gallery.get(0);
    }

    public float getAverageReview(){
        float sum = 0;
        if (this.reviews.size() == 0){
            return 0;
        }
        for (Review review:reviews){
            sum += review.stars;
        }
        averageReview = sum / reviews.size();
        return averageReview;
    }

    public Photo getReviewPhoto(int reviewID){
        for (Photo photo:gallery){
            if (photo.reviewID == reviewID){
                return photo;
            }
        }
        return null;
    }

    public String getLocation(){
        return location;
    }
    public String getName() {return this.name;}

    public int getMaxOccupancy(){
        return maxOccupancy;
    }
    public int getAreaID() { return areaID; }
    public int getPowerOutlet(){ return powerOutlet; }
    public int getNoiseLevel(){ return noiseLevel; }
    public int getNetworkSpeed() { return networkSpeed; }
    public int getLightLevel() { return  lightLevel; }
    public boolean getwifi() { return wiFi;}
    public boolean getwaterFountain() { return waterFountain; }
    public boolean getrestroom() { return restroom;}
    public boolean getfood() { return food;}
    public boolean getcoffeeShop() { return coffeeShop;}
    public boolean getwhiteBoard() { return whiteBoard;}
    public boolean getbigScreen() { return bigScreen;}
}
