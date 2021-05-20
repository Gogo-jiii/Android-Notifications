package com.example.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationManager {

    private static NotificationManager instance = null;
    private Context context;
    private NotificationCompat.Builder builder = null;
    private final String CHANNEL_NAME = "CHANNEL_NAME";
    private final String CHANNEL_DESCRIPTION = "CHANNEL_DESCRIPTION";
    private final String CHANNEL_ID = "CHANNEL_ID";
    private NotificationModel model;
    private android.app.NotificationManager notificationManager;

    private NotificationManager() {
    }

    public static NotificationManager getInstance(Context context) {
        if (instance == null) {
            instance = new NotificationManager();
        }
        instance.init(context);
        return instance;
    }

    private void init(Context context) {
        this.context = context;
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    android.app.NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager =
                    (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    NotificationManager createNotification(NotificationModel model) {
        this.model = model;
        builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(model.getIcon());
        builder.setContentTitle(model.getTitle());
        builder.setContentText(model.getContent());
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return instance;
    }

    void show() {
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(model.getId(), builder.build());
    }

    NotificationManager setBigText() {
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(model.getBigText()));
        return instance;
    }

    NotificationManager setCategory() {
        builder.setCategory(model.getCategory());
        return instance;
    }

    NotificationManager setBigPicture() {
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(model.getBigPicture()));
        return instance;
    }

    NotificationManager setLargeIcon() {
        builder.setLargeIcon(model.getBigPicture());
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(model.getBigPicture()).bigLargeIcon(null));
        return instance;
    }

    NotificationManager setPendingIntent() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        return instance;
    }

    void cancelNotification(int id) {
        notificationManager.cancel(id);
    }

    void cancelAllNotifications() {
        notificationManager.cancelAll();
    }
}
