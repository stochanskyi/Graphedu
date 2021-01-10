package com.nulp.graphedu.presentation.fragments.rotation.rotationStart

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface RotationStartContract {

    interface ViewContract : IBaseFragment {
        fun showHexagonCreationFragment()

        fun openRotationScreen(x: Float, y: Float, radius: Float)

        fun openHandbook()
    }

    interface PresentationContract : IBasePresenter<ViewContract> {
        fun createHexagon()
        fun setHexagonParams(x: Float, y: Float, radius: Float)

        fun openHandbook()
    }
}