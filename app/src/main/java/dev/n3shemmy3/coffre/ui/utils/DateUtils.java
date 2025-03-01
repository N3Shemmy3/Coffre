package dev.n3shemmy3.coffre.ui.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String formatTime(int hour, int minute, boolean is24HourFormat) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat(is24HourFormat ? "HH:mm" : "hh:mm a", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        return timeFormatter.format(cal.getTime());
    }

    public static String formatDate(long timeInMillis, Context context) {
        return DateFormat.getDateFormat(context).format(new Date(timeInMillis));
    }

    public static boolean isToday(long timeInMillis) {
        Calendar now = Calendar.getInstance();
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTimeInMillis(timeInMillis);
        return now.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR);
    }

    public static Calendar getCurrentTime() {
        return Calendar.getInstance();
    }
}
