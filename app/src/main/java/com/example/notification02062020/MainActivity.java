package com.example.notification02062020;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    Button mBtnCreateNotifi;
    String CHANEL_ID = "MY_CHANEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCreateNotifi = findViewById(R.id.buttonCreateNotifi);

        if (getIntent() != null) {
            int value = getIntent().getIntExtra("notification", -2);
            switch (value) {
                case -1:
                    Toast.makeText(this, "Button Pre", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    Toast.makeText(this, "Button Play", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(this, "Button next", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        mBtnCreateNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pre
                Intent intentPre = new Intent(MainActivity.this,MainActivity.class);
                intentPre.putExtra("notification", -1);
                intentPre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intentPre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingPre =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                123,
                                intentPre,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                //Next
                Intent intentNext = new Intent(MainActivity.this,MainActivity.class);
                intentNext.putExtra("notification", 1);
                intentNext.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intentNext.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingNext =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                456,
                                intentNext,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                //Start
                Intent intentStart = new Intent(MainActivity.this,MainActivity.class);
                intentStart.putExtra("notification", 0);
                intentStart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intentStart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingPause =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                789,
                                intentStart,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, CHANEL_ID)
                                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                                .setShowWhen(true)
                                .setWhen(System.currentTimeMillis())
                                .setLargeIcon(
                                        BitmapFactory.decodeResource(
                                                getResources(),
                                                R.drawable.ic_baseline_mood_24)
                                )
                                .setContentTitle("Thông báo có nội dung mới")
                                .setContentText("Sự kiện sắp được ra mắt")

                                .addAction(R.drawable.ic_baseline_skip_previous_24, "Pre", pendingPre)
                                .addAction(R.drawable.ic_baseline_pause_24, "Pause", pendingPause)
                                .addAction(R.drawable.ic_baseline_skip_next_24, "Next", pendingNext)
                                .setStyle(
                                        new androidx.media.app.NotificationCompat.MediaStyle()
                                                .setShowActionsInCompactView(0, 1, 2));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel =
                            new NotificationChannel(
                                    CHANEL_ID,
                                    "NOTIFICATION",
                                    NotificationManager.IMPORTANCE_DEFAULT
                            );
                    notificationManager.createNotificationChannel(notificationChannel);

                }
                notificationManager.notify(1, builder.build());
            }
        });
    }

}