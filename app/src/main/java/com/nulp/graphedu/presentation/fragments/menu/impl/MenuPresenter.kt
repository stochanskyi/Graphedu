package com.nulp.graphedu.presentation.fragments.menu.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.menu.MenuContract.*

class MenuPresenter : BasePresenter<ViewContract>(), PresenterContract {

    override fun onStartClicked() {
        view?.openFractalsScreen()
    }

    override fun onAboutUsClicked() {
        view?.openAboutUs()
    }

    override fun onInfoClicked() {
        view?.openHandbook()
    }
}