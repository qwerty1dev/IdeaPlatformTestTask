package com.example.ideaplatformtesttask.utilts

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeParser {

    @SuppressLint("ConstantLocale")
    private val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    fun formatTimestamp(timestamp: Long): String {
        return formatter.format(Date(timestamp))
    }
}