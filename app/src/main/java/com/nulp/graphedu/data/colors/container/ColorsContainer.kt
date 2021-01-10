package com.nulp.graphedu.data.colors.container

import com.nulp.graphedu.data.colors.entity.ColorTransformable
import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.presentation.fragments.fractals.imageBoundsSelector.ImageBounds

typealias ColorTransformation = ColorTransformable.() -> PixelColor

interface   ColorsContainer {

    fun getColors(): Array<PixelColor>

    fun changeColor(from: PixelColor, to: PixelColor, width: Int, bounds: ImageBounds)

    fun transform(transformation: ColorTransformation): ColorsContainer
}