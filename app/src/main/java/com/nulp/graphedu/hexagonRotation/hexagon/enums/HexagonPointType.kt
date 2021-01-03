package com.nulp.graphedu.hexagonRotation.hexagon.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.nulp.graphedu.R

enum class HexagonPointType(@DrawableRes val imageRes: Int, @StringRes val textRes: Int) {
    CENTER(R.drawable.ic_hexagon_center, R.string.hexagon_rotation_point_center_text),
    TOP(R.drawable.ic_hexagon_top, R.string.hexagon_rotation_point_top_text),
    TOP_RIGHT(R.drawable.ic_hexagon_top_right, R.string.hexagon_rotation_point_top_right_text),
    BOTTOM_RIGHT(R.drawable.ic_hexagon_bottom_right, R.string.hexagon_rotation_point_bottom_right_text),
    BOTTOM(R.drawable.ic_hexagon_bottom, R.string.hexagon_rotation_point_bottom_text),
    BOTTOM_LEFT(R.drawable.ic_hexagon_bottom_left, R.string.hexagon_rotation_point_bottom_left_text),
    TOP_LEFT(R.drawable.ic_hexagon_top_left, R.string.hexagon_rotation_point_top_left_text),
}