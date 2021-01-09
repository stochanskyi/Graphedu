package com.nulp.graphedu.presentation.views.affine

interface RenderersContainer<T: AffineRenderer> {

    fun addRenderer(renderer: T)
    fun removeRenderer(renderer: T)

}