package com.example.darya.joke;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


public class DelayedMessageService extends IntentService {

    private Handler handler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    public static final String EXTRA_MESSAGE = "message";
    private static final String DELAYED_MESSAGE = "DelayedMessageService";

    public DelayedMessageService() {
        super(DELAYED_MESSAGE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }

    private void showText(final String text) {
//        Log.v(DELAYED_MESSAGE, "The message is: " + text);
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();
            }
        });
    }


}
