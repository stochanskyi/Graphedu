package com.nulp.graphedu.presentation.fragments.rotation.hexagonRotation.impl

import androidx.constraintlayout.widget.ConstraintSet
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.fragment_rotation.*


internal fun HexagonRotationFragment.animateActionsVisible() {
    TransitionManager.beginDelayedTransition(layoutContent, actionsVisibilityTransition())
}

internal fun HexagonRotationFragment.animateHexagonPointsVisible() {
    TransitionManager.beginDelayedTransition(layoutContent, hexagonPointsVisibilityTransition())
}

internal fun HexagonRotationFragment.setActionsVisible(isVisible: Boolean) {
    ConstraintSet().apply {
        clone(layoutContent)

        if (isVisible) {
            clear(buttonActionRotate.id, ConstraintSet.TOP)
            connect(buttonActionRotate.id, ConstraintSet.BOTTOM, 0, ConstraintSet.BOTTOM)
        } else {
            clear(buttonActionRotate.id, ConstraintSet.BOTTOM)
            connect(buttonActionRotate.id, ConstraintSet.TOP, 0, ConstraintSet.BOTTOM)
        }

    }.applyTo(layoutContent)
}

internal fun HexagonRotationFragment.setHexagonPointsVisibility(isVisible: Boolean) {
    ConstraintSet().apply {
        clone(layoutContent)

        if (isVisible) {
            clear(recyclerHexagonPoints.id, ConstraintSet.TOP)
            connect(recyclerHexagonPoints.id, ConstraintSet.BOTTOM, 0, ConstraintSet.BOTTOM)
        } else {
            clear(recyclerHexagonPoints.id, ConstraintSet.BOTTOM)
            connect(recyclerHexagonPoints.id, ConstraintSet.TOP, 0, ConstraintSet.BOTTOM)
        }

    }.applyTo(layoutContent)
}

private fun HexagonRotationFragment.actionsVisibilityTransition(): Transition {
    return ChangeBounds().apply {
        duration = 200L
        interpolator = FastOutSlowInInterpolator()
        addTarget(buttonActionRotate)
    }
}

private fun HexagonRotationFragment.hexagonPointsVisibilityTransition(): Transition {
    return ChangeBounds().apply {
        duration = 200L
        interpolator = FastOutSlowInInterpolator()
        addTarget(recyclerHexagonPoints)
    }
}