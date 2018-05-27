package com.katrenich.alex.touristdiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.katrenich.alex.touristdiary.adapters.TripRecyclerItemClickListener;
import com.katrenich.alex.touristdiary.adapters.TripRecyclerViewAdapter;
import com.katrenich.alex.touristdiary.auth.AuthActivity;
import com.katrenich.alex.touristdiary.entity.Trip;
import com.katrenich.alex.touristdiary.entity.TripDataSource;

import java.util.List;

public class MainActivity extends LogActivity implements View.OnClickListener{

    private TripRecyclerItemClickListener tripRecyclerItemClickListener;
    private RecyclerView rvListTrip;
    private FloatingActionButton btnCreateNewTrip;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }

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
                        // в методі опрацьовується клік по елементу RecycleView
                        Toast.makeText(MainActivity.this, "ClickOnItem.... UuuuuHuuu", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // в методі опрацьовується довгий клік по елементу RecycleView
                        Toast.makeText(MainActivity.this, "LongClickOnItem..... YeeeAaaaap", Toast.LENGTH_SHORT).show();
                    }
                });

        rvListTrip.addOnItemTouchListener(tripRecyclerItemClickListener);
        Log.d(TAG, "init: rvListTrip.addOnItemTouchListener(tripRecyclerItemClickListener)");

        btnCreateNewTrip = findViewById(R.id.btn_new_trip);
        btnCreateNewTrip.setOnClickListener(this);
        Log.d(TAG, "init: btnCreateNewTrip.setOnClickListener(this)");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu: getMenuInflater().inflate(R.menu.menu, menu)");
        return menu != null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_action_log_out :
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, AuthActivity.class));
                finish();
                return true;
            case R.id.menu_action_about :
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("About");
                alertDialog.setMessage("Created by Alex Katrenich");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Log.d(TAG, "onClick: dialog.dismiss()");
                            }
                        });
                alertDialog.show();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        rvListTrip.removeOnItemTouchListener(tripRecyclerItemClickListener);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_new_trip :
                //TODO
                Toast.makeText(this, "Create new Trip", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
