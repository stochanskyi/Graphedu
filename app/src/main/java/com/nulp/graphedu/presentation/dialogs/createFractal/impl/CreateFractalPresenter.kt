package com.nulp.graphedu.presentation.dialogs.createFractal.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract.*

class CreateFractalPresenter : BasePresenter<ViewContract>(), PresenterContract {

    companion object {
        private const val FIRST_COEFFICIENT_VALUE = 3
        private const val SECOND_COEFFICIENT_VALUE = 4
    }

    private var coefficient = FIRST_COEFFICIENT_VALUE

    override fun onStart() {
        super.onStart()
        view?.setFirstCoefficientSelected()
    }

    override fun onFirstCoefficientSelected() {
        coefficient = FIRST_COEFFICIENT_VALUE
        view?.setFirstCoefficientSelected()
    }

    override fun onSecondCoefficientSelected() {
        coefficient = SECOND_COEFFICIENT_VALUE
        view?.setSecondCoefficientSelected()
    }

}