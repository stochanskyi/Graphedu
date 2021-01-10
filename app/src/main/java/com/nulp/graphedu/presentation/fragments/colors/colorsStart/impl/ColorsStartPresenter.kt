package com.nulp.graphedu.presentation.fragments.colors.colorsStart.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.colors.colorsStart.ColorsStartContract.*

class ColorsStartPresenter : BasePresenter<ViewContract>(), PresenterContract {

    override fun pickImage() {
        view?.openImagePicker()
    }

    override fun openHandbook() {
        view?.openHandbook()
    }
}