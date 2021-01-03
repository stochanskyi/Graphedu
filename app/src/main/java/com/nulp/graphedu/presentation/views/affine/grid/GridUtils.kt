package com.nulp.graphedu.presentation.views.affine.grid

import kotlin.math.floor

fun resolveIndexRelativeToAxis(value: Float, segmentSize: Float): Int {
    var i = -floor(value / segmentSize).toInt()
    if (i >= 1) i--
    return i
}