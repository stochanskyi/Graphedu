package com.nulp.graphedu.presentation.views.toolbarConfigurator

data class ClickableMenuItem(
    val menuItemId: Int,
    val onClickBlock: (menuItemId: Int) -> Unit
)