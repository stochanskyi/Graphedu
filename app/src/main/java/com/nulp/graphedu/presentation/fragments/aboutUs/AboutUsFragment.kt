package com.nulp.graphedu.presentation.fragments.aboutUs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nulp.graphedu.R
import com.nulp.graphedu.presentation.common.extensions.FragmentHolder
import com.nulp.graphedu.presentation.utils.parentAsListener
import com.nulp.graphedu.presentation.views.toolbarConfigurator.ToolbarConfigurator
import kotlinx.android.synthetic.main.fragment_about_us.*
import kotlinx.android.synthetic.main.fragment_about_us.toolbar

class AboutUsFragment : Fragment(R.layout.fragment_about_us) {
    companion object {
        fun newInstance(): AboutUsFragment {
            return AboutUsFragment()
        }
    }

    private val navigationListener: FragmentHolder
        get() = parentAsListener()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        ToolbarConfigurator()
            .setNavigationClickListener { navigationListener.requestCloseChild() }
            .setTitle(getString(R.string.about_us_title))
            .applyToToolbar(toolbar)

        textVersion.text = getString(R.string.about_us_version_name, resolveAppVersionName())
    }

    private fun resolveAppVersionName(): String {
        with (activity ?: return "") {
            return packageManager?.getPackageInfo(packageName, 0)?.versionName ?: ""
        }
    }
}