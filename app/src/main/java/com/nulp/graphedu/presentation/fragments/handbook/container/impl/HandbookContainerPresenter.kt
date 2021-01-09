package com.nulp.graphedu.presentation.fragments.handbook.container.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.handbook.container.HandbookContainerContract
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTab
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabColors
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabFractals
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabRotation

class HandbookContainerPresenter: BasePresenter<HandbookContainerContract.ViewContract>(),
    HandbookContainerContract.PresenterContract {

    private lateinit var currentTab: HandbookTab

    override fun initDefaultTab(tab: HandbookTab) {
        currentTab = tab
    }

    override fun onStart() {
        super.onStart()
        view?.setTab(currentTab)
    }

    override fun openFractalsTab() {
        setAndOpenTab(HandbookTabFractals)
    }

    override fun openColorsTab() {
        setAndOpenTab(HandbookTabColors)
    }

    override fun openRotationTab() {
        setAndOpenTab(HandbookTabRotation)
    }

    private fun setAndOpenTab(tab: HandbookTab) {
        currentTab = tab
        view?.setTab(tab)
    }
}