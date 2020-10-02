package com.luteh.core.data.local.room

import androidx.room.TypeConverter
import timber.log.Timber

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class Converters {
    @TypeConverter
    fun fromListOfInt(listOfString: List<Int>): String {
        return listOfString.joinToString(",")
    }

    @TypeConverter
    fun gettingListFromString(genreIds: String): List<Int> {
        val list = mutableListOf<Int>()
        val array = genreIds.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toInt())
            }
        }
        return list
    }
}