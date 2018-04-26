package com.example.darya.odometer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class OdometerService extends Service {
    private final IBinder binder = new OdometerBinder();

    public class OdometerBinder extends Binder {
        OdometerService getOdometer() {
            return OdometerService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
       return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
