package com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl

import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.ImageEditContract.*
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_image_edit.*
import org.koin.android.ext.android.inject

class ImageEditFragment : BaseFragment<PresenterContract>(R.layout.fragment_image_edit), ViewContract {

    companion object {
        private const val IMAGE_KEY = "key_image"

        fun newInstance(image: Uri): ImageEditFragment = ImageEditFragment().apply {
            arguments = Bundle().apply {
                putParcelable(IMAGE_KEY, image)
            }
        }
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
        arguments?.let {
            presenter.init(it.getParcelable(IMAGE_KEY)!!)
        }
    }

    override fun initViews() {
        ToolbarConfigurator()
            .setNavigationClickListener { close() }
            .setTitle(getString(R.string.image_edit_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.onHandbookClicked() })
            .applyToToolbar(toolbar)
    }

    override fun setImage(uri: Uri) {
        Glide.with(requireContext()).load(uri).into(image)
    }

}