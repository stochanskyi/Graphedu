package com.nulp.graphedu.data.colors.container

import com.nulp.graphedu.data.colors.entity.PixelColor

class ColorContainerImpl(
    private val colors: Array<PixelColor>
) : ColorsContainer {

    override fun getColors(): Array<PixelColor> {
        return colors
    }

    override fun changeColor(from: PixelColor, to: PixelColor) {
        if (colors.isEmpty()) return
        val anyTargetColor = colors.first()

        val typedFrom = anyTargetColor.colorOfSameType(from)
        val typedTo = anyTargetColor.colorOfSameType(to)

        for (i in colors.indices) {
            if (colors[i] == typedFrom) {
                colors[i] = typedTo
            }
        }
    }

    override fun transform(transformation: ColorTransformation): ColorsContainer {
        val newColors = Array(colors.size) { i ->
            colors[i].transformation()
        }
        return ColorContainerImpl(newColors)
    }
}