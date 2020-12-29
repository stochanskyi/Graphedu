package com.nulp.graphedu.presentation.fragments.imageBoundsSelector

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.extensions.FragmentHolder
import com.nulp.graphedu.presentation.utils.parentAsListener
import kotlinx.android.synthetic.main.fragment_image_selection.*

class ImageBoundsSelectionFragment: Fragment(R.layout.fragment_image_selection) {

    companion object {
        fun newInstance(): ImageBoundsSelectionFragment {
            return ImageBoundsSelectionFragment()
        }
    }

    private val listener: ImageBoundsSelectionListener
        get() = parentAsListener()

    private val navigationListener: FragmentHolder
        get() = parentAsListener()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        buttonCancel.setOnClickListener {
            navigationListener.requestCloseChild()
        }
        buttonSelect.setOnClickListener {
            navigationListener.requestCloseChild()
            listener.handleImageBoundsSelected(generateResult(cropView.cropRect))
        }

        cropView.setImageBitmap(listener.provideBoundsSelectionImage())
    }

    private fun generateResult(rect: Rect) = ImageBounds(
        rect.left,
        rect.top,
        rect.right,
        rect.bottom
    )
}