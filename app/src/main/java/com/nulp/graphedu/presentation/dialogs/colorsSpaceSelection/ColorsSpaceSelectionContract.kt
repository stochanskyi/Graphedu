package com.nulp.graphedu.presentation.dialogs.colorsSpaceSelection

import com.nulp.graphedu.presentation.common.mvp.IBaseDialog
import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface ColorsSpaceSelectionContract {

    interface ViewContract : IBaseDialog {

    }

    interface PresenterContract: IBasePresenter<ViewContract> {
        fun init(listener: ColorsSpaceSelectionParent)

        fun onRgbClicked()
        fun onHslClicked()
    }

    interface ColorsSpaceSelectionParent {
        fun onHslColorsSpaceSelected()
        fun onRgbColorsSpaceSelected()
    }
}