package com.nulp.graphedu.presentation.fragments.mainNavigation

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface MainNavigationContract {

    interface ViewContract : IBaseFragment {

    }

    interface PresenterContract : IBasePresenter<ViewContract> {

    }
}