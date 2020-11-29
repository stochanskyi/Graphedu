package com.nulp.graphedu.presentation.views.toolbarConfigurator

import androidx.appcompat.widget.Toolbar
import com.nulp.graphedu.R

class ToolbarConfigurator {

    private var useNavigationButton = true

    private var navigationButtonDrawableId: Int = R.drawable.ic_back

    private var title = ""

    private var menuId: Int = -1

    private val clickableMenuItems: MutableList<ClickableMenuItem> = mutableListOf()

    fun withNavigationButton(withNavigation: Boolean): ToolbarConfigurator {
        useNavigationButton = withNavigation
        return this
    }

    fun setNavigationButtonDrawableId(drawable: Int): ToolbarConfigurator {
        navigationButtonDrawableId = drawable
        return this
    }

    fun setTitle(title: String): ToolbarConfigurator {
        this.title = title
        return this
    }

    fun setMenuId(menuId: Int): ToolbarConfigurator {
        this.menuId = menuId
        return this
    }

    fun setClickableMenuItems(items: List<ClickableMenuItem>): ToolbarConfigurator {
        clickableMenuItems.clear()
        clickableMenuItems.addAll(items)
        return this
    }

    fun addClickableItem(item : ClickableMenuItem) : ToolbarConfigurator {
        clickableMenuItems += item
        return this
    }

    fun applyToToolbar(toolbar: Toolbar) {

        if (useNavigationButton) {
            toolbar.setNavigationIcon(navigationButtonDrawableId)
        }

        if (title.isNotBlank()) {
            toolbar.title = title
        }

        if (menuId != -1) {
            toolbar.inflateMenu(menuId)
        }

        val menu = toolbar.menu
        clickableMenuItems.forEach { item ->
            menu.findItem(item.menuItemId).setOnMenuItemClickListener {
                item.onClickBlock(it.itemId)
                true
            }
        }
    }
}