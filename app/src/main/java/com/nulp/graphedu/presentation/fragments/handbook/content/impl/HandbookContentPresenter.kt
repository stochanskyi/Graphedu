package com.nulp.graphedu.presentation.fragments.handbook.content.impl

import com.nulp.graphedu.presentation.common.mvp.BasePresenter
import com.nulp.graphedu.presentation.fragments.handbook.content.HandbookContentContract
import com.nulp.graphedu.presentation.fragments.handbook.content.data.HandbookContent

class HandbookContentPresenter: BasePresenter<HandbookContentContract.ViewContract>(),
    HandbookContentContract.PresenterContract {

    private lateinit var content: HandbookContent

    override fun initContent(content: HandbookContent) {
        this.content = content
    }

    override fun onStart() {
        super.onStart()
        view?.setContent(content)
    }
}