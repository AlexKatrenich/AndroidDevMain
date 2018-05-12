package com.katrenich.alex.touristdiary.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.katrenich.alex.touristdiary.R;

public class FacebookAuthActivity extends BaseActivity {

    private CallbackManager mCallbackManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_auth);
        initialize();

    }

    private void initialize() {
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        Log.d(TAG, "initialize: CallbackManager.Factory.create()");
        loginButton = findViewById(R.id.btn_facebook_login);
        loginButton.setReadPermissions("email", "public_profile");
        Log.d(TAG, "initialize: loginButton.setReadPermissions(\"email\", \"public_profile\")");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: mCallbackManager.onActivityResult(requestCode, resultCode, data)");
    }


}
