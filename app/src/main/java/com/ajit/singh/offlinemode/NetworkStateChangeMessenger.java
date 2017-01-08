package com.ajit.singh.offlinemode;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NetworkStateChangeMessenger {
  public static final String NETWORK_STATE_CHANGE_ACTION = "com.ajit.singh.NetworkStateChange";
  public static final String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

  public static void sendMessage(Context context) {
    Intent networkStateIntent = new Intent(NETWORK_STATE_CHANGE_ACTION);
    networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  isConnectedToInternet(context));
    LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);
  }

  private static boolean isConnectedToInternet(Context context) {
    try {
      if (context != null) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
      }
      return false;
    } catch (Exception e) {
      Log.e(NetworkStateChangeMessenger.class.getName(), e.getMessage());
      return false;
    }
  }
}
