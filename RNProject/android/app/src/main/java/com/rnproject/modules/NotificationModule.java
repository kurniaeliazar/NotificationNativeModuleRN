package com.rnproject.modules;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.rnproject.MainActivity;
import com.rnproject.R;
import com.rnproject.modules.notification.NotificationAlarmReceiver;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

import static android.app.AlarmManager.INTERVAL_DAY;
import static android.app.AlarmManager.RTC_WAKEUP;

/**
 * Created by kurniaeliazar on 2/27/17.
 */

public class NotificationModule extends ReactContextBaseJavaModule {

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String GROUP_MESSAGE = "brightr_notification";
    private static int NOTIFICATION_ID = 2512;
    private final AlarmManager alarmManager;

    public NotificationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        alarmManager = (AlarmManager) reactContext.getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    public String getName() {
        return "NotificationModule";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        return constants;
    }


    @ReactMethod
    public void showNotification(String title, String message) {

        NotificationCompat.Builder builder = new NotificationCompat.
                Builder(getReactApplicationContext());

        Intent resultIntent = new Intent(getReactApplicationContext(), MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getReactApplicationContext());

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
                getReactApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    @ReactMethod
    public void scheduleNotification(String title, String message){

        final Intent receiverIntent = new Intent(getReactApplicationContext(),
                NotificationAlarmReceiver.class);
        receiverIntent.putExtra("NOTIF_TITLE", title);
        receiverIntent.putExtra("NOTIF_MESSAGE", message);

        PendingIntent notificationAlarmPendingIntent = PendingIntent.getBroadcast(
                getReactApplicationContext(), NOTIFICATION_ID, receiverIntent, 0);

        final long alarmTime = DateTime.now()
                .withHourOfDay(18)
                .withMinuteOfHour(0)
                .getMillis();

        alarmManager.setRepeating(RTC_WAKEUP, alarmTime, INTERVAL_DAY, notificationAlarmPendingIntent);

    }

    @ReactMethod
    public void cancelNotification(String title, String message){
        final Intent receiverIntent = new Intent(getReactApplicationContext(),
                NotificationAlarmReceiver.class);
        receiverIntent.putExtra("NOTIF_TITLE", title);
        receiverIntent.putExtra("NOTIF_MESSAGE", message);

        PendingIntent removeChallengeAlarmPendingIntent = PendingIntent.getBroadcast(
                getReactApplicationContext(), NOTIFICATION_ID, receiverIntent, 0);
        alarmManager.cancel(removeChallengeAlarmPendingIntent);
    }

}
