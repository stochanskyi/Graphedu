package com.nulp.graphedu.data.colors.entity

import com.nulp.graphedu.data.colors.transformers.HslToRgbTransformer

data class HslPixelColor(
    val h: Int,
    val s: Int,
    val l: Int
) : PixelColor {

    init {
        require(h in 0..360) { "h must be in range [0, 360]" }
        require(s in 0..100) { "s must be in range [0, 100]" }
        require(l in 0..100) { "l must be in range [0, 100]" }
    }

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