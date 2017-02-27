package com.rnproject.modules.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.rnproject.MainActivity;
import com.rnproject.R;

/**
 * Created by kurniaeliazar on 2/27/17.
 */

public class NotificationAlarmReceiver extends BroadcastReceiver {

    private static final String GROUP_MESSAGE = "brightr_notification";
    private static int NOTIFICATION_ID = 2512;

    @Override
    public void onReceive(Context context, Intent intent) {
        String title =  intent.getExtras().getString("NOTIF_TITLE");
        String message =  intent.getExtras().getString("NOTIF_MESSAGE");
        showNotification(context, title, message);
    }

    public void showNotification(Context context, String title, String message) {

        NotificationCompat.Builder builder = new NotificationCompat.
                Builder(context);

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_ONE_SHOT);

        builder.setContentTitle(title.toUpperCase())
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setGroup(GROUP_MESSAGE)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message));

        final NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
