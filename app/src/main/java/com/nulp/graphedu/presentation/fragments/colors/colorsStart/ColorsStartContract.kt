package com.nulp.graphedu.presentation.fragments.colors.colorsStart

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface ColorsStartContract {

    interface ViewContract : IBaseFragment {
        fun openImagePicker()

        fun openHandbook()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun pickImage()

        fun openHandbook()
    }
}