package com.example.notification02062020;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                        .setContentText("Sự kiện sắp được ra mắt");
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