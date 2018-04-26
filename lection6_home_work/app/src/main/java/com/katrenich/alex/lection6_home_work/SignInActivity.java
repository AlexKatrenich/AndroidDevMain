package com.katrenich.alex.lection6_home_work;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.katrenich.alex.lection6_home_work.StartActivity.CONSTANT_EXTRA_EMAIL;

public class SignInActivity extends LogActivity implements View.OnClickListener{
    private long backPressTime = 0L; // використовується в onBackPressed()
    private static String email;
    private static String pass;


    private ImageButton buttonBack;
    private EditText emailEditText;
    private EditText paswordEditText;
    private CheckBox checkBoxRememberLogin;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //ініціалізую кнопку btn_loginScreen_back для переходу до попереднього екрану
        buttonBack = findViewById(R.id.btn_loginScreen_back);
        buttonBack.setOnClickListener(this);
        Log.d(TAG, "onCreate: findViewById(R.id.btn_loginScreen_back)");

        emailEditText = findViewById(R.id.et_email);
        Log.d(TAG, "onCreate: findViewById(R.id.et_email)");
        paswordEditText = findViewById(R.id.et_password);
        Log.d(TAG, "onCreate: findViewById(R.id.et_password)");

        checkBoxRememberLogin = findViewById(R.id.chb_RememberLogin);
        Log.d(TAG, "onCreate: findViewById(R.id.chb_RememberLogin)");

        buttonLogin = findViewById(R.id.btn_login);
        Log.d(TAG, "onCreate: findViewById(R.id.btn_login)");
        buttonLogin.setOnClickListener(this);

    }

    /* Метод описує безпечне повернення до попередньої активності*/
    @Override
    public void onBackPressed() {
        long time = System.currentTimeMillis();
        if (time - backPressTime > 2000) {    // 2 secs
            Toast.makeText(this, "Press back again to return",
                    Toast.LENGTH_SHORT).show();
            backPressTime = time;
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_loginScreen_back:
                onBackPressed();
                break;
            case R.id.btn_login:
                Intent intentToStartActivity = new Intent();
                if (emailEditText.getText() != null && paswordEditText.getText() != null){
                    intentToStartActivity.putExtra(CONSTANT_EXTRA_EMAIL, emailEditText.getText().toString());
                    Log.d(TAG, "onClick: intentToStartActivity.putExtra(CONSTANT_EXTRA_EMAIL, emailEditText.getText().toString())");

                    Toast.makeText(this,
                            String.format("Email: %s, password: %s, checkbox: ",
                                    emailEditText.getText().toString(),
                                    paswordEditText.getText().toString()) +
                            checkBoxRememberLogin.isChecked(),
                            Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK, intentToStartActivity);
                    Log.d(TAG, "onClick: setResult(RESULT_OK, intentToStartActivity)");
                    finish();

                } else {
                    Toast.makeText(this, "Please fill email and password", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: email or password is NULL");
                }

                break;
            default:
                break;
        }
    }
}
