package com.example.musicplayerapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class MusicService extends Service {
    private static final String CHANNEL_ID = "music_service_channel";
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MusicService", "Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
            startForeground(1, buildNotification());
        }

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.only); // Ensure the file is in res/raw
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start(); // Start playback immediately
                Log.d("MusicService", "MediaPlayer started successfully.");
            } else {
                Log.e("MusicService", "MediaPlayer initialization failed");
                stopSelf(); // Stop service if initialization fails
            }
        } else if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start(); // Start playback if not already playing
            Log.d("MusicService", "MediaPlayer started successfully.");
        } else {
            Log.d("MusicService", "MediaPlayer is already playing.");
        }

        return START_STICKY;
    }

    private Notification buildNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Music Player")
                .setContentText("Playing music...")
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Ensure icon exists
                .setContentIntent(pendingIntent)
                .build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Music Service Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Log.d("MusicService", "Service Destroyed");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
