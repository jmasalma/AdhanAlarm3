package islam.adhanalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                context.getApplicationContext().startForegroundService(new Intent(context.getApplicationContext(), PrayerTimeSchedulingService.class));
            } else {
                context.getApplicationContext().startService(new Intent(context.getApplicationContext(), PrayerTimeSchedulingService.class));
            }
        }
    }
}
