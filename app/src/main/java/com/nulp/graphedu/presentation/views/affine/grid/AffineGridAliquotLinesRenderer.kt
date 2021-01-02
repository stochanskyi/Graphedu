package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.BaseRenderer

class AffineGridAliquotLinesRenderer(
    context: Context
): BaseRenderer(), GridSubRenderer {

    companion object {
        // Grid line
        private const val GRID_LINE_COLOR = Color.LTGRAY
        private const val GRID_LINE_WIDTH = 0.5f

        // Grid aliquot line
        private const val GRID_ALIQUOT_LINE_COLOR = Color.DKGRAY
        private const val GRID_ALIQUOT_LINE_WIDTH = 0.5f

        private const val DRAW_OUT_OF_BOUNDS = 10f
    }

    private val gridLinePaint = Paint().apply {
        color = GRID_LINE_COLOR
        strokeWidth = context.dp(GRID_LINE_WIDTH)
    }

    private val gridAliquotLinePaint = Paint().apply {
        color = GRID_ALIQUOT_LINE_COLOR
        strokeWidth = context.dp(GRID_ALIQUOT_LINE_WIDTH)
    }

    private var segmentSize: Float = 0f

    override fun setSegmentSize(size: Float) {
        this.segmentSize = size
    }

    override fun render(canvas: Canvas) {
        drawXLines(canvas)
        drawYLines(canvas)
    }

    private fun drawXLines(canvas: Canvas) {
        drawLeftXLines(canvas)
        drawRightXLines(canvas)
    }

    private fun drawLeftXLines(canvas: Canvas) {
        var currentY = relativeCy - segmentSize
        var i = 1
        while (currentY > -DRAW_OUT_OF_BOUNDS) {
            drawXAxisLine(canvas, currentY, i % 5 == 0)
            currentY -= segmentSize
            i++
        }
    }

    private fun drawRightXLines(canvas: Canvas) {
        var currentY = relativeCy + segmentSize
        var i = 1
        while (currentY < heightF + DRAW_OUT_OF_BOUNDS) {
            drawXAxisLine(canvas, currentY, i % 5 == 0)
            currentY += segmentSize
            i++
        }
    }

    private fun drawXAxisLine(canvas: Canvas, y: Float, isAliquot: Boolean) {
        val paint =
            if (isAliquot) gridAliquotLinePaint
            else gridLinePaint
        drawXAxisLine(canvas, y, paint)
    }

    private fun drawXAxisLine(canvas: Canvas, y: Float, paint: Paint) {
        canvas.drawLine(
            relativeLeft, y,
            relativeRight, y,
            paint
        )
    }

    private fun drawYLines(canvas: Canvas) {
        drawTopYLines(canvas)
        drawBottomYLines(canvas)
    }

    private fun drawTopYLines(canvas: Canvas) {
        var currentX = relativeCx - segmentSize
        var i = 1
        while (currentX > -DRAW_OUT_OF_BOUNDS) {
            drawYAxisLine(canvas, currentX, i % 5 == 0)
            currentX -= segmentSize
            i++
        }
    }

    private fun drawBottomYLines(canvas: Canvas) {
        var currentX = relativeCx + segmentSize
        var i = 1
        while (currentX < widthF + DRAW_OUT_OF_BOUNDS) {
            drawYAxisLine(canvas, currentX, i % 5 == 0)
            currentX += segmentSize
            i++
        }
    }

    private fun drawYAxisLine(canvas: Canvas, x: Float, isAliquot: Boolean) {
        val paint =
            if (isAliquot) gridAliquotLinePaint
            else gridLinePaint
        drawYAxisLine(canvas, x, paint)
    }

    private fun drawYAxisLine(canvas: Canvas, x: Float, paint: Paint) {
        canvas.drawLine(
            x, relativeTop,
            x, relativeBottom,
            paint
        )
    }
}