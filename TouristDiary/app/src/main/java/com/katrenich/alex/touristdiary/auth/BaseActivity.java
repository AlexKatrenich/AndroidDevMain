package com.katrenich.alex.touristdiary.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.katrenich.alex.touristdiary.LogActivity;
import com.katrenich.alex.touristdiary.R;

public class BaseActivity extends LogActivity {

    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
            Log.d(TAG, "showProgressDialog: mProgressDialog.setMessage(getString(R.string.loading))");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            Log.d(TAG, "hideProgressDialog: mProgressDialog.dismiss()");
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        Log.d(TAG, "hideKeyboard: InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)");
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            Log.d(TAG, "hideKeyboard: imm.hideSoftInputFromWindow(view.getWindowToken(), 0)");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
        Log.d(TAG, "onStop: hideProgressDialog()");
    }

}
