package dev.n3shemmy3.coffre.util

import android.content.Context
import android.icu.util.Calendar
import android.text.format.DateFormat
import android.text.format.DateUtils
import java.util.Date


fun toRelativeTime(timestamp: Long): String {
    return DateUtils.getRelativeTimeSpanString(
        timestamp,
        System.currentTimeMillis(),
        DateUtils.SECOND_IN_MILLIS
    ).toString()
}

fun toRelativeDateTime(timestamp: Long, context: Context): String {
    return DateUtils.getRelativeDateTimeString(
        context,
        timestamp,
        DateUtils.WEEK_IN_MILLIS,
        System.currentTimeMillis(),
        DateUtils.FORMAT_ABBREV_ALL
    ).toString()
}

fun toHumanTime(timestamp: Long, context: Context): String {
    val timeFormat =
        DateFormat.getTimeFormat(context)
    return timeFormat.format(Date(timestamp))
}

fun toHumanDate(timestamp: Long, context: Context): String {
    val timeFormat =
        DateFormat.getDateFormat(context)
    return timeFormat.format(Date(timestamp))
}

fun toMilliseconds(hour: Int, minute: Int, date: Long): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minute)
    return calendar.timeInMillis
}