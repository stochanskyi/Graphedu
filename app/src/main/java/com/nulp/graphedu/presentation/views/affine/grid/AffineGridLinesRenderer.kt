package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.nulp.graphedu.presentation.utils.dp
import com.nulp.graphedu.presentation.views.affine.BaseRenderer
import com.nulp.graphedu.presentation.views.affine.DRAW_OUT_OF_BOUNDS
import com.nulp.graphedu.presentation.views.affine.SEGMENTS_BETWEEN_ALIQUOT
import kotlin.math.floor

class AffineGridLinesRenderer(
    context: Context
): BaseRenderer(), GridSubRenderer {

    companion object {
        // Grid line
        private const val GRID_LINE_COLOR = Color.LTGRAY
        private const val GRID_LINE_WIDTH = 1f
    }

    private val gridLinePaint = Paint().apply {
        color = GRID_LINE_COLOR
        strokeWidth = context.dp(GRID_LINE_WIDTH)
    }

    private var segmentSize: Float = 0f

    override fun setSegmentSize(size: Float) {
        this.segmentSize = size
    }

    override fun render(canvas: Canvas) {
        drawHorizontalLines(canvas)
        drawVerticalLines(canvas)
    }

    private fun drawHorizontalLines(canvas: Canvas) {
        val absoluteCy = cy + translateY
        var currentY = absoluteCy % segmentSize
        var i = resolveIndexRelativeToAxis(absoluteCy, segmentSize)

        while (currentY < heightF + DRAW_OUT_OF_BOUNDS) {
            if (i !=0 && i % SEGMENTS_BETWEEN_ALIQUOT != 0) {
                drawHorizontalLine(canvas, currentY)
            }
            currentY += segmentSize
            i++
        }
    }

    private fun drawHorizontalLine(canvas: Canvas, y: Float) {
        canvas.drawLine(
            0f, y,
            widthF, y,
            gridLinePaint
        )
    }

    private fun drawVerticalLines(canvas: Canvas) {
        val absoluteCx = cx + translateX
        var currentX = absoluteCx % segmentSize
        var i = resolveIndexRelativeToAxis(absoluteCx, segmentSize)

        while (currentX < widthF + DRAW_OUT_OF_BOUNDS) {
            if (i !=0 && i % SEGMENTS_BETWEEN_ALIQUOT != 0) {
                drawVerticalLine(canvas, currentX)
            }
            currentX += segmentSize
            i++
        }
    }

    private fun drawVerticalLine(canvas: Canvas, x: Float) {
        canvas.drawLine(
            x, 0f,
            x, heightF,
            gridLinePaint
        )
    }
}