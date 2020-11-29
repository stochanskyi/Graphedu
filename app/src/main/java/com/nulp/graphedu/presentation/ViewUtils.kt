package com.nulp.graphedu.presentation

import android.graphics.Color
import android.view.View
import android.view.ViewTreeObserver

object ViewUtils {
    fun resolveContrastColor(color: Int): Int {
        val luminance: Double = (
                0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)
                ) / 255

        return if (luminance > 0.5) Color.BLACK
        else Color.WHITE
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