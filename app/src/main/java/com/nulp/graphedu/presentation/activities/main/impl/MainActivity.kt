package com.nulp.graphedu.presentation.activities.main.impl

import android.os.Bundle
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.activities.main.MainContract.*
import com.nulp.graphedu.presentation.common.mvp.BaseActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<PresenterContract>(), ViewContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override val presenter: PresenterContract by inject()

    override fun onInitPresenter() {
        presenter.view = this
    }

    private fun initViews() {

    }

}