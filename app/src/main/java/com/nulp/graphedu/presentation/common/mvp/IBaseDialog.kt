package com.nulp.graphedu.presentation.common.mvp

interface IBaseDialog: IBaseView {
    val dialogTag: String?

    fun close()
}