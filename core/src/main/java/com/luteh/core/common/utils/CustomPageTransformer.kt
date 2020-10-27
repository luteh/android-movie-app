package com.luteh.core.common.utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class CustomPageTransformer(
    private val nextItemVisibleDimen: Float,
    private val currentItemHorizontalMarginDimen: Float
) :
    ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.translationX = -(nextItemVisibleDimen + currentItemHorizontalMarginDimen) * position
        // Next line scales the item's height. You can remove it if you don't want this effect
        page.scaleY = 1 - (0.25f * abs(position))
        // If you want a fading effect uncomment the next line:
                 page.alpha = 0.25f + (1 - abs(position))
    }
}