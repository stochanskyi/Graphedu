package com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.navItems

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.affine.affineStart.AffineStartFragment

class AffineNavigationItem : NavigationItem() {

    override val tag: String = "fragment_nav_rotation"

    override val menuId: Int = R.id.tabRotation

    override val fragment: AffineStartFragment by lazy { AffineStartFragment.newInstance() }

}