package com.nulp.graphedu.presentation.common.mvp

import androidx.fragment.app.DialogFragment

class BaseDialog : DialogFragment(), IBaseDialog {
    override val dialogTag: String?
        get() = tag

    override fun close() {

    }

    override fun handleError(e: Throwable) {
        //TODO errors handling
    }
}