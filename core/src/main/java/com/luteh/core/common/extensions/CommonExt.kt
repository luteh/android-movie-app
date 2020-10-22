package com.luteh.core.common.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */

/**
 * Show [Toast] message
 *
 * @param message   The toast message [String]
 * @param duration  The toast duration [Int]
 */
fun Context.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration)
        .show()

/**
 * Show [Toast] message
 *
 * @param context   The context [Context]
 * @param message   The toast message [String]
 * @param duration  The toast duration [Int]
 */
@JvmName("showToast1")
fun showToast(context: Context, @StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, duration)
        .show()