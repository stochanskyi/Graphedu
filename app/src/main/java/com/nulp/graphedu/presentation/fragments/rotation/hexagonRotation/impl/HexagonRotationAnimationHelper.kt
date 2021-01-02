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

private fun HexagonRotationFragment.actionsVisibilityTransition(): Transition {
    return ChangeBounds().apply {
        duration = 200L
        interpolator = FastOutSlowInInterpolator()
        addTarget(buttonActionRotate)
    }

}