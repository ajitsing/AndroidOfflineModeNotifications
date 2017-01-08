package com.ajit.singh.offlinemode;

import android.app.Application;

public class MyApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    NetworkStateChangeRegistrar.register(this);
  }
}
