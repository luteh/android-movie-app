package com.luteh.core.common.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Luthfan Maftuh on 8/30/2020.
 */
class HorizontalMarginItemDecoration(
    horizontalMarginDimen: Float,
    private val isViewPager: Boolean = false
) :
    RecyclerView.ItemDecoration() {

    private val horizontalMarginInPx: Int = horizontalMarginDimen.toInt()

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (isViewPager) {
                right = horizontalMarginInPx
                left = horizontalMarginInPx
            } else {
                left = if (isFirstItem(view, parent)) {
                    horizontalMarginInPx
                } else {
                    horizontalMarginInPx / 2
                }

                right = if (isLastItem(view, parent, state)) {
                    horizontalMarginInPx
                } else {
                    0
                }
            }
        }
    }

    private fun isFirstItem(view: View, parent: RecyclerView): Boolean {
        return parent.getChildAdapterPosition(view) == 0
    }

    private fun isLastItem(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean {
        return parent.getChildAdapterPosition(view) == state.itemCount - 1
    }
}