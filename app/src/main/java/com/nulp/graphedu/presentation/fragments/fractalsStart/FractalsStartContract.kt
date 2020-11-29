package com.nulp.graphedu.presentation.fragments.fractalsStart

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface FractalsStartContract {

    interface ViewContract: IBaseFragment {

    }

    interface PresenterContract: IBasePresenter<ViewContract> {
        fun onHandbookClicked()
    }
}