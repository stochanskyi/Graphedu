package com.nulp.graphedu.data.colors.entity

import com.nulp.graphedu.data.colors.transformers.RgbToHslTransformer
import kotlinx.android.parcel.Parcelize

@Parcelize
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

    override fun colorOfSameType(input: PixelColor): PixelColor {
        return input.toRgb()
    }
}