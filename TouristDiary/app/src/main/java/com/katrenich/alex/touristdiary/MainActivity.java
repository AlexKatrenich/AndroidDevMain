package com.katrenich.alex.touristdiary;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.katrenich.alex.touristdiary.adapters.TripRecyclerItemClickListener;
import com.katrenich.alex.touristdiary.adapters.TripRecyclerViewAdapter;
import com.katrenich.alex.touristdiary.entity.Trip;
import com.katrenich.alex.touristdiary.entity.TripDataSource;

import java.util.List;

public class MainActivity extends LogActivity {

    private TripRecyclerItemClickListener tripRecyclerItemClickListener;
    private RecyclerView rvListTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ініціалізую змінну RecycleView
        rvListTrip = findViewById(R.id.rv_trip_list);
        TripDataSource tripDataSource = new TripDataSource();

        tripDataSource.load(new TripDataSource.ResultCallback() {
            @Override
            public void onResult(List<Trip> trips) {
                RecyclerView.Adapter adapterListTrip = new TripRecyclerViewAdapter(trips);
                rvListTrip.setAdapter(adapterListTrip);
            }
        });



        tripRecyclerItemClickListener = new TripRecyclerItemClickListener(this,
                rvListTrip,
                new TripRecyclerItemClickListener.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "ClickOnItem.... UuuuuHuuu", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "LongClickOnItem..... YeeeAaaaap", Toast.LENGTH_SHORT).show();
                    }
                });

        rvListTrip.addOnItemTouchListener(tripRecyclerItemClickListener);


    }

    @Override
    protected void onDestroy() {
        rvListTrip.removeOnItemTouchListener(tripRecyclerItemClickListener);
        super.onDestroy();
    }
}
