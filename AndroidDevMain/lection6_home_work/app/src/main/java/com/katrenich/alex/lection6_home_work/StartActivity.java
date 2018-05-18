package com.katrenich.alex.lection6_home_work;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends LogActivity implements View.OnClickListener{
    public static final String CONSTANT_EXTRA_EMAIL = "email";
    private static final int REQUEST_CODE_EMAIL = 307;
    private String helloText;
    private boolean signedIn = false;

    private Button buttonSignIn;
    private TextView helloTextView;
    private Button buttonToMainScreen;
    private Button buttonSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // ініціалізую кнопку btn_signIn
        buttonSignIn = findViewById(R.id.btn_sign_in);
        buttonSignIn.setOnClickListener(this);
        Log.d(TAG, "onCreate: buttonSignIn = findViewById(R.id.btn_sign_in)");

        // ініціалізую текстове поле helloTextView
        helloTextView = findViewById(R.id.tv_hello);
        Log.d(TAG, "onCreate: helloTextView = findViewById(R.id.tv_hello)");
        helloText = helloTextView.getText().toString();


        // ініціалізую кнопку btn_toMainScreen
        buttonToMainScreen = findViewById(R.id.btn_toMainScreen);
        buttonToMainScreen.setOnClickListener(this);

        // ініціалізую кнопку
        buttonSignOut = findViewById(R.id.btn_signOut);
        buttonSignOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in:
                Intent intent = new Intent(this, SignInActivity.class);
                startActivityForResult(intent, REQUEST_CODE_EMAIL);
                Log.d(TAG, "onClick: startActivityForResult(intent, DEFAULT_KEYS_DIALER)");
                break;
            case R.id.btn_toMainScreen:

//                Intent intentToMainScreen = new Intent(this, MainScreenActivity.class);
//                startActivity(intentToMainScreen);
//                Log.d(TAG, "onClick: startActivity(intentToMainScreen)");
                break;
            case R.id.btn_signOut:
                helloText = "Please Sign in for Application";
                buttonToMainScreen.setVisibility(View.INVISIBLE);
                Log.d(TAG, "onClick: buttonToMainScreen.setVisibility(View.INVISIBLE)");
            default:
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: resultCode: " + resultCode);
        if(requestCode == REQUEST_CODE_EMAIL){

            //записую дані до змінної helloText та заповнюю текстове поле TextViewHello
            if(data != null){
                helloText = new StringBuilder(data.getStringExtra(CONSTANT_EXTRA_EMAIL))
                        .append(", on LOGIN").toString();
                Log.d(TAG, "onActivityResult: helloText = " + helloText);

                helloTextView.setText(helloText);
                Log.d(TAG, "onActivityResult: helloTextView.setText(helloText)");

                // ставлю відмітку про те, що користувач під'єднався до системи(ввів логін та пароль)
                signedIn = true;

            } else {
                Log.d(TAG, "onActivityResult: Intent data == null");
            }


            if(signedIn == true){
                buttonToMainScreen.setVisibility(Button.VISIBLE);
                Log.d(TAG, "onActivityResult: buttonToMainScreen.setVisibility(Button.VISIBLE)");
            }

            Log.d(TAG, "onActivityResult: signedIn = true");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        buttonSignIn.setOnClickListener(null);
        buttonToMainScreen.setOnClickListener(null);
        buttonSignOut.setOnClickListener(null);
    }
}
