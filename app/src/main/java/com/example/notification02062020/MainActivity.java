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

        mBtnCreateNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.putExtra("chuoi","Restart Activity");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                MainActivity.this,
                                123,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this , CHANEL_ID)
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
                        .setContentIntent(pendingIntent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel notificationChannel =
                            new NotificationChannel(
                                    CHANEL_ID,
                                    "NOTIFICATION",
                                    NotificationManager.IMPORTANCE_DEFAULT
                            );
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(1,builder.build());
            }
        });
    }

}