package com.nulp.graphedu.presentation.common.mvp

import androidx.annotation.CallSuper

abstract class BasePresenter<T : IBaseView> : IBasePresenter<T> {
    override var view: T? = null

    @CallSuper
    override fun release() {
        view = null
    }

    @CallSuper
    override fun onStart() {
    }

    @CallSuper
    override fun onCreate() {
    }

    @CallSuper
    override fun onResume() {
    }

    @CallSuper
    override fun onPause() {
    }
}