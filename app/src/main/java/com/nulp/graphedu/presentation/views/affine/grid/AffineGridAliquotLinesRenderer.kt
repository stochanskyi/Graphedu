package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.BaseRenderer
import com.nulp.graphedu.presentation.views.affine.SEGMENTS_BETWEEN_ALIQUOT

// TODO do not draw below axes
class AffineGridAliquotLinesRenderer(
    context: Context
) : BaseRenderer(), GridSubRenderer {

    companion object {
        // Grid aliquot line
        private const val ALIQUOT_LINE_COLOR = Color.DKGRAY
        private const val ALIQUOT_LINE_WIDTH = 1.5f

        private const val DRAW_OUT_OF_BOUNDS = 10f
    }

    private val aliquotLinePaint = Paint().apply {
        color = ALIQUOT_LINE_COLOR
        strokeWidth = context.dp(ALIQUOT_LINE_WIDTH)
    }

    private var segmentSize: Float = 0f
    private var quintSize: Float = 0f

    override fun setSegmentSize(size: Float) {
        this.segmentSize = size
        quintSize = segmentSize * SEGMENTS_BETWEEN_ALIQUOT
    }

    override fun render(canvas: Canvas) {
        drawHorizontalLines(canvas)
        drawVerticalLines(canvas)
    }

    private fun drawHorizontalLines(canvas: Canvas) {
        var currentY = (cy + translateY) % quintSize
        while (currentY < heightF + DRAW_OUT_OF_BOUNDS) {
            drawHorizontalLine(canvas, currentY)
            currentY += quintSize
        }
    }

    private fun drawHorizontalLine(canvas: Canvas, y: Float) {
        canvas.drawLine(
            0f, y,
            widthF, y,
            aliquotLinePaint
        )
    }

    private fun drawVerticalLines(canvas: Canvas) {
        var currentX = (cx + translateX) % quintSize
        while (currentX < widthF + DRAW_OUT_OF_BOUNDS) {
            drawVerticalLine(canvas, currentX)
            currentX += quintSize
        }
    }

    private fun drawVerticalLine(canvas: Canvas, x: Float) {
        canvas.drawLine(
            x, 0f,
            x, heightF,
            aliquotLinePaint
        )
    }
}