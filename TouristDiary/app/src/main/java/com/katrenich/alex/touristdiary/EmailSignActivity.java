package com.katrenich.alex.touristdiary;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.ActionBar;

public class EmailSignActivity extends LogActivity implements View.OnClickListener {

    private Button btnSignIn;
    private Button btnSignUp;
    private TextView tvEmail;
    private TextView tvPassword;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sign);

        init();
        Log.d(TAG, "onCreate: init()");
    }

    private void init() {
        btnSignIn = findViewById(R.id.btn_email_auth_sign_in);
        btnSignIn.setOnClickListener(this);
        Log.d(TAG, "init: btnSignIn.setOnClickListener(this)");
        btnSignUp = findViewById(R.id.btn_email_auth_sign_up);
        btnSignUp.setOnClickListener(this);
        Log.d(TAG, "init: btnSignUp.setOnClickListener(this)");
        tvEmail = findViewById(R.id.tv_email_auth_login);
        Log.d(TAG, "init: tvEmail = findViewById(R.id.tv_email_auth_login)");
        tvPassword = findViewById(R.id.tv_email_auth_pasword);
        Log.d(TAG, "init: tvPassword = findViewById(R.id.tv_email_auth_password)");
        actionBar = this.getSupportActionBar();
        Log.d(TAG, String.valueOf("init: actionBar = getActionBar()"));
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            Log.d(TAG, "init:actionBar.setHomeButtonEnabled(true)");
            actionBar.setDisplayHomeAsUpEnabled(true);
            Log.d(TAG, "init: setDisplayHomeAsUpEnabled(true)");
            actionBar.setDisplayShowHomeEnabled(true);
            Log.d(TAG, "init: setDisplayShowHomeEnabled(true)");

        } else {
            Log.d(TAG, "init: actionBar = null");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_email_auth_sign_in :
                Log.d(TAG, "onClick: case R.id.btn_email_auth_sign_in");
                break;
            case R.id.btn_email_auth_sign_up :
                Log.d(TAG, "onClick: ase R.id.btn_email_auth_sign_up ");
                break;
            default:
                    break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                Log.d(TAG, "onOptionsItemSelected: onBackPressed()");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onDestroy() {
        btnSignUp.setOnClickListener(null);
        Log.d(TAG, "onDestroy: btnSignUp.setOnClickListener(null)");
        btnSignIn.setOnClickListener(null);
        Log.d(TAG, "onDestroy: btnSignIn.setOnClickListener(null)");
        super.onDestroy();
    }
}
