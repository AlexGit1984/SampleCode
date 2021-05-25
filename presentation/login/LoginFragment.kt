package co.test.presentation.login

import android.os.Bundle
import android.view.View
import co.test.R
import co.test.presentation.base.BaseFragment

/**
 * Created by Alexander Karpenko on 2021-05-25.
 * java.karpenko@gmail.com
 */
class LoginFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_login

    private val viewModel: LoginViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.isPasswordAndEmailValid(), tvLogin::setEnabled)

        etEmail.doAfterTextChanged {
            viewModel.onEmailChanged(it?.toString()?.trim())
        }

        etPassword.doAfterTextChanged {
            viewModel.onPasswordChanged(it?.toString()?.trim())
        }

        tvLogin.setOnClickListener {
            viewModel.onLoginClick()
        }

        tvForgotPassword.setOnClickListener {
            viewModel.onForgotPasswordClick()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.isResetPasswordFlow = false
    }

    companion object {
        fun newInstance() = FrontLogInFragment()
    }
}
