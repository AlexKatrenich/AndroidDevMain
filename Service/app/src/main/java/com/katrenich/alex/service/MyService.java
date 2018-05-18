package com.katrenich.alex.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String TAG = "MyLog_";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate MyService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand MyService");
        doSomeTask();
        return super.onStartCommand(intent, flags, startId);
    }

    private void doSomeTask() {
        for (int i = 1; i<=5; i++) {
            Log.d(TAG, "i = " + i);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "Service do Some Task");

        this.stopSelf(); // об'єкт Сервіс викликає в себе метод onStop()
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind MyService");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy MyService");
        super.onDestroy();
    }
}
