package co.test.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.cancelChildren

/**
 * Created by Alexander Karpenko on 2021-05-25.
 * java.karpenko@gmail.com
 */

abstract class BaseFragment : Fragment() {

    protected abstract val layoutRes: Int
    protected val baseActivity get() = requireActivity() as BaseActivity

    override fun onCreateView(inflater: LayoutInflater, group: ViewGroup?, state: Bundle?): View? =
        inflater.inflate(layoutRes, group, false)

    override fun onDestroyView() {
        lifecycleScope.coroutineContext.cancelChildren()
        super.onDestroyView()
    }

    protected fun setStatusBarColor(@ColorRes resId: Int) {
        baseActivity.setStatusBarColor(resId)
    }

    protected fun showError(error: Error) {
        baseActivity.showError(error)
    }

    protected fun showMessage(@StringRes id: Int) {
        baseActivity.showMessage(id)
    }
}
