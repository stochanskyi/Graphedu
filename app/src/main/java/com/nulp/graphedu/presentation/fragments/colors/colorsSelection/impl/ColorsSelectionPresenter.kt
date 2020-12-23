package com.nulp.graphedu.presentation.fragments.colors.colorsSelection.impl

import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.data.colors.utils.toAndroidColor
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.ColorsSelectionContract.*
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.models.ColorViewModel

class ColorsSelectionPresenter : BasePresenter<ViewContract>(), PresenterContract {
    private lateinit var listener: ColorsSelectionParent
    private lateinit var pixelColors: Array<PixelColor>

    override fun init(listener: ColorsSelectionParent, pixelColors: Array<PixelColor>) {
        this.listener = listener
        this.pixelColors = pixelColors
    }

    override fun onStart() {
        super.onStart()

        view?.setColors(pixelColors.map { it.toViewModel() })
    }

    private fun PixelColor.toViewModel(): IColorViewModel {
        return ColorViewModel(
            toAndroidColor(),
            getFormattedString()
        ) { listener.onColorSelected(this) }
    }
}