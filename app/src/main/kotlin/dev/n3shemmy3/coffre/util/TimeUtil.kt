package dev.n3shemmy3.coffre.util

import android.content.Context
import android.text.format.DateFormat
import android.text.format.DateUtils
import java.util.Date


fun relativeTime(timestamp: Long): String {
    return DateUtils.getRelativeTimeSpanString(
        timestamp,
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS
    ).toString()
}

fun humanTime(timestamp: Long, context: Context): String {
    val timeFormat =
        DateFormat.getTimeFormat(context)
    return timeFormat.format(Date(timestamp))
}