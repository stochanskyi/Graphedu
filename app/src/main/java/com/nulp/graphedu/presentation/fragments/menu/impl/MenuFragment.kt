package com.nulp.graphedu.presentation.fragments.menu.impl

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.fractals.fractalsStart.impl.FractalsStartFragment
import com.nulp.graphedu.presentation.fragments.mainNavigation.impl.MainNavigationFragment
import com.nulp.graphedu.presentation.fragments.menu.MenuContract.*
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.android.ext.android.inject

class MenuFragment : BaseFragment<PresenterContract>(R.layout.fragment_menu), ViewContract {

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
    }

    override fun openFractalsScreen() {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragmentContainer, MainNavigationFragment.newInstance())
            .commit()
    }
}