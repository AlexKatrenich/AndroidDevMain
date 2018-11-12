package com.katrenich.alex.customdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

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

        findViewById(R.id.btn_pikolo_show).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: BTN_PIKOLO");
                AlertDialog.Builder adBuilder = new AlertDialog.Builder(v.getContext());
                adBuilder.setView(new HSLColorPicker(v.getContext()));
                adBuilder.create();
                adBuilder.show();
            }
        });

    }


}
