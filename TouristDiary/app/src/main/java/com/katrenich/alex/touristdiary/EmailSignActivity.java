package com.katrenich.alex.touristdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmailSignActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignIn;
    private Button btnSignUp;
    private TextView tvEmail;
    private TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sign);

        init();
    }

    private void init() {
        btnSignIn = findViewById(R.id.btn_email_auth_sign_in);
        btnSignIn.setOnClickListener(this);
        btnSignUp = findViewById(R.id.btn_email_auth_sign_up);
        btnSignUp.setOnClickListener(this);

        tvEmail = findViewById(R.id.tv_email_auth_login);
        tvPassword = findViewById(R.id.tv_email_auth_login);

    }

    @Override
    public void onClick(View v) {

    }
}
