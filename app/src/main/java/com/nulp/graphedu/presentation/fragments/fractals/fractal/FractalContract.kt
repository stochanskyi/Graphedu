package com.nulp.graphedu.presentation.fragments.fractals.fractal

import com.nulp.graphedu.data.fractals.generator.FractalResult
import com.nulp.graphedu.data.fractals.generator.NewtonFractalBuilder
import com.nulp.graphedu.data.fractals.generator.NewtonFractalGenerator
import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface FractalContract {

    interface ViewContract : IBaseFragment {
        fun requestDrawFractalWhenReady()

        fun prepareGenerator(builder: NewtonFractalBuilder)

        fun setFractalLoadingVisible(isVisible: Boolean)
        fun setFractalLoadingProgress(progress: Float)

        fun showFractalResult(result: FractalResult)
        fun showCachedFractalResult(result: FractalResult, scaleChange: Float)

        fun scaleCurrentFractalImage(scale: Float)

        fun openHandbook()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(coefficient: Int, c: Float, colors: List<Int>)

        fun handleSizeChanged(width: Int, height: Int)
        fun generateFractal(generator: NewtonFractalGenerator)

        fun openHandbook()

        fun scaleFractalUp()
        fun scaleFractalDown()
    }
}