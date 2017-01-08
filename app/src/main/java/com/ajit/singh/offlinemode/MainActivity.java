package com.ajit.singh.offlinemode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import static com.ajit.singh.offlinemode.NetworkStateChangeMessenger.IS_NETWORK_AVAILABLE;
import static com.ajit.singh.offlinemode.NetworkStateChangeMessenger.NETWORK_STATE_CHANGE_ACTION;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    IntentFilter intentFilter = new IntentFilter(NETWORK_STATE_CHANGE_ACTION);
    LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
        String networkStatus = isNetworkAvailable ? "connected" : "disconnected";

        Snackbar.make(findViewById(R.id.activity_main), "Network Status: " + networkStatus, Snackbar.LENGTH_LONG).show();
      }
    }, intentFilter);
  }
}
