package com.nulp.graphedu.presentation.fragments.rotation.rotationStart.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.rotation.rotationStart.RotationStartContract.*

class RotationStartPresenter : BasePresenter<ViewContract>(), PresentationContract {

    override fun createHexagon() {
        view?.showHexagonCreationFragment()
    }

    override fun setHexagonParams(x: Float, y: Float) {
        view?.openRotationScreen(x, y)
    }

    override fun openHandbook() {
        view?.openHandbook()
    }
}