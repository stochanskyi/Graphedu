package com.nulp.graphedu.presentation.views.affine.figure

import android.content.Context
import android.graphics.*
import com.nulp.graphedu.presentation.utils.dp

class FigureDrawer(context: Context) {

    companion object {
        private const val LINE_STROKE_WIDTH = 4

        private const val CENTER_POINT_RADIUS = 4

        private const val SELECTED_POINT_RADIUS = 6
        private const val SELECTED_POINT_WIDTH = 4
    }

    private val linePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = context.dp(LINE_STROKE_WIDTH)
        color = Color.BLACK
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    private val centerPointPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }

    private val selectedPointPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = context.dp(SELECTED_POINT_WIDTH)
        color = Color.RED
    }

    private val centerPointRadius = context.dp(CENTER_POINT_RADIUS)

    private val selectedPointRadius = context.dp(SELECTED_POINT_RADIUS)

    val path = Path()

    fun draw(canvas: Canvas, figure: FigureRendererData) {
        drawLines(canvas, figure.linePoints)
        drawCenterPoint(canvas, figure.centerPoint)
        drawSelectedPointCircle(canvas, figure.selectedPoint)
    }

    private fun drawLines(canvas: Canvas, linePoints: List<PointF>) {
        if (linePoints.size < 2) return

        path.reset()

        val initialPoint = linePoints.first()
        path.moveTo(initialPoint.x, -initialPoint.y)

        for (i in 1 until linePoints.size) {
            val point = linePoints[i]
            path.lineTo(point.x, -point.y)
        }
        path.lineTo(initialPoint.x, -initialPoint.y)
        path.close()

        canvas.drawPath(path, linePaint)
    }

    private fun drawCenterPoint(canvas: Canvas, point: PointF) {
        canvas.drawCircle(
            point.x,
            -point.y,
            centerPointRadius,
            centerPointPaint
        )
    }

    private fun drawSelectedPointCircle(canvas: Canvas, point: PointF) {
        canvas.drawCircle(
            point.x,
            -point.y,
            selectedPointRadius,
            selectedPointPaint
        )
    }
}