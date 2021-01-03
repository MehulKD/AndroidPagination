package com.shankasur.android.pagination.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.shankasur.android.pagination.R;


public abstract class BaseActivity extends AppCompatActivity {

    private ActivityCompat.OnRequestPermissionsResultCallback permissionsResultCallback;


    protected abstract View getProgressView();

    public void showProgressBar() {
        getProgressView().setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void dismissProgressBar() {
        //  if (getProgressView().isShown()) {
        getProgressView().setVisibility(View.GONE);
        //   }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


    public void setPermissionsResultCallback(ActivityCompat.OnRequestPermissionsResultCallback permissionsResultCallback) {
        this.permissionsResultCallback = permissionsResultCallback;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (this.permissionsResultCallback != null) {
            this.permissionsResultCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
            this.permissionsResultCallback = null;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void displayError(String message) {
        if (!this.isFinishing()) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                    .setTitle(this.getString(R.string.dialog_error_title))
                    .setMessage(message)
                    .setNeutralButton(R.string.dialog_action_ok, null);
            alertDialog.show();
        }
    }


    protected boolean checkIfNetworkAvailable() {
        if (getApplicationContext() == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
