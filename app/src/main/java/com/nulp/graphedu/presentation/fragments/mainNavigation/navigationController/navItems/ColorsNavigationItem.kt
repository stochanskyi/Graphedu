package com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.navItems

import androidx.fragment.app.Fragment
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.fragments.colors.colorsStart.impl.ColorsStartFragment

class ColorsNavigationItem : NavigationItem() {

    override val tag: String = "fragment_nav_colors"

    override val menuId: Int = R.id.tabColors

    override val fragment: ColorsStartFragment by lazy { ColorsStartFragment.newInstance() }

}