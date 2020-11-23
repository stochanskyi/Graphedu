package com.nulp.graphedu.presentation.common.mvp

interface IBasePresenter<T : IBaseView> {
    var view: T?

    fun onCreate() {}
    fun onStart() {}
    fun onResume() {}
    fun onPause() {}

    fun release()
}