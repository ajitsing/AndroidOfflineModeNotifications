package com.ajit.singh.offlinemode.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ajit.singh.offlinemode.NetworkStateChangeMessenger;

public class NetworkStateChangeReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    NetworkStateChangeMessenger.sendMessage(context);
  }
}
