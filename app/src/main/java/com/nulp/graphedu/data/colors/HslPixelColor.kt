package com.nulp.graphedu.data.colors

import com.nulp.graphedu.data.colors.transformers.HslToRgbTransformer

data class HslPixelColor(
    val h: Int,
    val s: Int,
    val l: Int
) : PixelColor {

    override fun toRgb(): RgbPixelColor {
        return HslToRgbTransformer.transform(this)
    }

    override fun toHsl(): HslPixelColor {
        return this
    }
}