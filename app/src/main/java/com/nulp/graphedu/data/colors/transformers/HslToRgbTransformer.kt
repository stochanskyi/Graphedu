package com.nulp.graphedu.data.colors.transformers

import com.nulp.graphedu.data.colors.entity.HslPixelColor
import com.nulp.graphedu.data.colors.entity.RgbPixelColor
import kotlin.math.roundToInt

object HslToRgbTransformer : ColorSchemeTransformer<HslPixelColor, RgbPixelColor> {

    override fun transform(from: HslPixelColor): RgbPixelColor {
        val h = from.h / 360.0
        val s = from.s / 100.0
        val l = from.l / 100.0
        if (s == 0.0) {
            val v = (l * 255).roundToInt()
            return RgbPixelColor(v, v, v)
        }

        val t2 = if (l < 0.5) l * (1 + s)
        else l + s - l * s

        val t1 = 2 * l - t2

        val rgb = arrayOf(0.0, 0.0, 0.0)
        for (i in 0..2) {
            var t3: Double = h + 1.0 / 3.0 * -(i - 1.0)
            if (t3 < 0) t3 += 1.0
            if (t3 > 1) t3 -= 1.0

            val v: Double = when {
                6 * t3 < 1 -> t1 + (t2 - t1) * 6 * t3
                2 * t3 < 1 -> t2
                3 * t3 < 2 -> t1 + (t2 - t1) * (2.0 / 3.0 - t3) * 6
                else -> t1
            }

            rgb[i] = v
        }

        for (i in rgb.indices) {
            rgb[i] = rgb[i] * 255
        }

        return RgbPixelColor(rgb[0].toInt(), rgb[1].toInt(), rgb[2].toInt())
    }
}