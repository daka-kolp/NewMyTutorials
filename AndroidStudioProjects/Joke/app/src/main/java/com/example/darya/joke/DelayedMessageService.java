package com.example.darya.joke;

import android.app.IntentService;
import android.content.Intent;
import android.app.TaskStackBuilder;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

public class DelayedMessageService extends IntentService {

    public static final int NOTIFICATION_ID = 5453;
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
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        android.app.PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, android.app.PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getString(R.string.app_name))
            .setAutoCancel(true)
            .setPriority(Notification.PRIORITY_MAX)
            .setDefaults(Notification.DEFAULT_VIBRATE)
            .setContentIntent(pendingIntent)
            .setContentText(text)
            .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify (NOTIFICATION_ID, notification);

    }
}
