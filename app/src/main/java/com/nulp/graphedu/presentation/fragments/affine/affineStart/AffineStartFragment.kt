package com.nulp.graphedu.presentation.fragments.affine.affineStart

import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.views.affine.figure.FigureRenderer
import com.nulp.graphedu.presentation.views.affine.figure.FigureRendererData
import kotlinx.android.synthetic.main.fragment_affine_start.*

class AffineStartFragment: Fragment(R.layout.fragment_affine_start) {

    companion object {
        fun newInstance(): AffineStartFragment {
            return AffineStartFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val renderer = FigureRenderer(requireContext())
        val figure = FigureRendererData(
            listOf(
                PointF(50f, 50f),
                PointF(50f, 100f),
                PointF(100f, 100f),
                PointF(100f, 50f)
            ),
            PointF(75f, 75f),
            PointF(50f, 50f)
        )
        renderer.setFigure(figure)
        affineView.gridRenderer.addRenderer(renderer)
        affineView.invalidate()
    }
}