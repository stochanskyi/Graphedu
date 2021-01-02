package com.nulp.graphedu.data.colors.container

import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.presentation.fragments.fractals.imageBoundsSelector.ImageBounds

class ColorContainerImpl(
    private val colors: Array<PixelColor>
) : ColorsContainer {

    override fun getColors(): Array<PixelColor> {
        return colors
    }

    override fun changeColor(from: PixelColor, to: PixelColor, width: Int, bounds: ImageBounds) {
        if (colors.isEmpty()) return

        val anyTargetColor = colors.first()

        val typedFrom = anyTargetColor.colorOfSameType(from)
        val typedTo = anyTargetColor.colorOfSameType(to)

        for (y in bounds.top..bounds.bottom) {
            for (x in bounds.left..bounds.right) {
                val i = (y - 1) * width + x
                if (colors[i] == typedFrom) {
                    colors[i] = typedTo
                }
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