package com.durocrete_client.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by root on 20/6/17.
 */

public class Util {

    public static void errorDialog(String message , Activity activity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static void noConnectionAlert(Context context) {
        AlertDialog.Builder connection_builder = new AlertDialog.Builder(context);
        connection_builder.setMessage("Please check internet connection");
        connection_builder.setNegativeButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog exit_alertDialog = connection_builder.create();
        exit_alertDialog.show();
    }

    public static void showToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
