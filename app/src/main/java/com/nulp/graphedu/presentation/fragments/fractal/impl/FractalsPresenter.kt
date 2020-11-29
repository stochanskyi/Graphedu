package com.nulp.graphedu.presentation.fragments.fractal.impl

import com.nulp.graphedu.data.definition.DefinedNewtonFractalParams
import com.nulp.graphedu.data.definition.FractalParams
import com.nulp.graphedu.data.generator.NewtonFractalBuilder
import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.fractal.FractalContract

class FractalsPresenter : BasePresenter<FractalContract.ViewContract>(),
    FractalContract.PresenterContract {

    companion object {
        private const val DOWNSCALE = 2
    }

    private var coefficient: Int = 0
    private lateinit var colors: IntArray

    override fun init(coefficient: Int, colors: List<Int>) {
        this.coefficient = coefficient
        this.colors = colors.toIntArray()
    }

    override fun onReadyToDraw(width: Int, height: Int) {
        val params: FractalParams = when (coefficient) {
            3 -> DefinedNewtonFractalParams.K3
            4 -> DefinedNewtonFractalParams.K4
            else -> throw IllegalStateException("Unsupported coefficient $coefficient")
        }

        val builder = NewtonFractalBuilder(
            params.polynomial,
            width / DOWNSCALE, height / DOWNSCALE
        )
            .apply { params.builderApplier(this) }
            .setColors(colors)

        view?.loadFractal(builder)
    }

    override fun onHandbookClicked() {
        //TODO
    }

    override fun onZoomUpClicked() {
        //TODO
    }

    override fun onZoomDownClicked() {
        //TODO
    }
}