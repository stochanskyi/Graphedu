package com.nulp.graphedu.data.colors.image

import android.graphics.Bitmap
import android.graphics.Color
import com.nulp.graphedu.data.colors.entity.PixelColor

class ColorsBitmapProviderImpl(
    private val width: Int,
    private val height: Int,
    private val colors: Array<PixelColor>
): ColorsBitmapProvider {

    init {
        if (width * height != colors.size) throw IllegalStateException(
            "Incorrect width and height with colors array size"
        )
    }

    override fun generateBitmap(): Bitmap {
        val pixels = IntArray(colors.size) { i ->
            val color = colors[i].toRgb()
            Color.rgb(color.r, color.g, color.b)
        }
        return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.RGB_565)
    }
}