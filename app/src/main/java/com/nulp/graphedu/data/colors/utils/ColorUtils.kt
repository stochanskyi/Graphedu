package com.nulp.graphedu.data.colors.utils

import android.graphics.Color
import com.nulp.graphedu.data.colors.entity.PixelColor

fun PixelColor.toAndroidColor(): Int {
    return with(this.toRgb()) {
        Color.rgb(r, g, b)
    }
}