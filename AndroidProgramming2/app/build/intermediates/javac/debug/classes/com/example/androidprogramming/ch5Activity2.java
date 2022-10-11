package com.example.android;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class ch5Activity2 extends AppCompatActivity {
    TimePicker btnSelectedTime;
    TextView display;
    Button button1;
    String NOTIFICATION_CHANNEL_ID = "my_channel";

    // NotificationChannel 객체 생성
    // NotificationManager를 객체를 불러와 알림 채널을 등록
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Channel description");
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    public void sendNotification(View view) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            display = findViewById(R.id.text1);
            int mHour = btnSelectedTime.getHour();
            int mMinute = btnSelectedTime.getMinute();
            notificationBuilder.setSmallIcon(R.drawable.img);
            notificationBuilder.setContentTitle("time");
            notificationBuilder.setContentText("[time] " + mHour + ":" +  mMinute);
            notificationBuilder.setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch5_activity2);
        createNotificationChannel();
        btnSelectedTime = (TimePicker) findViewById(R.id.timePicker1);
        
        // 시간 설정 변수 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btnSelectedTime = findViewById(R.id.timePicker1);
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            btnSelectedTime.setHour(mHour);
            btnSelectedTime.setMinute(mMinute);
        }

        
        // 시간 설정 함수
        button1 = findViewById(R.id.timebutton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    display = findViewById(R.id.text1);
                    int mHour = btnSelectedTime.getHour();
                    int mMinute = btnSelectedTime.getMinute();
                    display.setText("[time] " + mHour + ":" +  mMinute);
                    sendNotification(v);
                }
            }
        });
    }
//    public void onClick(View v) {
//        if (v == btnSelectedTime) {
//            final Calendar c = Calendar.getInstance();
//            int mHour = c.get(Calendar.HOUR_OF_DAY);
//            int mMinute = c.get(Calendar.MINUTE);
//            timePickerDialog = new TimePickerDialog(this,
//                    new TimePickerDialog.OnTimeSetListener(){
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                            display.setText(hourOfDay + ":" + minute);
//                        }
//                    }, mHour, mMinute, false
//                    );
//            timePickerDialog.show();
//
//            }
//
//
//    }

}
