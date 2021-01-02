package com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.navItems.ColorsNavigationItem
import com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.navItems.FractalsNavigationItem
import com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.navItems.NavigationItem

class NavigationController(
    private val context: Context,
    private val fragmentManager: FragmentManager,
    fragmentContainerId: Int
) {

    private val items: List<NavigationItem> = listOf(
        FractalsNavigationItem(),
        ColorsNavigationItem()
    )

    init {
        val transaction = fragmentManager.beginTransaction()

        items.forEach { item ->
            transaction.add(fragmentContainerId, item.fragment)
            transaction.hide(item.fragment)
        }

        transaction.commitNow()
    }

    private var currentItem: NavigationItem? = null

    fun setup(bottomView: BottomNavigationView) {
        bottomView.setOnNavigationItemSelectedListener { item ->
            items.firstOrNull { it.menuId == item.itemId }?.let {
                onItemSelected(it)
                true
            } ?: false
        }
        bottomView.selectedItemId = items.first().menuId
    }

    private fun onItemSelected(item: NavigationItem) {
        val transaction = fragmentManager.beginTransaction()
        currentItem?.let {
            transaction.hideItem(it)
        }

        currentItem = item
        transaction.apply {
            showItem(item)
            commitNow()
        }
    }

    private fun FragmentTransaction.showItem(item: NavigationItem) {
        show(item.fragment)
    }


    private fun FragmentTransaction.hideItem(item: NavigationItem) {
        hide(item.fragment)
    }
}