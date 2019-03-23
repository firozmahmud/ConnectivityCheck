package com.example.connectivitycheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckInternetConnection extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int types[] = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};

        if (isNetworkAvailable(context, types)) {
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            ;
        } else {
            Toast.makeText(context, "No internet!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isNetworkAvailable(Context context, int[] networkTypes) {

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int type : networkTypes) {
                NetworkInfo networkInfo = cm.getNetworkInfo(type);
                if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {

                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
