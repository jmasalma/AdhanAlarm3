package islam.adhanalarm;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.sourceforge.jitl.astro.Location;

import islam.adhanalarm.handler.ScheduleData;
import islam.adhanalarm.handler.ScheduleHandler;

public class PrayerTimeScheduler {

    public static ScheduleData scheduleAlarms(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        String latitude = settings.getString("latitude", null);
        String longitude = settings.getString("longitude", null);

        if (latitude != null && longitude != null) {
            String altitude = settings.getString("altitude", "0");
            String pressure = settings.getString("pressure", "1010");
            String temperature = settings.getString("temperature", "10");
            Location locationAstro = ScheduleHandler.getLocation(latitude, longitude, altitude, pressure, temperature);
            String calculationMethodIndex = settings.getString("calculationMethodsIndex", String.valueOf(CONSTANT.DEFAULT_CALCULATION_METHOD));
            String roundingTypeIndex = settings.getString("roundingTypesIndex", String.valueOf(CONSTANT.DEFAULT_ROUNDING_TYPE));
            int offsetMinutes = 0;
            try {
                offsetMinutes = Integer.parseInt(settings.getString("offsetMinutes", "0"));
            } catch (NumberFormatException e) {
                // Ignore and use 0
            }
            ScheduleData newScheduleData = ScheduleHandler.calculate(locationAstro, calculationMethodIndex, roundingTypeIndex, offsetMinutes);
            ScheduleHandler.scheduleAlarms(context, newScheduleData);
            return newScheduleData;
        }
        return null;
    }
}
