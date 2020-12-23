package com.nulp.graphedu.presentation.fragments.colors.colorsSelection.models

import androidx.annotation.ColorInt
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.ColorsSelectionContract

data class ColorViewModel(
    @ColorInt override val color: Int,
    override val name: String,
    override val selectedHandler: () -> Unit
) : ColorsSelectionContract.IColorViewModel