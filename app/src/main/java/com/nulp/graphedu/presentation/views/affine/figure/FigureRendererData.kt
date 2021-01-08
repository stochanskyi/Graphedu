package com.nulp.graphedu.presentation.views.affine.figure

import android.graphics.PointF

class FigureRendererData(
    val linePoints: List<PointF>,
    val centerPoint: PointF,
    val selectedPoint: PointF
) {

    fun transformedToCanvasCoordinates(segmentSize: Float): FigureRendererData {
        return FigureRendererData(
            linePoints.map { it.transformedToCanvasCoordinates(segmentSize) },
            centerPoint.transformedToCanvasCoordinates(segmentSize),
            selectedPoint.transformedToCanvasCoordinates(segmentSize)
        )
    }

    private fun PointF.transformedToCanvasCoordinates(segmentValue: Float): PointF {
        return PointF(x * segmentValue, -y * segmentValue)
    }
}