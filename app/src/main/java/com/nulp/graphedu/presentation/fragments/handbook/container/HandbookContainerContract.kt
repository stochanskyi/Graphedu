package com.nulp.graphedu.presentation.fragments.handbook.container

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTab

interface HandbookContainerContract {

    interface ViewContract: IBaseFragment {
        fun setTab(tab: HandbookTab)
    }

    interface PresenterContract: IBasePresenter<ViewContract> {
        fun initDefaultTab(tab: HandbookTab)

        fun openFractalsTab()
        fun openColorsTab()
        fun openRotationTab()
    }

}