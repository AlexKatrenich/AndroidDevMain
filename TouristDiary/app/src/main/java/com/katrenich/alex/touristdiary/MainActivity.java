package com.katrenich.alex.touristdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.katrenich.alex.touristdiary.adapters.TripRecyclerViewAdapter;
import com.katrenich.alex.touristdiary.entity.Trip;
import com.katrenich.alex.touristdiary.entity.TripDataSource;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ініціалізую змінну RecycleView
        final RecyclerView rvListTrip = findViewById(R.id.rv_trip_list);
        TripDataSource tripDataSource = new TripDataSource();

        tripDataSource.load(new TripDataSource.ResultCallback() {
            @Override
            public void onResult(List<Trip> trips) {
                RecyclerView.Adapter adapterListTrip = new TripRecyclerViewAdapter(trips);
                rvListTrip.setAdapter(adapterListTrip);
            }
        });


    }
}
