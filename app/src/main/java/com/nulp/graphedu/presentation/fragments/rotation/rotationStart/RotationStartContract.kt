package com.nulp.graphedu.presentation.fragments.rotation.rotationStart

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface RotationStartContract {

    interface ViewContract : IBaseFragment

    interface PresentationContract : IBasePresenter<ViewContract> {
        fun onHandbookClicked()
    }
}