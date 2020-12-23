package com.nulp.graphedu.data.colors.entity

import com.nulp.graphedu.data.colors.transformers.RgbToHslTransformer

data class RgbPixelColor(
    val r: Int,
    val g: Int,
    val b: Int
) : PixelColor {

    init {
        require(r in 0..255) { "r must be in range [0, 255] in $this" }
        require(g in 0..255) { "g must be in range [0, 255] in $this" }
        require(b in 0..255) { "b must be in range [0, 255] in $this" }
    }

    override fun toRgb(): RgbPixelColor {
        return this
    }

    override fun toHsl(): HslPixelColor {
        return RgbToHslTransformer.transform(this)
    }

    override fun colorOfSameType(input: PixelColor): PixelColor {
        return input.toRgb()
    }
}