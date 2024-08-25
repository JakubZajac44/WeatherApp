package com.jakub.zajac.common.resource.util

import java.time.OffsetDateTime


fun String.parseFullDateToTime(): String {
    val odt = OffsetDateTime.parse(this)
    val hour = odt.toLocalTime().hour
    val minute = odt.toLocalTime().minute

    val hourString = if (hour < 10) "0${hour}"
    else hour.toString()

    val minuteString = if (minute < 10) "0${minute}"
    else minute.toString()

    return "$hourString:$minuteString"
}

fun String.parseFullDateToMonthAndDay(): String {
    val odt = OffsetDateTime.parse(this)
    val day = odt.toLocalDate().dayOfMonth
    val month = odt.toLocalDate().monthValue

    val dayString = if (day < 10) "0${day}"
    else day.toString()

    val monthString = if (month < 10) "0${month}"
    else month.toString()

    return "$dayString-$monthString"
}

