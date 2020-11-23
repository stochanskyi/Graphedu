package com.nulp.graphedu.presentation.common.mvp

import android.app.Activity
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.nulp.graphedu.presentation.common.extensions.BackPressListener
import com.nulp.graphedu.presentation.common.extensions.FragmentHolder

abstract class BaseActivity<out T: IBasePresenter<out IBaseActivity>>: AppCompatActivity(),
    IBaseActivity,
    FragmentHolder {

    abstract val presenter: T

    abstract fun onInitPresenter()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.release()
    }

    override fun onBackPressed() {
        if(onBackPressedInternal()) return

        if(onBackPressedChildHandled()) return
        if(onBackPressedCloseChild()) return

        if(onBackPressedUnhandled()) return

        super.onBackPressed()
    }

    protected open fun onBackPressedInternal(): Boolean = true
    protected open fun onBackPressedUnhandled(): Boolean = true

    private fun onBackPressedChildHandled(): Boolean {
        for (fragment in supportFragmentManager.fragments.reversed()) {
            if (fragment is BackPressListener && fragment.onBackPressed()) return true
        }
        return false
    }

    private fun onBackPressedCloseChild(): Boolean {
        return if(supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            true
        } else false
    }

    override fun closeOK() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun closeCanceled() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun handleError(e: Throwable) {
        //TODO handle error
    }

    override fun requestCloseChild() {
        supportFragmentManager.popBackStack()
    }
}