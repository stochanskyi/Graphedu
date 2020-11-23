package com.nulp.graphedu.presentation.fragments.menuFragment.impl

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.menuFragment.MenuContract.*
import org.koin.android.ext.android.inject

class MenuFragment : BaseFragment<PresenterContract>(R.layout.fragment_menu), ViewContract {

    companion object {
        fun newInstance() : MenuFragment {
            return MenuFragment()
        }
    }

    override val presenter: PresenterContract by inject()

    override fun onInitPresenter() {
        presenter.view = this
    }

    override fun initViews() {
        //TODO
    }
}