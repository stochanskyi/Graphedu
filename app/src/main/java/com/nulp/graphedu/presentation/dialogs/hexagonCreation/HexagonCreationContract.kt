package com.nulp.graphedu.presentation.dialogs.hexagonCreation

import com.nulp.graphedu.presentation.common.mvp.IBaseDialog
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

class HexagonCreationContract {
    interface ViewContract: IBaseDialog {
        fun setXHint(hint: String)
        fun setYHint(hint: String)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {

        fun init(listener: HexagonCreationParent)

        fun onXChanged(x: String)
        fun onYChanged(y: String)

        fun onConfirmClicked()
    }

    interface HexagonCreationParent {
        fun onHexagonCoordinatesSelected(x: Float, y: Float)
    }
}