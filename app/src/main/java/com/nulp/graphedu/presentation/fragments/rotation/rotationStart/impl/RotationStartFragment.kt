package com.nulp.graphedu.presentation.fragments.rotation.rotationStart.impl

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.rotation.rotationStart.RotationStartContract.*
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_rotation_start.*
import org.koin.android.ext.android.inject

class RotationStartFragment : BaseFragment<PresentationContract>(R.layout.fragment_rotation_start), ViewContract {

    companion object {
        fun newInstance(): RotationStartFragment = RotationStartFragment()
    }

    override val presenter: PresentationContract by inject()

    override fun initPresenter() {
        presenter.view = this
    }

    override fun initViews() {
        ToolbarConfigurator()
            .withNavigationButton(false)
            .setTitle(getString(R.string.rotation_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.onHandbookClicked() })
            .applyToToolbar(toolbar)
    }
}