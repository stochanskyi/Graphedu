package com.nulp.graphedu.presentation.fragments.menu

import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTab

interface HandbookContainer {

    fun requestOpenHandbook(tab: HandbookTab)

}