package com.nulp.graphedu.presentation.activities.main.impl

import android.os.Bundle
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.activities.main.MainContract.*
import com.nulp.graphedu.presentation.common.mvp.BaseActivity
import com.nulp.graphedu.presentation.fragments.menu.impl.MenuFragment
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<PresenterContract>(), ViewContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Graphedu)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openMenuScreen()
    }

    override val presenter: PresenterContract by inject()

    override fun onInitPresenter() {
        presenter.view = this
    }

    private fun openMenuScreen() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, MenuFragment.newInstance())
            .commit()
    }

}