package com.nulp.graphedu.presentation.fragments.menuFragment

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface MenuContract {

    interface ViewContract : IBaseFragment {

    }

    interface PresenterContract : IBasePresenter<ViewContract> {
         fun onInfoClicked()
         fun onAboutUsClicked()
    }
}