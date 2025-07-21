package io.davidosemwota.moniepointx.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getDate(
    pattern: String = "dd MMM yyyy",
    date: Date = Date(),
): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(date)
}

fun getSpecificDate(
    day: Int,
    month: Int = Calendar.JANUARY,
    year: Int
): Date {
    require(month in 0..11) {
        "Month value must be between ${Calendar.JANUARY} JAN and ${Calendar.DECEMBER} DEC. Was: $month"
    }
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day) // Month is 0-based: January = 0
    return calendar.time
}