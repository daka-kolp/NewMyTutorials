package com.example.darya.odometer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class TaskNumberService extends Service {

    private final IBinder binder = new NumberBinder();
    private final Random random = new Random();

    private class NumberBinder extends Binder {
        TaskNumberService getTaskNumberService() {
            return TaskNumberService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  binder;
    }

    public int getNumber() {
        return random.nextInt(100);
    }

}
