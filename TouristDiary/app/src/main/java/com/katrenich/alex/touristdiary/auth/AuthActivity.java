package com.katrenich.alex.touristdiary.auth;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.katrenich.alex.touristdiary.LogActivity;
import com.katrenich.alex.touristdiary.MainActivity;
import com.katrenich.alex.touristdiary.R;

import java.util.Arrays;


public class AuthActivity extends LogActivity implements View.OnClickListener{

    private static final int RC_SIGN_IN = 2;
    private FirebaseAuth mAuth; // змінна для роботи з авторизацією
    private FirebaseAuth.AuthStateListener mAuthListener; // змінна для прослуховування зміни статусу користувача
    private CallbackManager mCallbackManager; //менеджер управління зв'язоком сервісу фейсбук та додатку
    private FacebookCallback<LoginResult> facebookCallback;
    private GoogleApiClient mGoogleApiClient;

    private Button btnGoogleSignIn;
    private Button btnFacebookSignIn;
    private Button btnEmailSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        init(); // ініціалізація елементів
        Log.d(TAG, "onCreate: init()");
    }

    // ініціалізую кнопки та передаю їм посилання на прослуховувач кліку
    private void init() {
        btnGoogleSignIn = findViewById(R.id.btn_google_sign_in);
        btnGoogleSignIn.setText(this.getResources().getText(R.string.app_btn_google_sign_in));
        btnGoogleSignIn.setOnClickListener(this);
        Log.d(TAG, "onCreate: btnGoogleSignIn.setOnClickListener(this)");
        btnFacebookSignIn = findViewById(R.id.btn_facebook_login);
        btnFacebookSignIn.setText(this.getResources().getText(R.string.app_btn_facebook_sign_in));
        btnFacebookSignIn.setOnClickListener(this);
        Log.d(TAG, "onCreate: btnFacebookSignIn.setOnClickListener(this)");
        btnEmailSignIn = findViewById(R.id.btn_email_sign_in);
        btnEmailSignIn.setText(this.getResources().getText(R.string.app_btn_email_sign_in));
        btnEmailSignIn.setOnClickListener(this);
        Log.d(TAG, "onCreate: btnEmailSignIn.setOnClickListener(this)");

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // create instance of CallbackManager

        // [START initialize facebook auth]
        mCallbackManager = CallbackManager.Factory.create();
        Log.d(TAG, "init: Instance CallbackManager create");
        facebookCallback = new FacebookCallback<LoginResult>() {
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
        };
        Log.d(TAG, "init: FacebookCallback - success");
        // [END initialize facebook auth]

        // [START initialize google auth]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        Log.d(TAG, "init: GoogleSignInOptions - success");
        // [END config_signin]

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d(TAG, "onConnectionFailed: " + connectionResult.getErrorMessage());
                        Toast.makeText(AuthActivity.this, "Connection failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // метод для отримання ключа для авторизації з фейсбук
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            update(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        update(currentUser);

    }

    // update program interface while user is
    private void update(FirebaseUser currentUser) {
        if(currentUser != null){
            Log.d(TAG, "update: currentUser != null");
            Log.d(TAG, "update: UserId" + currentUser.getUid());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        Log.d(TAG, "update: currentUser == null");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.auth_activity_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu: getMenuInflater().inflate(R.menu.menu, menu)");
        return menu != null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_AUTH_EMAIL :
                break;
            case RC_SIGN_IN :
                // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if(result.isSuccess()){
                    GoogleSignInAccount account = result.getSignInAccount();
                    // Google sign-in was successful, authenticate with firebase
                    Log.d(TAG, "onActivityResult: google sign-in successful");
                    firebaseAuthWithGoogle(account);
                } else {
                    // Google sign-in failed
                    Log.d(TAG, "onActivityResult: Google sign-in failed" );
                }
                break;

            default:
                break;

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обробляю подію кліку на один з пунктів меню
        switch (item.getItemId()){
            case R.id.menu_action_about :
                AlertDialog alertDialog = new AlertDialog.Builder(AuthActivity.this).create();
                alertDialog.setTitle("About");
                alertDialog.setMessage("Created by Alex Katrenich");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private static final int REQUEST_CODE_AUTH_EMAIL = 825;
    
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_email_sign_in :
                Intent intent = new Intent(this, EmailSignActivity.class);
                startActivityForResult(intent, REQUEST_CODE_AUTH_EMAIL);
                Toast.makeText(this, "Sign-in with Email button was clicked!", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: Sign-in with Email button was clicked!");
                break;
            case R.id.btn_facebook_login:
                LoginManager.getInstance().logInWithReadPermissions(AuthActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, facebookCallback);
                Log.d(TAG, "onClick: Sign-in with Facebook  button was clicked!");
                break;
            case R.id.btn_google_sign_in :
                Toast.makeText(this, "Sign-in with Google button was clicked!", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: Sign-in with Google button was clicked!");
                break;
            default:
                break;
        }


    }

    @Override
    protected void onDestroy() {
        //зануляю посилання на слухача кліків для кнопок
        btnEmailSignIn.setOnClickListener(null);
        btnFacebookSignIn.setOnClickListener(null);
        btnGoogleSignIn.setOnClickListener(null);
        super.onDestroy();
    }
}
