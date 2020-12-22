package com.nulp.graphedu.data.colors

interface ColorTransformable {

    fun toRgb(): RgbPixelColor

    fun toHsl(): HslPixelColor
}