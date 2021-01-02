package com.nulp.graphedu.presentation.common.mvp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.nulp.graphedu.presentation.common.extensions.BackPressListener
import com.nulp.graphedu.presentation.common.extensions.FragmentHolder

abstract class BaseDialog<T : IBasePresenter<out IBaseView>> : DialogFragment,
    IBaseDialog,
    FragmentHolder,
    BackPressListener {

    protected abstract val presenter: T

    constructor() : super()

    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    protected abstract fun initPresenter()

    protected open fun initViews() {}

    open val isFullScreen: Boolean = false

    override val dialogTag: String?
        get() = tag

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.decorView?.background?.alpha = 0
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initPresenter()
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onStart()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        val newAttributes = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height =
                if (isFullScreen) ViewGroup.LayoutParams.MATCH_PARENT
                else ViewGroup.LayoutParams.WRAP_CONTENT
        }

        dialog?.window?.attributes = newAttributes
    }

    override fun close() {
        this.dismiss()
    }

    override fun handleError(e: Throwable) {
        //TODO errors handling
    }

    override fun requestCloseChild() {
        childFragmentManager.popBackStack()
    }

    override fun onBackPressed(): Boolean {
        if (onBackPressedChildHandled()) return true
        if (onBackPressedCloseChild()) return true
        return false
    }

    private fun onBackPressedChildHandled(): Boolean {
        for (fragment in childFragmentManager.fragments.reversed()) {
            if (fragment is BackPressListener && fragment.onBackPressed()) return true
        }
        return false
    }

    private fun onBackPressedCloseChild(): Boolean {
        return if (childFragmentManager.backStackEntryCount > 0) {
            childFragmentManager.popBackStack()
            true
        } else false
    }
}