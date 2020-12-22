package com.nulp.graphedu.data.colors

import com.nulp.graphedu.data.colors.transformers.RgbToHslTransformer

data class RgbPixelColor(
    val r: Int,
    val g: Int,
    val b: Int
) : PixelColor {

    override fun toRgb(): RgbPixelColor {
        return this
    }

    override fun toHsl(): HslPixelColor {
        return RgbToHslTransformer.transform(this)
    }
}