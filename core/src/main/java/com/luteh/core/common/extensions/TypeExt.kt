package com.luteh.core.common.extensions

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

fun Boolean.toInt(): Int = if (this) 1 else 0
fun Int.toBoolean(): Boolean = when (this) {
    0 -> false
    1 -> true
    else -> throw RuntimeException("toBoolean(): not available. The value must be 0 or 1")
}

fun Any?.orNothing(): Any = this ?: ""