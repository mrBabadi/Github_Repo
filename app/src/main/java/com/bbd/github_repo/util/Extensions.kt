package com.bbd.github_repo.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.isWhiteSpace(): Boolean {
    this.map {
        if (it.isWhitespace()) {
            return true
        }
    }
    return false
}

fun String.toSimpleDate(): String {

    if ("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z".toRegex().matches(this)) {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date = dateFormat.parse(this)
            println(dateFormat.format(date))
            dateFormat.format(date)
        } catch (e: ParseException) {
            "UnSupportedDate"
        }
    }

    return "UnSupportedDate"
}

fun Activity.launchBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    try {
        startActivity(intent)
    } catch (ex: ActivityNotFoundException) {
        throw ActivityNotFoundException()
    }
}