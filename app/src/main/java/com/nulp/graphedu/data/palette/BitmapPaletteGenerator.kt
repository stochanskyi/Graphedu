package com.nulp.graphedu.data.palette

import android.graphics.Bitmap
import android.graphics.Color
import com.nulp.graphedu.data.colors.container.ColorContainerImpl
import com.nulp.graphedu.data.colors.container.ColorsContainer
import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.data.colors.entity.RgbPixelColor

class BitmapPaletteGenerator(
    private val bitmap: Bitmap
) {

    fun generate(): ColorsContainer {
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, 0, 0, 0, bitmap.width, bitmap.height)
        val colors = sortedSetOf<PixelColor>()
        for (i in pixels.indices) {
            val color = pixels[i]
            colors += RgbPixelColor(
                Color.red(color),
                Color.green(color),
                Color.blue(color)
            )
        }
        return ColorContainerImpl(colors.toTypedArray())
    }
}