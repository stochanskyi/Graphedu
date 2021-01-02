package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface HexagonRotationContract {

    interface ViewContract : IBaseFragment {
        fun setRotateActionVisible(isVisible: Boolean, animate: Boolean = false)

        fun showTutorialDialog()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun init(x: Float, y: Float)

        fun onRotateClicked()

        fun onTutorialCompleted()

        fun onHandbookClicked()
    }
}