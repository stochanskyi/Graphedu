package com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.navItems

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.impl.FractalsStartFragment

class FractalsNavigationItem : NavigationItem() {
    override val tag: String = "fragment_nav_fractals"

    override val menuId: Int = R.id.tabFractals

    override val fragment: FractalsStartFragment by lazy { FractalsStartFragment.newInstance() }

}