package com.katrenich.alex.fragments;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        bottomNavigation = findViewById(R.id.bottom_navigation_view);
        bottomNavigation.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.app_navigation_colors));

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fl_container_fragment, new MyFragment())
                    .commit();
        }

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.item_main :
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_container_fragment, new MyFragment())
                                .addToBackStack(null)
                                .commit();
                        Toast.makeText(MainActivity.this, "One", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_map :
                        Toast.makeText(MainActivity.this, "Two", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_text :
                        Toast.makeText(MainActivity.this, "Tree", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

}
