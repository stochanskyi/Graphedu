package com.nulp.graphedu.presentation.fragments.colors.colorsStart.impl

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.mvp.BaseFragment
import com.nulp.graphedu.presentation.fragments.colors.colorsStart.ColorsStartContract.PresenterContract
import com.nulp.graphedu.presentation.fragments.colors.colorsStart.ColorsStartContract.ViewContract
import com.nulp.graphedu.presentation.fragments.colors.imageEdit.impl.ImageEditFragment
import com.nulp.graphedu.presentation.fragments.handbook.container.tab.HandbookTabColors
import com.nulp.graphedu.presentation.fragments.menu.HandbookContainer
import com.nulp.graphedu.presentation.utils.parentAsListener
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ClickableMenuItem
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_fractal_start.*
import org.koin.android.ext.android.inject

class ColorsStartFragment : BaseFragment<PresenterContract>(R.layout.fragment_colors_start), ViewContract {

    companion object {
        private const val STORAGE_PERMISSION_REQUEST_CODE = 101
        private const val PICK_IMAGE_REQUEST_CODE = 201

        fun newInstance(): ColorsStartFragment = ColorsStartFragment()
    }

    override val presenter: PresenterContract by inject()

    override fun initPresenter() {
        presenter.view = this
    }

    override fun initViews() {
        ToolbarConfigurator()
            .withNavigationButton(false)
            .setTitle(getString(R.string.colors_screen_toolbar_title))
            .setMenuId(R.menu.menu_with_handbook)
            .addClickableItem(ClickableMenuItem(R.id.buttonHandbook) { presenter.openHandbook() })
            .applyToToolbar(toolbar)

        layoutContent.setOnClickListener { presenter.pickImage() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != PICK_IMAGE_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        data?.data?.let {
            openEditScreen(it)
        }
    }

    private fun openEditScreen(uri: Uri) {
        childFragmentManager.beginTransaction()
            .addToBackStack(null)
            .add(fragmentContainer.id, ImageEditFragment.newInstance(uri))
            .commit()
    }

    override fun openImagePicker() {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickIntent, PICK_IMAGE_REQUEST_CODE)
    }

    override fun openHandbook() {
        parentAsListener<HandbookContainer>().requestOpenHandbook(HandbookTabColors)
    }

    private fun requestPickImage() {
        val isGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        if (isGranted) openImagePicker()
        else requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_REQUEST_CODE
        )
    }
}