package com.nulp.graphedu.presentation.fragments.rotation.rotationStart.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.rotation.rotationStart.RotationStartContract.*

class RotationStartPresenter : BasePresenter<ViewContract>(), PresentationContract {

    override fun onHandbookClicked() {
        //TODO
    }

    override fun onCreateClicked() {
        view?.showHexagonCreationFragment()
    }

    override fun onCoordinatesSelected(x: Float, y: Float) {
        view?.openRotationScreen(x, y)
    }
}