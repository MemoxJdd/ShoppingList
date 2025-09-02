package com.jdd.shoppinglist.utils


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatMillisToDate(millis: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date(millis))
}