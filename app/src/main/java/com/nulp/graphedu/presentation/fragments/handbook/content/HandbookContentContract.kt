package com.nulp.graphedu.presentation.fragments.handbook.content

import com.nulp.graphedu.presentation.common.mvp.IBaseFragment
import com.nulp.graphedu.presentation.common.mvp.IBasePresenter
import com.nulp.graphedu.presentation.fragments.handbook.content.data.HandbookContent

interface HandbookContentContract {

    interface ViewContract: IBaseFragment {
        fun setContent(content: HandbookContent)
    }

    interface PresenterContract: IBasePresenter<ViewContract> {
        fun initContent(content: HandbookContent)
    }

}