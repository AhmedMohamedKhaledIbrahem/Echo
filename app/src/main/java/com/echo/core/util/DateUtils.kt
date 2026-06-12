@file:OptIn(FormatStringsInDatetimeFormats::class)

package com.echo.core.util

import kotlin.time.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun Long.formatMillisToTime(): String {
    val durationInSeconds = this / 1000

    val localDateTime =
        Instant
            .fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    val pattern =
        if (durationInSeconds < 3600) {
            "ss:SS"
        } else {
            "mm:ss:SS"
        }

    val format =
        LocalDateTime.Format {
            byUnicodePattern(pattern = pattern)
        }
    return localDateTime.format(format)
}


fun Instant.formatInstantToRelativeDay(): String {
    val pattern = DateTimeFormatter.ofPattern("EEEE, MMM d")
    val currentDate = ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate()
    val targetDate =
        this.toEpochMilliseconds().let {
            ZonedDateTime.ofInstant(java.time.Instant.ofEpochMilli(it), ZoneId.systemDefault())
                .toLocalDate()
        }

    return when (targetDate) {
        currentDate -> "Today"
        currentDate.minusDays(1) -> "Yesterday"
        else -> targetDate.format(pattern)
    }
}

fun Instant.formatHoursAndMinutes(): String {
    val pattern = "HH:mm"
    val format = LocalDateTime.Format { byUnicodePattern(pattern = pattern) }

    val time = this.toLocalDateTime(TimeZone.currentSystemDefault())

    return time.format(format)
}
