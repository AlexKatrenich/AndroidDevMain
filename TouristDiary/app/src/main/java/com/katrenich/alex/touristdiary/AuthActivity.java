package com.katrenich.alex.touristdiary;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends LogActivity implements View.OnClickListener{

    private FirebaseAuth mAuth; // змінна для роботи з авторизацією
    private FirebaseAuth.AuthStateListener mAuthListener; // змінна для прослуховування зміни статусу користувача

    private Button btnGoogleSignIn;
    private Button btnFacebookSignIn;
    private Button btnEmailSignIn;
    private CheckBox cbSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser user;
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: signed_out ");
                }
            }
        };

        getWindow().getDecorView().setBackground(ContextCompat
                .getDrawable(this, R.drawable.ic_app_authority_background));

        // ініціалізую кнопки та передаю їм прослуховувач кліку
        btnGoogleSignIn = findViewById(R.id.btn_google_sign_in);
        btnGoogleSignIn.setOnClickListener(this);
        Log.d(TAG, "onCreate: btnGoogleSignIn.setOnClickListener(this)");
        btnFacebookSignIn = findViewById(R.id.btn_facebook_sign_in);
        btnFacebookSignIn.setOnClickListener(this);
        Log.d(TAG, "onCreate: btnFacebookSignIn.setOnClickListener(this)");
        btnEmailSignIn = findViewById(R.id.btn_email_sign_in);
        btnEmailSignIn.setOnClickListener(this);
        Log.d(TAG, "onCreate: btnEmailSignIn.setOnClickListener(this)");
        cbSignUp = findViewById(R.id.cb_sign_up);
        cbSignUp.setOnClickListener(this);
        Log.d(TAG, "onCreate: cbSignUp.setOnClickListener(this)");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_email_sign_in :
                Toast.makeText(this, "Sign-in with Email button was clicked!", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: Sign-in with Email button was clicked!");
                break;
            case R.id.btn_facebook_sign_in :
                Toast.makeText(this, "Sign-in with Facebook  button was clicked!", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: Sign-in with Facebook  button was clicked!");
                break;
            case R.id.btn_google_sign_in :
                Toast.makeText(this, "Sign-in with Google button was clicked!", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: Sign-in with Google button was clicked!");
                break;
            case R.id.cb_sign_up :
                /* кнопки підключення нового користувача мають міняти свій текст в залежності від
                 * того чекбокс true або false, потрібно прописати в ресурсах тексти для кнопок та опрацювати
                 * зміну тексту в залежності від кліку на чекбокс
                 * */
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
        cbSignUp.setOnClickListener(null);
        super.onDestroy();
    }
}
