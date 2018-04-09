package com.example.darya.stopwatch;

import android.os.Handler;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends Activity {

    private int seconds;
    private boolean running;
    private boolean wasRunning;

    public static final String SECONDS = "seconds";
    public static final String RUNNING = "running";
    public static final String WAS_RUNNING = "wasRunning";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS);
            running = savedInstanceState.getBoolean(RUNNING);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING);
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(SECONDS, seconds);
        savedInstanceState.putBoolean(RUNNING, running);
        savedInstanceState.putBoolean(WAS_RUNNING, wasRunning);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (wasRunning) running = true;
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        wasRunning = running;
//        running = false;
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) running = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    public void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }
}
