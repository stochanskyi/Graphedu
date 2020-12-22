package com.nulp.graphedu.data.colors.entity

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

    override fun colorOfSameType(input: PixelColor): PixelColor {
        return input.toHsl()
    }
}