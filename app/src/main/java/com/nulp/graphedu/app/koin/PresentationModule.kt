package com.nulp.graphedu.app.koin

import com.nulp.graphedu.presentation.activities.main.MainContract
import com.nulp.graphedu.presentation.activities.main.impl.MainPresenter
import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract
import com.nulp.graphedu.presentation.dialogs.createFractal.impl.CreateFractalPresenter
import com.nulp.graphedu.presentation.fragments.fractalsStart.FractalsStartContract
import com.nulp.graphedu.presentation.fragments.fractalsStart.impl.FractalsStartPresenter
import com.nulp.graphedu.presentation.fragments.menuFragment.MenuContract
import com.nulp.graphedu.presentation.fragments.menuFragment.impl.MenuPresenter

object PresentationModule : KoinModule({

    // Activities
    factory { MainPresenter() as MainContract.PresenterContract }

    //Fragments
    factory { MenuPresenter() as MenuContract.PresenterContract }

    factory { FractalsStartPresenter() as FractalsStartContract.PresenterContract }

    //Dialogs
    factory { CreateFractalPresenter() as CreateFractalContract.PresenterContract }
})