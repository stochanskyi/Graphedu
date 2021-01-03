package com.nulp.graphedu.presentation.views.affine.figure

import android.content.Context
import android.graphics.*
import com.nulp.graphedu.presentation.views.affine.BaseRenderer

class FigureRenderer(
    context: Context
) : BaseRenderer() {

    private val drawer = FigureDrawer(context)

    private var figure: FigureRendererData? = null

    fun setFigure(figure: FigureRendererData) {
        this.figure = figure
    }

    override fun render(canvas: Canvas) {
        if (figure == null) return

        canvas.save()

        canvas.translate(cx, cy)
        canvas.applyAffineTransformations()

        drawer.draw(canvas, figure!!)

        canvas.restore()
    }

    private fun Canvas.applyAffineTransformations() {
        scale(scale, scale, cx, cy)
        translate(translateX, translateY)
    }
}