package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDefaultNotification, btnBigTextNotification, btnBigPictureNotification,
            btnLargeIconNotification, btnPendingIntentNotification;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDefaultNotification = findViewById(R.id.btnDefaultNotification);
        btnBigTextNotification = findViewById(R.id.btnBigTextNotification);
        btnBigPictureNotification = findViewById(R.id.btnBigPictureNotification);
        btnLargeIconNotification = findViewById(R.id.btnLargeIconNotification);
        btnPendingIntentNotification = findViewById(R.id.btnPendingIntentNotification);

        btnDefaultNotification.setOnClickListener(this);
        btnLargeIconNotification.setOnClickListener(this);
        btnBigPictureNotification.setOnClickListener(this);
        btnBigTextNotification.setOnClickListener(this);
        btnPendingIntentNotification.setOnClickListener(this);

        notificationManager = NotificationManager.getInstance(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDefaultNotification:
                NotificationModel defaultModel = new NotificationModel();
                defaultModel.setId(1);
                defaultModel.setTitle("Default Notification");
                defaultModel.setContent("This is content.");
                defaultModel.setIcon(R.drawable.ic_android);

                notificationManager.createNotification(defaultModel).show();
                break;

            case R.id.btnLargeIconNotification:
                NotificationModel largeIconModel = new NotificationModel();
                largeIconModel.setId(2);
                largeIconModel.setTitle("Large Icon Notification");
                largeIconModel.setContent("This is content.");
                largeIconModel.setIcon(R.drawable.ic_android);

                HandlerThread handlerThread = new HandlerThread("MyHandlerThread");
                handlerThread.start();
                Looper looper = handlerThread.getLooper();
                Handler handler = new Handler(looper);
                handler.post(new Runnable() {
                    @Override public void run() {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                R.drawable.android);
                        largeIconModel.setBigPicture(bitmap);
                        notificationManager.createNotification(largeIconModel).setLargeIcon().show();
                        handlerThread.quit();
                    }
                });
                break;

            case R.id.btnBigPictureNotification:
                NotificationModel bigPictureModel = new NotificationModel();
                bigPictureModel.setId(3);
                bigPictureModel.setTitle("Big Picture Notification");
                bigPictureModel.setContent("This is content.");
                bigPictureModel.setIcon(R.drawable.ic_android);

                HandlerThread handlerThread1 = new HandlerThread("MyHandlerThread1");
                handlerThread1.start();
                Looper looper1 = handlerThread1.getLooper();
                Handler handler1 = new Handler(looper1);
                handler1.post(new Runnable() {
                    @Override public void run() {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                R.drawable.android);
                        bigPictureModel.setBigPicture(bitmap);
                        notificationManager.createNotification(bigPictureModel).setBigPicture().show();
                        handlerThread1.quit();
                    }
                });
                break;

            case R.id.btnBigTextNotification:
                NotificationModel bigTextModel = new NotificationModel();
                bigTextModel.setId(4);
                bigTextModel.setTitle("Big Text Notification");
                bigTextModel.setContent("This is content.");
                bigTextModel.setIcon(R.drawable.ic_android);
                bigTextModel.setBigText("Lorem Ipsum is simply dummy text of the printing and " +
                        "typesetting industry. Lorem Ipsum has been the industry's standard dummy" +
                        " text ever since the 1500s, when an unknown printer took a galley of " +
                        "type and scrambled it to make a type specimen book. It has survived not " +
                        "only five centuries, but also the leap into electronic typesetting, " +
                        "remaining essentially unchanged. It was popularised in the 1960s with " +
                        "the release of Letraset sheets containing Lorem Ipsum passages, and more" +
                        " recently with desktop publishing software like Aldus PageMaker " +
                        "including versions of Lorem Ipsum.");

                notificationManager.createNotification(bigTextModel).setBigText().show();
                break;

            case R.id.btnPendingIntentNotification:
                NotificationModel pendingIntentModel = new NotificationModel();
                pendingIntentModel.setId(5);
                pendingIntentModel.setTitle("Pending Intent Notification");
                pendingIntentModel.setContent("This is content.");
                pendingIntentModel.setIcon(R.drawable.ic_android);

                notificationManager.createNotification(pendingIntentModel).setPendingIntent().show();
                break;
        }
    }
}