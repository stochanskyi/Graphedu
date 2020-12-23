package com.nulp.graphedu.presentation.dialogs.colorsSpaceSelection.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.dialogs.colorsSpaceSelection.ColorsSpaceSelectionContract.*

class ColorsSpaceSelectionPresenter: BasePresenter<ViewContract>(), PresenterContract {

    private lateinit var listener: ColorsSpaceSelectionParent

    override fun init(listener: ColorsSpaceSelectionParent) {
        this.listener = listener
    }

    override fun onRgbClicked() {
        listener.onRgbColorsSpaceSelected()
        view?.close()
    }

    override fun onHslClicked() {
        listener.onHslColorsSpaceSelected()
        view?.close()
    }
}