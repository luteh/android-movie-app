package com.luteh.core.common.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.luteh.core.common.constants.ActionConstants.CLASS_NAME_TRAILER

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
enum class Features(val className: String) {
    TRAILER(CLASS_NAME_TRAILER)
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