package com.nulp.graphedu.presentation.fragments.handbook.container.impl

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.handbook.container.HandbookContainerContract
import com.nulp.graphedu.presentation.fragments.handbook.container.adapter.HandbookTabAdapter
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTab
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabColors
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabFractals
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabRotation
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_handbook_container.*
import org.koin.android.ext.android.inject

class HandbookContainerFragment : BaseFragment<HandbookContainerContract.PresenterContract>(
    R.layout.fragment_handbook_container
), HandbookContainerContract.ViewContract {

    companion object {
        private const val DEFAULT_TAB_KEY = "key_default_tab"

        fun newInstance(defaultTab: HandbookTab? = null): HandbookContainerFragment {
            return HandbookContainerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DEFAULT_TAB_KEY, defaultTab)
                }
            }
        }
    }

    private val tabs = listOf(
        HandbookTabFractals,
        HandbookTabColors,
        HandbookTabRotation
    )

    override val presenter: HandbookContainerContract.PresenterContract by inject()

    override fun initPresenter() {
        val defaultTab: HandbookTab? = requireArguments().getParcelable(DEFAULT_TAB_KEY)
        presenter.initDefaultTab(defaultTab ?: HandbookTabFractals)
        presenter.view = this
    }

    override fun initViews() {
        ToolbarConfigurator()
            .withNavigationButton(true)
            .setNavigationClickListener { close() }
            .setTitle(getString(R.string.title_handbook))
            .applyToToolbar(toolbar)

        val adapter = HandbookTabAdapter(this, tabs)
        viewPager.adapter = adapter
        TabLayoutMediator(
            tabLayout,
            viewPager,
            adapter.resolveTabConfigurationStrategy()
        ).attach()
    }

    override fun setTab(tab: HandbookTab) {
        viewPager.currentItem = tabs.indexOf(tab)
    }
}