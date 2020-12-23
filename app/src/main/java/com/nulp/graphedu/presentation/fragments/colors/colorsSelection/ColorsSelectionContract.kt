package com.nulp.graphedu.presentation.fragments.colors.colorsSelection

import com.nulp.graphedu.data.colors.entity.PixelColor
import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface ColorsSelectionContract {

    interface ViewContract : IBaseFragment {
        fun setColors(items: List<IColorViewModel>)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(listener: ColorsSelectionParent, pixelColors: Array<PixelColor>)
    }

    interface IColorViewModel {
        val color: Int
        val name: String
        val selectedHandler: () -> Unit
    }

    interface ColorsSelectionParent {
        fun onColorSelected(color: PixelColor)
    }
}