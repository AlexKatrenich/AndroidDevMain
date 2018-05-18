package com.katrenich.alex.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final String TAG = "MyLog_";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start :
                startService(new Intent(this, MyService.class));
                Log.d(TAG, "onClick: startService");
                break;
            case R.id.btn_stop :
                stopService(new Intent(this, MyService.class));
                Log.d(TAG, "onClick: stopService");
                break;
            default :
                break;
        }
    }
}
