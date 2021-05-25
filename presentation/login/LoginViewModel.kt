package co.test.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Alexander Karpenko on 2021-05-25.
 * java.karpenko@gmail.com
 */
class LoginViewModel (private val signInUseCase: LoginUseCase) : ViewModel() {

    sealed class Event {
        object LogIn : Event()
        object ShowPassword : Event()
        object ShowEmail : Event()

        val isLoading get() = this is Loading
    }

    val event = MutableLiveData<Event>()
    private val isEmailValid = MutableLiveData(false)
    private val isPasswordValid= MutableLiveData(false)

    private lateinit var email: String
    private var code: String? = null
    private var password: String? = null
    var isResetPasswordFlow = false

    fun isPasswordAndEmailValid() = isEmailValid.combineWith(isPasswordValid) { email, password ->
        email && password
    }

    fun onEmailChanged(email: String?) {
        this.email = email.orEmpty()
        isEmailValid.value = email?.isEmailValid == true
    }


    fun onPasswordChanged(password: String?) {
        this.password = password.orEmpty()
        isPasswordValid.value = password?.isPasswordValid == true
    }

    private fun onLoginClick() = viewModelScope.launch {
        event.value = Event.Loading
        signInUseCase.launchLogin(email, password).fold(
            onSuccess = {
                when (it) {
                    is LoginResult.Failed -> handleFailedResult(it)
                    is LoginResult.Success -> event.value = Event.ShowMainScreen
                }
            },
            onFailure = ::handleError
        )
    }
}
