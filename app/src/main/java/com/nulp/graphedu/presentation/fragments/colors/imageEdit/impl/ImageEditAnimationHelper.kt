package com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl

import androidx.constraintlayout.widget.ConstraintSet
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.fragment_image_edit.*

internal fun ImageEditFragment.setActionsLayoutVisible(isVisible: Boolean) {
    ConstraintSet().apply {
        clone(layoutContent)

        if (isVisible) {
            clear(layoutActions.id, ConstraintSet.TOP)
            connect(layoutActions.id, ConstraintSet.BOTTOM, 0, ConstraintSet.BOTTOM)
        } else {
            clear(layoutActions.id, ConstraintSet.BOTTOM)
            connect(layoutActions.id, ConstraintSet.TOP, 0, ConstraintSet.BOTTOM)
        }
    }.applyTo(layoutContent)
}

internal fun ImageEditFragment.animateActionsLayoutVisible() {
    TransitionManager.beginDelayedTransition(layoutContent, actionsLayoutVisibleTransition())
}

private fun ImageEditFragment.actionsLayoutVisibleTransition(): Transition {
    return ChangeBounds().apply {
        duration = 200L
        interpolator = FastOutSlowInInterpolator()
        addTarget(layoutActions)
    }
}