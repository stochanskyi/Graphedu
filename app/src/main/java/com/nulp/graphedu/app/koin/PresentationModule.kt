package com.nulp.graphedu.app.koin

import com.nulp.graphedu.presentation.activities.main.MainContract
import com.nulp.graphedu.presentation.activities.main.impl.MainPresenter

object PresentationModule : KoinModule({
    factory { MainPresenter() as MainContract.PresenterContract }
})