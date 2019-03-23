package com.example.connectivitycheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInternetActivity();
    }


    private void checkInternetActivity() {

        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int types[] = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};

                if (CheckInternetConnection.isNetworkAvailable(context, types)) {
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();;
                } else {
                    Toast.makeText(context, "No internet!!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }
}
