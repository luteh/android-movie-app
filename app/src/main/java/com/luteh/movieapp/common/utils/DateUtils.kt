package com.luteh.movieapp.common.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
object DateUtils {

    const val DATE_FORMAT_YEAR = "yyyy"
    const val DATE_FORMAT_SHORT = "dd MMMM yyyy"
    const val DATE_FORMAT_SHORT_US = "yyyy-MM-dd"
    const val DATE_FORMAT_LONG_US = "yyyy-MM-dd HH:mm:ss"

    /**
     * Change string date format to targeted date format
     *
     * @param strDate The string date
     * @param currentDateFormat The [strDate] date format
     * @param targetDateFormat The target date format
     *
     * @return Formatted string date
     */
    fun changeStrDateFormat(strDate: String, currentDateFormat: String, targetDateFormat: String): String {
        return if (strDate.isNotEmpty()) {
            val datetime: Date? = SimpleDateFormat(currentDateFormat, Locale.getDefault()).parse(strDate)
            SimpleDateFormat(targetDateFormat, Locale.getDefault()).format(datetime ?: Date())
        } else {
            "No Date"
        }
    }
}