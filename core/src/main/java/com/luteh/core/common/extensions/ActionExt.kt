package com.luteh.core.common.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.luteh.core.common.constants.ActionConstants.CLASS_NAME_MAIN
import com.luteh.core.common.constants.ActionConstants.CLASS_NAME_TRAILER

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
enum class Features(val className: String) {
    TRAILER(CLASS_NAME_TRAILER), MAIN(CLASS_NAME_MAIN)
}

fun Context.navigateTo(features: Features, bundle: Bundle? = null) {
    val intent = Intent().apply {
        setClassName(this@navigateTo, features.className)
        bundle?.let {
            putExtras(it)
        }
    }
    startActivity(intent)
}

fun Fragment.navigateTo(features: Features, bundle: Bundle? = null) {
    val intent = Intent().apply {
        setClassName(this@navigateTo.requireContext(), features.className)
        bundle?.let {
            putExtras(it)
        }
    }
    startActivity(intent)
}