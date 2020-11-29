package com.nulp.graphedu.presentation.fragments.menuFragment.impl

import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.fractalsStart.impl.FractalsStartFragment
import com.nulp.graphedu.presentation.fragments.menuFragment.MenuContract.*
import kotlinx.android.synthetic.main.fragment_menu.*
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
        textStart.setOnClickListener { presenter.onStartClicked() }
    }

    override fun openFractalsScreen() {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragmentContainer, FractalsStartFragment.newInstance())
            .commit()
    }
}