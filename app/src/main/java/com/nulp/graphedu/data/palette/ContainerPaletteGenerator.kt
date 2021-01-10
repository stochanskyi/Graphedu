package com.nulp.graphedu.data.palette

import com.nulp.graphedu.data.colors.container.ColorContainerImpl
import com.nulp.graphedu.data.colors.container.ColorsContainer
import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.presentation.fragments.fractals.imageBoundsSelector.ImageBounds

class ContainerPaletteGenerator(
    private val container: ColorsContainer
) {

    fun generate(): ColorsContainer {
        val containerColors = container.getColors()
        val colors = mutableSetOf<PixelColor>()
        for (i in containerColors.indices) {
            val color = containerColors[i]
            colors += color
        }
        return ColorContainerImpl(colors.toTypedArray())
    }

    fun generate(width: Int, bounds: ImageBounds): ColorsContainer {
        val containerColors = container.getColors()
        val colors = mutableSetOf<PixelColor>()

        for (y in bounds.top..bounds.bottom) {
            for (x in bounds.left..bounds.right) {
                val i = (y - 1) * width + x
                colors.add(containerColors[i])
            }
        }
        return ColorContainerImpl(colors.toTypedArray())
    }
}