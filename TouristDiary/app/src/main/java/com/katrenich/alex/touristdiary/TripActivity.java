package com.katrenich.alex.touristdiary;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class TripActivity extends LogActivity {

    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        init();
    }

    private void init(){
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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home :
                // потрібно дописати збереження змінених даних та повернення до головної активності
                //TODO
                onBackPressed();

            default :
                return false;
        }
    }
}
