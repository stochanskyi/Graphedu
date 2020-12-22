package com.nulp.graphedu.data.colors.transformers

import com.nulp.graphedu.data.colors.HslPixelColor
import com.nulp.graphedu.data.colors.RgbPixelColor
import kotlin.math.roundToInt

object RgbToHslTransformer : ColorSchemeTransformer<RgbPixelColor, HslPixelColor> {

    override fun transform(from: RgbPixelColor): HslPixelColor {
        val r = from.r / 255.0
        val g = from.g / 255.0
        val b = from.b / 255.0

        val min = minOf(r, g, b)
        val max = maxOf(r, g, b)
        val delta = max - min

        var h = when {
            max == min -> 0.0
            r == max -> (g - b) / delta
            g == max -> 2 + (b - r) / delta
            b == max -> 4 + (r - g) / delta
            else -> 0.0
        }
        h = minOf(h * 60, 360.0)
        if (h < 0) h += 360

        val l = (min + max) / 2.0

        val s = when {
            max == min -> 0.0
            l <= 0.5 -> delta / (max + min)
            else -> delta / (2 - max - min)
        }

        return HslPixelColor(h.roundToInt(), (s * 100).roundToInt(), (l * 100).roundToInt())
    }
}