package com.katrenich.alex.fragments;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        bottomNavigation = findViewById(R.id.bottom_navigation_view);
        bottomNavigation.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.app_navigation_colors));
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.item_main :
                        return true;
                    case R.id.item_map :
                        return true;
                    case R.id.item_text :
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

}
