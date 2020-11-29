package com.nulp.graphedu.presentation.views.colorSelector

import android.graphics.Color
import com.nulp.graphedu.R

sealed class ColorSelectorLuminance(
    val textColor: Int,
    val rippleRes: Int
)

object ColorSelectorDark: ColorSelectorLuminance(
    Color.WHITE,
    R.drawable.ripple_color_selector_light
)

object ColorSelectorLight: ColorSelectorLuminance(
    Color.BLACK,
    R.drawable.ripple_color_selector_dark
)