package com.nulp.graphedu.data.palette

import android.graphics.Bitmap

class BitmapPaletteGenerator(
    private val bitmap: Bitmap
) {

    fun generate(): BitmapPalette {
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, 0, 0, 0, bitmap.width, bitmap.height)
        val colors = sortedSetOf<Int>()
        for (i in pixels.indices) {
            colors += pixels[i]
        }
        return BitmapPalette(colors)
    }
}