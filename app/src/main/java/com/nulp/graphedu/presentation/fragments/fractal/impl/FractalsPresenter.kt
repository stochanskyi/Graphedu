package com.nulp.graphedu.presentation.fragments.fractal.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.fractal.FractalContract.*

class FractalsPresenter : BasePresenter<ViewContract>(), PresenterContract {

    private var coefficient: Int = 0

    private lateinit var colors: List<Int>

    override fun init(coefficient: Int, colors: List<Int>) {
        this.coefficient = coefficient
        this.colors = colors
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