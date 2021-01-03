package com.nulp.graphedu.hexagonRotation.hexagon.generator

import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType
import com.nulp.graphedu.hexagonRotation.hexagon.models.Hexagon
import com.nulp.graphedu.hexagonRotation.hexagon.models.PointCoordinates
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private typealias MutableHexagonPoints = MutableMap<HexagonPointType, PointCoordinates>

class HexagonGeneratorImpl : HexagonGenerator {

    companion object {
        private const val DEFAULT_HEXAGON_RADIUS = 50
    }

    private fun calculateVertexCoordinates(center: PointCoordinates, vertexIndex: Int): PointCoordinates {
        val x = center.x + DEFAULT_HEXAGON_RADIUS * sin((2 * PI * vertexIndex) / 6).toFloat()
        val y = center.y + DEFAULT_HEXAGON_RADIUS * cos((2 * PI * vertexIndex) / 6).toFloat()

        return PointCoordinates(x, y)
    }

    override fun generateHexagonPoints(centerCoordinates: PointCoordinates): HexagonPoints {
        val points: MutableHexagonPoints = mutableMapOf()

        points.apply {
            put(HexagonPointType.CENTER, centerCoordinates)
            put(HexagonPointType.TOP, calculateVertexCoordinates(centerCoordinates, 0))
            put(HexagonPointType.TOP_RIGHT, calculateVertexCoordinates(centerCoordinates, 1))
            put(HexagonPointType.BOTTOM_RIGHT, calculateVertexCoordinates(centerCoordinates, 2))
            put(HexagonPointType.BOTTOM, calculateVertexCoordinates(centerCoordinates, 3))
            put(HexagonPointType.BOTTOM_LEFT, calculateVertexCoordinates(centerCoordinates, 4))
            put(HexagonPointType.TOP_LEFT, calculateVertexCoordinates(centerCoordinates, 5))
        }

        return points.toMap()
    }

    override fun createHexagonFromPoints(points: HexagonPoints): Hexagon {
        val vertexCoordinates = points.filterNot { it.key == HexagonPointType.CENTER }.values.toList()
        val centerCoordinates = points[HexagonPointType.CENTER]

        return Hexagon(
            centerCoordinates!!,
            vertexCoordinates
        )
    }

}