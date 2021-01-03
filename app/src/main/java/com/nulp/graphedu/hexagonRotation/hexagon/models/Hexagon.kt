package com.nulp.graphedu.hexagonRotation.hexagon.models

import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType.*
import com.nulp.graphedu.hexagonRotation.hexagon.enums.HexagonPointType

data class Hexagon(
    val hexagonCenter: PointCoordinates,
    val hexagonPoints: List<PointCoordinates>
) {

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
        return if (type == CENTER) return hexagonCenter
        else hexagonPoints[vertexTypeIndexes.getValue(type)]
    }
}