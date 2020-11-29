package com.nulp.graphedu.presentation

import android.graphics.Color
import android.view.View
import android.view.ViewTreeObserver

object ViewUtils {
    fun resolveContrastColor(color: Int): Int {
        return if (isColorDark(color)) Color.WHITE
        else Color.BLACK
    }

    fun isColorDark(color: Int): Boolean {
        val luminance = (0.299 * Color.red(color) +
                0.587 * Color.green(color) +
                0.114 * Color.blue(color)
                ) / 255
        return luminance < 0.5
    }
}

inline fun View.waitForLayout(crossinline f: () -> Unit) {
    viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver?.removeOnGlobalLayoutListener(this)
            f()
        }
    })
}