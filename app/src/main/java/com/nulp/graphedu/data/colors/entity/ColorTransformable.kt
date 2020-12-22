package com.nulp.graphedu.data.colors.entity

interface ColorTransformable {

    fun toRgb(): RgbPixelColor

    fun toHsl(): HslPixelColor

    fun colorOfSameType(input: PixelColor): PixelColor
}