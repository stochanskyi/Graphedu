package com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.navItems

import androidx.fragment.app.Fragment

abstract class NavigationItem {
    abstract val tag: String
    abstract val menuId:  Int

    abstract val fragment: Fragment
}