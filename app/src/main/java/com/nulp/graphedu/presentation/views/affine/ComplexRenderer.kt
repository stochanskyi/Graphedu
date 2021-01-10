package com.nulp.graphedu.presentation.views.affine

import android.graphics.Canvas

abstract class ComplexRenderer<T: AffineRenderer>: BaseRenderer(), RenderersContainer<T> {

    abstract val renderers: MutableList<T>

    override fun addRenderer(renderer: T) {
        initRendererParams(renderer)
        renderers += renderer
    }

    override fun removeRenderer(renderer: T) {
        renderers -= renderer
    }

    override fun setSize(width: Int, height: Int) {
        super.setSize(width, height)
        renderers.forEach { it.setSize(width, height) }
    }

    override fun setScale(scale: Float) {
        super.setScale(scale)
        renderers.forEach { it.setScale(scale) }
    }

    override fun setTranslateX(translateX: Float) {
        super.setTranslateX(translateX)
        renderers.forEach { it.setTranslateX(translateX) }
    }

    override fun setTranslateY(translateY: Float) {
        super.setTranslateY(translateY)
        renderers.forEach { it.setTranslateY(translateY) }
    }

    override fun render(canvas: Canvas) {
        renderers.forEach { it.render(canvas) }
    }

    protected open fun initRendererParams(renderer: T) {
        renderer.setSize(width, height)
        renderer.setScale(scale)
        renderer.setTranslateX(translateX)
        renderer.setTranslateY(translateY)
    }

}