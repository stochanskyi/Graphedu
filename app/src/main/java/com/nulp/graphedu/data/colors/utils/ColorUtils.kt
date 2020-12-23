package com.nulp.graphedu.data.colors.utils

import android.graphics.Color
import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.data.colors.entity.RgbPixelColor

fun PixelColor.toAndroidColor(): Int {
    return with(this.toRgb()) {
        Color.rgb(r, g, b)
    }
}

fun Int.toRGBColor(): RgbPixelColor {
    return RgbPixelColor(
        Color.red(this),
        Color.blue(this),
        Color.green(this)
    )
}