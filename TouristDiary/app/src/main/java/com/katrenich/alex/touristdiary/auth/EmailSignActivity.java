package com.katrenich.alex.touristdiary.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.ActionBar;

import com.google.firebase.auth.FirebaseAuth;
import com.katrenich.alex.touristdiary.R;

import java.util.regex.Pattern;

public class EmailSignActivity extends BaseActivity implements View.OnClickListener {

    private Button btnSignIn;
    private Button btnSignUp;
    private TextView tvEmail;
    private TextView tvPassword;
    private ActionBar actionBar;

    //константа для валідації емейла
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sign);

        init();
        Log.d(TAG, "onCreate: init()");
    }

    @Override
    protected void onStart() {
        super.onStart();

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

        // ініціалізую об'єкт авторизації Firebase
        mAuth = FirebaseAuth.getInstance();

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
            actionBar.setDisplayShowTitleEnabled(false);
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
                createAccount(tvEmail.getText().toString(), tvPassword.getText().toString());
                break;
            default:
                break;

        }
    }

    // метод для створення акаунту користувача
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount: email");

        // перевіряємо коректність заповнення полів форми
        if(!validateForm()){
            return;
        }

        showProgressDialog();
        Log.d(TAG, "createAccount: showProgressDialog");


    }


    private boolean validateForm() {
        boolean valid = true;

        String email = tvEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            tvEmail.setError("Required.");
            valid = false;
        } else {
            tvEmail.setError(null);

            // валідація корректного вводу емейла за допомогою регулярного виразу
            valid = Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
        }



        String password = tvPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            tvPassword.setError("Required.");
            valid = false;
        } else {
            tvPassword.setError(null);
            
            //валідація корректного вводу пароля за допомогою регулярного виразу
            valid = Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
            Log.d(TAG, "validateForm: password - "+ valid);
        }

        return valid;
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
