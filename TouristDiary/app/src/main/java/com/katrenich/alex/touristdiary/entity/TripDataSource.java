package com.katrenich.alex.touristdiary.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripDataSource {

    public interface ResultCallback {
        void onResult(List<Trip> trips);
    }

     public void load(ResultCallback resultCallback){
        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip("Travel to Crimea", new Date(System.currentTimeMillis())));
        trips.add(new Trip("Travel to France", new Date(System.currentTimeMillis())));
        trips.add(new Trip("Travel to USA", new Date(System.currentTimeMillis())));
        trips.add(new Trip("Travel to Lviv", new Date(System.currentTimeMillis())));
        trips.add(new Trip("Travel to Grace", new Date(System.currentTimeMillis())));

        resultCallback.onResult(trips);
    }
}
