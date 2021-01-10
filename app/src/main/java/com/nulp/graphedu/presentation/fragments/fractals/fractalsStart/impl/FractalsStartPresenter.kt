package com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.FractalsStartContract.*

class FractalsStartPresenter : BasePresenter<ViewContract>(), PresenterContract {

    override fun createFractal() {
        view?.showFragmentCreationDialog()
    }

    override fun openHandbook() {
        view?.openHandbook()
    }

}