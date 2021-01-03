package com.nulp.graphedu.hexagonRotation.hexagon.generator

import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType
import com.nulp.graphedu.hexagonRotation.hexagon.models.Hexagon
import com.nulp.graphedu.hexagonRotation.hexagon.models.PointCoordinates

typealias HexagonPoints = Map<HexagonPointType, PointCoordinates>

interface HexagonGenerator {

    fun generateHexagonPoints(centerCoordinates: PointCoordinates): HexagonPoints

    fun createHexagonFromPoints(points: HexagonPoints): Hexagon
}