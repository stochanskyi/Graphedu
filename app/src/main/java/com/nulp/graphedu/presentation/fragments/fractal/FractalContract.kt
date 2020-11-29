package com.nulp.graphedu.presentation.fragments.fractal

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface FractalContract {

    interface ViewContract : IBaseFragment {

    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(coefficient: Int, colors: List<Int>)

        fun onHandbookClicked()

        fun onZoomUpClicked()
        fun onZoomDownClicked()
    }
}