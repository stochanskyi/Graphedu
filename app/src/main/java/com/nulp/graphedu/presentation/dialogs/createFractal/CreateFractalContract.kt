package com.nulp.graphedu.presentation.dialogs.createFractal

import com.nulp.graphedu.presentation.common.mvp.IBaseDialog
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface CreateFractalContract {

    interface ViewContract : IBaseDialog {
        fun setFirstCoefficientSelected()
        fun setSecondCoefficientSelected()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun onFirstCoefficientSelected()
        fun onSecondCoefficientSelected()
    }

}