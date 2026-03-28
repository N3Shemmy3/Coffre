package dev.n3shemmy3.coffre.util

import android.content.Context
import android.text.format.DateFormat
import android.text.format.DateUtils
import java.util.Date


fun toRelativeTime(timestamp: Long): String {
    return DateUtils.getRelativeTimeSpanString(
        timestamp,
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS
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