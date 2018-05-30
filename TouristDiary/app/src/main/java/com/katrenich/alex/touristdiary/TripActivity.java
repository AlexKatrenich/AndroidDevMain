package com.katrenich.alex.touristdiary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.katrenich.alex.touristdiary.fragments.TripMainFragment;
import com.katrenich.alex.touristdiary.fragments.TripMapFragment;
import com.katrenich.alex.touristdiary.fragments.TripNoteFragment;
import com.katrenich.alex.touristdiary.fragments.TripPhotosFragment;

public class TripActivity extends LogActivity {
    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState){
        // редагую верхнє меню, додаю кнопку "назад" та видаляю назву проекту
        actionBar = this.getSupportActionBar();
        Log.d(TAG, String.valueOf("init: actionBar = getActionBar()"));
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            Log.d(TAG, "init:actionBar.setHomeButtonEnabled(true)");
            actionBar.setDisplayHomeAsUpEnabled(true);
            Log.d(TAG, "init: setDisplayHomeAsUpEnabled(true)");
            actionBar.setDisplayShowHomeEnabled(true);
            Log.d(TAG, "init: setDisplayShowHomeEnabled(true)");
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle("New Trip");
        } else {
            Log.d(TAG, "init: actionBar = null");
        }

        bottomNavigationView = findViewById(R.id.bnv_trip_activity);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fl_trip_fragments, new TripMainFragment())
                    .commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.bnm_main :
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_trip_fragments, new TripMainFragment())
                                .addToBackStack(null)
                                .commit();
                        Toast.makeText(TripActivity.this, "TripMainFragment", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bnm_map :
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_trip_fragments, new TripMapFragment())
                                .addToBackStack(null)
                                .commit();

                        Toast.makeText(TripActivity.this, "TripMapFragment", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bnm_notes :
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_trip_fragments, new TripNoteFragment())
                                .addToBackStack(null)
                                .commit();

                        Toast.makeText(TripActivity.this, "TripNoteFragment", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bnm_photos :
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_trip_fragments, new TripPhotosFragment())
                                .addToBackStack(null)
                                .commit();

                        Toast.makeText(TripActivity.this, "TripPhotosFragment", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home :
                // потрібно дописати збереження змінених даних та повернення до головної активності
                //TODO
                startActivity(new Intent(this, MainActivity.class));
                finish();
            default :
                return false;
        }
    }
}
