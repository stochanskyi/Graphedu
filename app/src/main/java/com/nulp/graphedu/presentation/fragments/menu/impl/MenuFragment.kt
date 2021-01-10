package com.nulp.graphedu.presentation.fragments.menu.impl

import androidx.fragment.app.Fragment
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.handbook.container.impl.HandbookContainerFragment
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTab
import com.nulp.graphedu.presentation.fragments.mainNavigation.impl.MainNavigationFragment
import com.nulp.graphedu.presentation.fragments.menu.HandbookContainer
import com.nulp.graphedu.presentation.fragments.menu.MenuContract.*
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.android.ext.android.inject

class MenuFragment : BaseFragment<PresenterContract>(R.layout.fragment_menu), ViewContract,
    HandbookContainer {

    companion object {
        fun newInstance() : MenuFragment {
            return MenuFragment()
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
    }

    override fun initViews() {
        textStart.setOnClickListener { presenter.onStartClicked() }
        layoutAboutUs.setOnClickListener { presenter.onAboutUsClicked() }
        layoutInfo.setOnClickListener { presenter.onInfoClicked() }
    }

    override fun openFractalsScreen() {
        openScreen(MainNavigationFragment.newInstance(), null)
    }

    override fun openAboutUs() {
        // TODO
    }

    override fun openHandbook() {
        openScreen(HandbookContainerFragment.newInstance(), null)
    }

    override fun requestOpenHandbook(tab: HandbookTab) {
        openScreen(HandbookContainerFragment.newInstance(tab), null)
    }

    private fun openScreen(fragment: Fragment, tag: String?) {
        childFragmentManager.beginTransaction()
            .addToBackStack(tag)
            .add(R.id.fragmentContainer, fragment, tag)
            .commit()
    }
}