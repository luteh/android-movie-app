package com.luteh.movieapp.common.extensions

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat

/**
 * Created by Luthfan Maftuh
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.shouldVisible(isShouldVisible: Boolean) {
    if (isShouldVisible) visible() else gone()
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.setTint(@ColorRes colorResId: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorResId)))
}

fun TextView.setColor(@ColorRes colorResId: Int) {
    this.setTextColor(ContextCompat.getColor(context, colorResId))
}
