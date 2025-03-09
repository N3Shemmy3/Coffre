package dev.n3shemmy3.coffre.ui.utils;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static boolean is24HourFormat(Context context) {
        return DateFormat.is24HourFormat(context);
    }

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

    public static long getTimeMillis(@NonNull MaterialDatePicker<Long> date, @NonNull MaterialTimePicker time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (date.getSelection() != null && time.getHour() != 0 && time.getMinute() != 0) {
            calendar.setTimeInMillis(date.getSelection());
            Log.d("TIME", "getTimeMillis: " + date.getSelection());
            Log.d("Hour", "getHour: " + time.getHour());
            Log.d("Minute", "getMinute: " + time.getMinute());
            calendar.set(Calendar.HOUR_OF_DAY, time.getHour());
            calendar.set(Calendar.MINUTE, time.getMinute());
        }
        return calendar.getTimeInMillis();
    }
}
