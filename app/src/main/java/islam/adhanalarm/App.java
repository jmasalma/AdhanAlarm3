package islam.adhanalarm;

import android.app.Application;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class App extends Application {

    private static App sInstance;

    private MediaPlayer mPlayer;

    public static void startMedia(int resid) {
        sInstance.mPlayer.stop();
        sInstance.mPlayer = MediaPlayer.create(sInstance, resid);
        sInstance.mPlayer.setScreenOnWhilePlaying(true);
        sInstance.mPlayer.start();
    }

    public static void stopMedia() {
        sInstance.mPlayer.stop();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mPlayer = MediaPlayer.create(this, R.raw.bismillah);
        NotificationHelper.createNotificationChannel(this);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                Intent intent = new Intent(getApplicationContext(), CrashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                java.io.StringWriter sw = new java.io.StringWriter();
                java.io.PrintWriter pw = new java.io.PrintWriter(sw);
                e.printStackTrace(pw);
                String stackTrace = sw.toString();
                intent.putExtra("stacktrace", stackTrace);
                startActivity(intent);
                System.exit(1);
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, PrayerTimeSchedulingService.class));
        } else {
            startService(new Intent(this, PrayerTimeSchedulingService.class));
        }
    }

    @Override
    public void onTerminate() {
        sInstance.mPlayer.stop();
        super.onTerminate();
    }
}
