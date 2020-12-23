package com.nulp.graphedu.data.colors.container

import android.graphics.Bitmap
import android.graphics.Color
import com.nulp.graphedu.data.colors.entity.RgbPixelColor

class ContainerGenerator(
    private val bitmap: Bitmap
) {
    fun generateHSLContainer(): ColorsContainer {
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, 0, 0, 0, bitmap.width, bitmap.height)

        return ColorContainerImpl(pixels.map {
            RgbPixelColor(
                Color.red(it),
                Color.green(it),
                Color.blue(it)
            ).toHsl()
        }.toTypedArray())
    }

    fun generateRGBContainer(): ColorsContainer {
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        return ColorContainerImpl(pixels.map {
            RgbPixelColor(
                Color.red(it),
                Color.green(it),
                Color.blue(it)
            )
        }.toTypedArray())
    }
}