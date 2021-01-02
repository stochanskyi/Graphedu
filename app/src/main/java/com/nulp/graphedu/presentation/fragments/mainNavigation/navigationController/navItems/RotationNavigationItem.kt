package com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.navItems

import androidx.fragment.app.Fragment
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.rotation.rotationStart.impl.RotationStartFragment

class RotationNavigationItem : NavigationItem() {
    override val tag: String = "fragment_nav_rotation"

    override val menuId: Int = R.id.tabRotation

    override val fragment: Fragment by lazy { RotationStartFragment.newInstance() }
}