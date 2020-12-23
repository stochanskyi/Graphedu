package com.nulp.graphedu.data.palette

import com.nulp.graphedu.data.colors.container.ColorContainerImpl
import com.nulp.graphedu.data.colors.container.ColorsContainer
import com.nulp.graphedu.data.colors.entity.PixelColor

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
}