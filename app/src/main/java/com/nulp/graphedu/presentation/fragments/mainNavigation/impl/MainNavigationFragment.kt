package com.nulp.graphedu.presentation.fragments.mainNavigation.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.mainNavigation.MainNavigationContract.*
import com.nulp.graphedu.presentation.fragments.mainNavigation.navigationController.NavigationController
import kotlinx.android.synthetic.main.fragment_main_navigation.*
import org.koin.android.ext.android.inject

class MainNavigationFragment : BaseFragment<PresenterContract>(), ViewContract {

    companion object {
        fun newInstance(): MainNavigationFragment = MainNavigationFragment()
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationController(
            requireContext(),
            childFragmentManager,
            R.id.fragmentContainer
        ).also { it.setup(viewBottomNavigation) }
    }
}