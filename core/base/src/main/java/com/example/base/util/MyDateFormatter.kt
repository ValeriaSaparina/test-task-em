package com.example.base.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object MyDateFormatter {
    fun formatDate(date: String, locale: Locale): String {
        val dateApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", locale)
        val formattedDate = LocalDate.parse(date, dateApiFormat)
        return "${formattedDate.dayOfMonth} ${
            formattedDate.month.getDisplayName(
                TextStyle.FULL_STANDALONE,
                locale
            )
        }"
    }

    fun getMonth(date: String): Int {
        val dateApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = LocalDate.parse(date, dateApiFormat)
        return formattedDate.monthValue
    }

    fun getDay(date: String): Int {
        val dateApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = LocalDate.parse(date, dateApiFormat)
        return formattedDate.dayOfMonth
    }
}