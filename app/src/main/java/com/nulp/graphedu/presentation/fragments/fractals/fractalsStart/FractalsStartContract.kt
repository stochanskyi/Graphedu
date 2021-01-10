package com.nulp.graphedu.presentation.fragments.fractals.fractalsStart

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface FractalsStartContract {

    interface ViewContract: IBaseFragment {
        fun showFragmentCreationDialog()

        fun openHandbook()
    }

    interface PresenterContract: IBasePresenter<ViewContract> {
        fun createFractal()

        fun openHandbook()
    }
}