package com.nulp.graphedu.presentation.fragments.fractal

import com.nulp.graphedu.data.generator.FractalResult
import com.nulp.graphedu.data.generator.NewtonFractalBuilder
import com.nulp.graphedu.data.generator.NewtonFractalGenerator
import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface FractalContract {

    interface ViewContract : IBaseFragment {
        fun requestDrawFractalWhenReady()

        fun prepareGenerator(builder: NewtonFractalBuilder)

        fun setFractalLoadingVisible(isVisible: Boolean)
        fun setFractalLoadingProgress(progress: Float)

        fun handleFractalResult(result: FractalResult)
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(coefficient: Int, colors: List<Int>)

        fun onReadyToDraw(width: Int, height: Int)
        fun generateFractal(generator: NewtonFractalGenerator)

        fun onHandbookClicked()

        fun onZoomUpClicked()
        fun onZoomDownClicked()
    }
}