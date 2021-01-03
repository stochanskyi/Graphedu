package com.nulp.graphedu.presentation.views.affine

import android.graphics.Canvas

abstract class ComplexRenderer: BaseRenderer() {

    abstract val renderers: List<AffineRenderer>

    override fun setSize(width: Int, height: Int) {
        renderers.forEach { it.setSize(width, height) }
    }

    override fun setScale(scale: Float) {
        renderers.forEach { it.setScale(scale) }
    }

    override fun setTranslateX(translateX: Float) {
        renderers.forEach { it.setTranslateX(translateX) }
    }

    override fun setTranslateY(translateY: Float) {
        renderers.forEach { it.setTranslateY(translateY) }
    }

    override fun render(canvas: Canvas) {
        renderers.forEach { it.render(canvas) }
    }

}