package co.test.domain.usecases

import co.test.data.repository.LoginRepository

/**
 * Created by Alexander Karpenko on 2021-05-25.
 * java.karpenko@gmail.com
 */

class LoginUseCase {
        logger: Logger,
        dispatcher: CoroutineDispatcher,
        private val loginRepository: LoginRepository
    ) : BaseUseCase(logger) {

        suspend fun launchLogin(email: String, password: String?, code: String?) = wrapResult {
            loginRepository.userLogin(email, password, code).onSuccess {
                handleSuccessResult(it)
            }
        }
    }
