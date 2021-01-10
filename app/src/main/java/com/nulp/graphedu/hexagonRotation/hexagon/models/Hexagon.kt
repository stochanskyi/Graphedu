package com.nulp.graphedu.hexagonRotation.hexagon.models

import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType.*
import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType
import kotlin.math.sqrt

data class Hexagon(
    val center: PointCoordinates,
    val points: List<PointCoordinates>
) {

    val radius: Float =
        if (points.isEmpty()) 1f
        else distanceToPoint(points.first())

    companion object {
        private val vertexTypeIndexes: Map<HexagonPointType, Int> = mapOf(
            TOP to 0,
            TOP_RIGHT to 1,
            BOTTOM_RIGHT to 2,
            BOTTOM to 3,
            BOTTOM_LEFT to 4,
            TOP_LEFT to 5
        )
    }

    fun vertexTypeCoordinates(type: HexagonPointType): PointCoordinates {
        return if (type == CENTER) return center
        else points[vertexTypeIndexes.getValue(type)]
    }

    private fun distanceToPoint(point: PointCoordinates): Float {
        return sqrt(
            (point.x - center.x) * (point.x - center.x) + (point.y - center.y) * (point.y - center.y)
        )
    }
}