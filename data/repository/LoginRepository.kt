package co.test.data.repository

/**
 * Created by Alexander Karpenko on 2021-05-25.
 * java.karpenko@gmail.com
 */
class LoginRepository(
private val cloudStore: CloudStore,
private val localDataStore: LocalDataStore
) {

    suspend fun userLogin(email: String, password: String?, code: String?): LoginResult {
        val body = PostUserBody(
            username = email,
            password = password
        )
        return executeLoginUser(body)
    }

    private fun executeLoginUser(body: Response): LoginResult {
       val response =  cloudStore.loginCall(body)

        localDataStore.save(body.email)

        return LoginResult(response.token)
    }
}
