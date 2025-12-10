package islam.adhanalarm;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class PrayerTimeSchedulingService extends Service {

    private static final int NOTIFICATION_ID = Integer.MAX_VALUE;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(NOTIFICATION_ID, createNotification());
        PrayerTimeScheduler.scheduleAlarms(this);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, "prayer_time_channel")
                .setContentTitle("Adhan Alarm")
                .setContentText("Ensuring timely prayer notifications.")
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(pendingIntent)
                .build();
    }
}
