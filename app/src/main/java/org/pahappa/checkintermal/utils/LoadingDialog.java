package org.pahappa.checkintermal.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.pahappa.checkintermal.R;
import org.pahappa.checkintermal.databinding.LoadingDialogBinding;

public class LoadingDialog {
    private LoadingDialogBinding binding;
    private Activity activity;
    private AlertDialog alertDialog;
    private String alertMessage;
    private TextView textViewMessage;

    public LoadingDialog(Activity activity, String alertMessage) {
        this.activity = activity;
        this.alertMessage = alertMessage;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        binding = LoadingDialogBinding.inflate(activity.getLayoutInflater());

//        LayoutInflater inflater = activity.getLayoutInflater();
//        View builderView = inflater.inflate(R.layout.loading_dialog, null);


        View builderView = binding.getRoot();
        textViewMessage = binding.progressBarText;
        textViewMessage.setText(this.alertMessage);
        builder.setView(builderView);

        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void dismissDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }
}
