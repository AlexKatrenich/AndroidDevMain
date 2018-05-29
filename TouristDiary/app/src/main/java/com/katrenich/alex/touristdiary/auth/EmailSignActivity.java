package com.katrenich.alex.touristdiary.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.katrenich.alex.touristdiary.MainActivity;
import com.katrenich.alex.touristdiary.R;

import java.io.Serializable;
import java.util.regex.Pattern;


public class EmailSignActivity extends BaseActivity implements View.OnClickListener {

    public static final String EMAIL_PASSWORD_USER = "user";
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
                signIn(tvEmail.getText().toString(), tvPassword.getText().toString());
                break;
            case R.id.btn_email_auth_sign_up :
                Log.d(TAG, "onClick: ase R.id.btn_email_auth_sign_up ");
                createAccount(tvEmail.getText().toString(), tvPassword.getText().toString());
                break;
            default:
                break;

        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn: email");

        // перевіряємо коректність заповнення полів форми
        if(!validateForm()){
            return;
        }

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(EmailSignActivity.this, AuthActivity.class);
                            Gson gson = new Gson();
                            String myJson = gson.toJson(user);
                            intent.putExtra(EMAIL_PASSWORD_USER, myJson);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(EmailSignActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]

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

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // викликаю головну Activity
                            startActivity(new Intent(EmailSignActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(EmailSignActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
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
            Log.d(TAG, "validateForm: email - "+ valid);
        }

        if(valid == false){
            SpannableString sString = new SpannableString(email);
            sString.setSpan(new UnderlineSpan(), 0, sString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvEmail.setText(sString);
            return valid;
        }

        String password = tvPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            tvPassword.setError("Required.");
            valid = false;
        } else {
            tvPassword.setError(null);

            //валідація корректного вводу пароля за допомогою регулярного виразу
            valid = Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
            Log.d(TAG, "validateForm: password - " + valid);
        }

        if(valid == false){
            SpannableString sString = new SpannableString(password);
            sString.setSpan(new UnderlineSpan(), 0, sString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvPassword.setText(sString);
            return valid;
        }

        return valid;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                setResult(RESULT_CANCELED);
                Log.d(TAG, "onOptionsItemSelected: setResult(RESULT_CANCELED)");
                finish();
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
