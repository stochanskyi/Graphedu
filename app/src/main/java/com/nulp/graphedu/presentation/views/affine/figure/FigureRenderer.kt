package com.nulp.graphedu.presentation.views.affine.figure

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import com.nulp.graphedu.presentation.views.affine.grid.BaseGridRendererComponent
import com.nulp.graphedu.presentation.views.affine.grid.SEGMENTS_BETWEEN_ALIQUOT

class FigureRenderer(
    context: Context
) : BaseGridRendererComponent() {

    private val drawer = FigureDrawer(context)

    private var figure: FigureRendererData? = null

    private var defaultAliquotSize: Float = 1f

    fun setFigure(figure: FigureRendererData) {
        this.figure = figure
    }

    override fun initDefaultSegmentSize(size: Float) {
        super.initDefaultSegmentSize(size)
        defaultAliquotSize = size * SEGMENTS_BETWEEN_ALIQUOT
    }

    override fun render(canvas: Canvas) {
        if (figure == null) return
        val transformedFigure = FigureTransformer(figure!!).transformToCanvasCoordinates()

        canvas.save()

        // Move to coordinates center
        canvas.translate(cx, cy)

        // Apply user translation
        canvas.translate(translateX, translateY)

        drawer.draw(canvas, transformedFigure)

        canvas.restore()
    }

    private inner class FigureTransformer(
        private val figure: FigureRendererData,
    ) {
        private val scale = this@FigureRenderer.scale * defaultAliquotSize

        fun transformToCanvasCoordinates(): FigureRendererData {
            return FigureRendererData(
                figure.linePoints.map { it.transformedToCanvasCoordinates() },
                figure.centerPoint.transformedToCanvasCoordinates(),
                figure.selectedPoint.transformedToCanvasCoordinates()
            )
        }

        private fun PointF.transformedToCanvasCoordinates(): PointF {
            return PointF(x * scale, -y * scale)
        }
    }
}