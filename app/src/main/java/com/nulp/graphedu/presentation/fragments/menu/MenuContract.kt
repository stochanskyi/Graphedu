package com.nulp.graphedu.presentation.fragments.menu

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface MenuContract {

    interface ViewContract : IBaseFragment {
        fun openFractalsScreen()
    }

    interface PresenterContract : IBasePresenter<ViewContract> {
        fun onStartClicked()

        fun onInfoClicked()
        fun onAboutUsClicked()
    }
}