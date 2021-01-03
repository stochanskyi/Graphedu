package com.nulp.graphedu.presentation.views.affine.grid

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import com.nulp.graphedu.presentation.utils.*
import com.nulp.graphedu.presentation.views.affine.BaseRenderer
import com.nulp.graphedu.presentation.views.affine.DRAW_OUT_OF_BOUNDS
import com.nulp.graphedu.presentation.views.affine.SEGMENTS_BETWEEN_ALIQUOT

// TODO do not draw below axes
class AffineGridAliquotLinesRenderer(
    context: Context
) : BaseRenderer(), GridSubRenderer {

    companion object {
        // Grid aliquot line
        private const val ALIQUOT_LINE_COLOR = Color.DKGRAY
        private const val ALIQUOT_LINE_WIDTH = 1.5f

        // Grid coordinate text
        private const val COORDINATE_TEXT_COLOR = Color.RED
        private const val COORDINATE_TEXT_SIZE = 16
        private const val COORDINATE_TEXT_MARGIN = 4
    }

    private val coordinateTextMargin = context.dp(COORDINATE_TEXT_MARGIN)

    private val aliquotLinePaint = Paint().apply {
        color = ALIQUOT_LINE_COLOR
        strokeWidth = context.dp(ALIQUOT_LINE_WIDTH)
    }

    private val baseCoordinatePaint = TextPaint().apply {
        color = COORDINATE_TEXT_COLOR
        textSize = context.dp(COORDINATE_TEXT_SIZE)
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    }

    private val coordinateYTextPaintLeft = TextPaint(baseCoordinatePaint).apply {
        textAlign = Paint.Align.LEFT
    }

    private val coordinateYTextPaintRight = TextPaint(baseCoordinatePaint).apply {
        textAlign = Paint.Align.RIGHT
    }

    private val coordinateXTextPaint = TextPaint(baseCoordinatePaint).apply {
        textAlign = Paint.Align.CENTER
    }

    private val textBackgroundPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.WHITE
        alpha = 64
    }

    private val bottomTextYOffset = coordinateTextMargin
    private val bottomTextStickyY = coordinateXTextPaint.height + coordinateTextMargin

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
        val absoluteCy = cy + translateY
        var currentY = absoluteCy % quintSize
        var value = resolveIndexRelativeToAxis(absoluteCy, quintSize)
        while (currentY < heightF + DRAW_OUT_OF_BOUNDS) {
            drawHorizontalLine(canvas, currentY)
            drawYCoordinate(canvas, currentY, value.toString())
            currentY += quintSize
            value++
        }
    }

    private fun drawHorizontalLine(canvas: Canvas, y: Float) {
        canvas.drawLine(
            0f, y,
            widthF, y,
            aliquotLinePaint
        )
    }

    private fun drawYCoordinate(canvas: Canvas, y: Float, text: String) {
        val axisX = cx + translateX
        val textY = y + coordinateYTextPaintLeft.baselineToTop / 3f
        val textWidth = baseCoordinatePaint.measureText(text)
        when {
            axisX < textWidth + coordinateTextMargin * 2 -> drawYCoordinateLeft(canvas, textY, text)
            axisX > widthF -> drawYCoordinateRight(canvas, textY, text)
            else -> drawYCoordinateNearAxis(canvas, axisX, textY, text)
        }
    }

    private fun drawYCoordinateNearAxis(canvas: Canvas, x: Float, y: Float, text: String) {
        canvas.drawText(
            text,
            x - coordinateTextMargin,
            y,
            coordinateYTextPaintRight
        )
    }

    private fun drawYCoordinateLeft(canvas: Canvas, y: Float, text: String) {
        canvas.drawText(
            text,
            coordinateTextMargin,
            y,
            coordinateYTextPaintLeft
        )
    }

    private fun drawYCoordinateRight(canvas: Canvas, y: Float, text: String) {
        canvas.drawText(
            text,
            widthF - coordinateTextMargin,
            y,
            coordinateYTextPaintRight
        )
    }

    private fun drawVerticalLines(canvas: Canvas) {
        val absoluteCx = cx + translateX
        var currentX = absoluteCx % quintSize
        var value = resolveIndexRelativeToAxis(absoluteCx, quintSize)
        while (currentX < widthF + DRAW_OUT_OF_BOUNDS) {
            drawVerticalLine(canvas, currentX)
            drawXCoordinate(canvas, currentX, value.toString())
            currentX += quintSize
            value++
        }
    }

    private fun drawVerticalLine(canvas: Canvas, x: Float) {
        canvas.drawLine(
            x, 0f,
            x, heightF,
            aliquotLinePaint
        )
    }

    private fun drawXCoordinate(canvas: Canvas, x: Float, text: String) {
        val axisY = cy + translateY
        when {
            axisY < 0 -> drawXCoordinateTop(canvas, x, text)
            axisY > heightF - bottomTextStickyY -> drawXCoordinateBottom(canvas, x, text)
            else -> drawXCoordinateNearAxis(canvas, x, axisY, text)
        }
    }

    private fun drawXCoordinateNearAxis(canvas: Canvas, x: Float, axisY: Float, text: String) {
        val y = axisY + coordinateXTextPaint.baselineToTop + coordinateTextMargin
        canvas.drawText(text, x, y, coordinateXTextPaint)
    }

    private fun drawXCoordinateTop(canvas: Canvas, x: Float, text: String) {
        val y = coordinateXTextPaint.baselineToTop + coordinateTextMargin
        canvas.drawText(text, x, y, coordinateXTextPaint)
    }

    private fun drawXCoordinateBottom(canvas: Canvas, x: Float, text: String) {
        canvas.drawText(
            text,
            x,
            heightF - bottomTextYOffset,
            coordinateXTextPaint
        )
    }
}