package com.katrenich.alex.customdialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "MyTAG";
    EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog, container, false);
        getDialog().setTitle("Title!!!!!!!!!");
        editText = view.findViewById(R.id.et_input_text);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        Button btnOK = view.findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.d(TAG, "onDismiss: dismiss");
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.d(TAG, "onCancel: cancel");
        super.onCancel(dialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok :
                String s = editText.getText().toString();
                Toast.makeText(v.getContext(), s != null ? s : " ", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.btn_cancel :
                dismiss();
                break;
            default:
                break;
        }
    }
}
