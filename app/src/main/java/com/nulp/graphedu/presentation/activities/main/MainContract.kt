package com.nulp.graphedu.presentation.activities.main

import com.nulp.graphedu.presentation.common.mvp.IBaseActivity
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter

interface MainContract {

    interface ViewContract: IBaseActivity

    interface PresenterContract : IBasePresenter<ViewContract>

}