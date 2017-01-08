package com.ajit.singh.offlinemode;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;

import com.ajit.singh.offlinemode.receiver.NetworkStateChangeReceiver;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class NetworkStateChangeRegistrar {

  private static final String WIFI_STATE_CHANGE_ACTION = "android.net.wifi.WIFI_STATE_CHANGED";
  private static boolean isConnected = false;

  public static void register(final Context context) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      NetworkStateChangeReceiver networkStateChangeReceiver = new NetworkStateChangeReceiver();
      context.registerReceiver(networkStateChangeReceiver, new IntentFilter(CONNECTIVITY_ACTION));
      context.registerReceiver(networkStateChangeReceiver, new IntentFilter(WIFI_STATE_CHANGE_ACTION));
    } else {
      registerForAPI21AndAbove(context);
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private static void registerForAPI21AndAbove(final Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

    ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
      @Override
      public void onAvailable(Network network) {
        if (isConnected) return;
        isConnected = true;
        NetworkStateChangeMessenger.sendMessage(context);
      }

      @Override
      public void onLost(Network network) {
        if (!isConnected) return;
        isConnected = false;
        NetworkStateChangeMessenger.sendMessage(context);
      }
    };

    connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), networkCallback);
  }
}