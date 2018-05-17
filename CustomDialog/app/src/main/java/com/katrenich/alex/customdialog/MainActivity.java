package com.katrenich.alex.customdialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTAG";
    DialogFragment dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new CustomDialogFragment();

        findViewById(R.id.main_text_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getFragmentManager(), "dialog");
                Log.d(TAG, "onClick: customDialogFragment");
            }
        });
    }


}
