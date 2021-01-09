package com.nulp.graphedu.app.koin

import com.nulp.graphedu.presentation.activities.main.MainContract
import com.nulp.graphedu.presentation.activities.main.impl.MainPresenter
import com.nulp.graphedu.presentation.dialogs.colorsSpaceSelection.ColorsSpaceSelectionContract
import com.nulp.graphedu.presentation.dialogs.colorsSpaceSelection.impl.ColorsSpaceSelectionPresenter
import com.nulp.graphedu.presentation.dialogs.createFractal.CreateFractalContract
import com.nulp.graphedu.presentation.dialogs.createFractal.impl.CreateFractalPresenter
import com.nulp.graphedu.presentation.dialogs.hexagonCreation.HexagonCreationContract
import com.nulp.graphedu.presentation.dialogs.hexagonCreation.impl.HexagonCreationPresenter
import com.nulp.graphedu.presentation.dialogs.tutorial.TutorialContract
import com.nulp.graphedu.presentation.dialogs.tutorial.impl.TutorialPresenter
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.ColorsSelectionContract
import com.nulp.graphedu.presentation.fragments.colors.colorsSelection.impl.ColorsSelectionPresenter
import com.nulp.graphedu.presentation.fragments.colors.colorsStart.ColorsStartContract
import com.nulp.graphedu.presentation.fragments.colors.colorsStart.impl.ColorsStartPresenter
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl.ImageEditPresenter
import com.nulp.graphedu.presentation.fragments.fractals.fractal.FractalContract
import com.nulp.graphedu.presentation.fragments.fractals.fractal.impl.FractalsPresenter
import com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.FractalsStartContract
import com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.impl.FractalsStartPresenter
import com.nulp.graphedu.presentation.fragments.handbook.container.HandbookContainerContract
import com.nulp.graphedu.presentation.fragments.handbook.container.impl.HandbookContainerPresenter
import com.nulp.graphedu.presentation.fragments.handbook.content.HandbookContentContract
import com.nulp.graphedu.presentation.fragments.handbook.content.impl.HandbookContentPresenter
import com.nulp.graphedu.presentation.fragments.mainNavigation.MainNavigationContract
import com.nulp.graphedu.presentation.fragments.mainNavigation.impl.MainNavigationPresenter
import com.nulp.graphedu.presentation.fragments.menu.MenuContract
import com.nulp.graphedu.presentation.fragments.menu.impl.MenuPresenter
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.HexagonRotationContract
import com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl.HexagonRotationPresenter
import com.nulp.graphedu.presentation.fragments.rotation.rotationStart.RotationStartContract
import com.nulp.graphedu.presentation.fragments.rotation.rotationStart.impl.RotationStartPresenter

object PresentationModule : KoinModule({

    // Activities
    factory { MainPresenter() as MainContract.PresenterContract }

    //Fragments
    factory { MenuPresenter() as MenuContract.PresenterContract }

    factory { FractalsStartPresenter() as FractalsStartContract.PresenterContract }

    factory { FractalsPresenter() as FractalContract.PresenterContract }

    factory { MainNavigationPresenter() as MainNavigationContract.PresenterContract }

    factory { ColorsStartPresenter() as ColorsStartContract.PresenterContract }

    factory { ImageEditPresenter(get()) as ImageEditContract.PresenterContract }

    factory { ColorsSelectionPresenter() as ColorsSelectionContract.PresenterContract }

    factory { RotationStartPresenter() as RotationStartContract.PresentationContract }

    factory { HexagonRotationPresenter(get()) as HexagonRotationContract.PresenterContract }

    //Dialogs
    factory { CreateFractalPresenter() as CreateFractalContract.PresenterContract }

    factory { ColorsSpaceSelectionPresenter() as ColorsSpaceSelectionContract.PresenterContract }

    factory { HexagonCreationPresenter() as HexagonCreationContract.PresenterContract }

    factory { TutorialPresenter() as TutorialContract.PresenterContract }

    factory { HandbookContainerPresenter() as HandbookContainerContract.PresenterContract }

    factory { HandbookContentPresenter() as HandbookContentContract.PresenterContract }
})