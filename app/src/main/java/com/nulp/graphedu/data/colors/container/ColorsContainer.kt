package com.nulp.graphedu.data.colors.container

import android.os.Parcelable
import com.nulp.graphedu.data.colors.entity.ColorTransformable
import com.nulp.graphedu.data.colors.entity.PixelColor

typealias ColorTransformation = ColorTransformable.() -> PixelColor

interface ColorsContainer {

    fun getColors(): Array<PixelColor>

    fun changeColor(from: PixelColor, to: PixelColor)

    fun transform(transformation: ColorTransformation): ColorsContainer
}