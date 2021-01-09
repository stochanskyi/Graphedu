package com.nulp.graphedu.presentation.fragments.handbook.content.impl

import android.os.Bundle
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.handbook.content.HandbookContentContract
import com.nulp.graphedu.presentation.fragments.handbook.content.data.HandbookContent
import org.koin.android.ext.android.inject

class HandbookContentFragment: BaseFragment<HandbookContentContract.PresenterContract>(
    R.layout.fragment_handbook_content
), HandbookContentContract.ViewContract {

    companion object {
        private const val CONTENT_KEY = "key_content"

        fun newInstance(content: HandbookContent): HandbookContentFragment {
            return HandbookContentFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CONTENT_KEY, content)
                }
            }
        }
    }

    override val presenter: HandbookContentContract.PresenterContract by inject()

    override fun initPresenter() {
        val content: HandbookContent = requireArguments().getParcelable(CONTENT_KEY)!!
        presenter.initContent(content)
    }

    override fun setContent(content: HandbookContent) {
        // TODO show content items
    }

}