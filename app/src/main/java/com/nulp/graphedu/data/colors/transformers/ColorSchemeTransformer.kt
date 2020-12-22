package com.nulp.graphedu.data.colors.transformers

import com.nulp.graphedu.data.colors.PixelColor

interface ColorSchemeTransformer<T : PixelColor, U : PixelColor> {
    fun transform(from: T): U
}