package com.example.darya.taskservice;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;


public class WombleService extends IntentService {

    public WombleService() {
        super("WombleService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.lone_digger);
        mediaPlayer.start();
    }
}
