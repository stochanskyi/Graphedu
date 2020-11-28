package com.nulp.graphedu.presentation.fragments.menuFragment.impl

import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
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
        textStart.setOnClickListener {
            findNavController().navigate(R.id.action_start_to_tabs)
        }
    }
}