package az.appa.mobile.data.repository

import az.appa.mobile.data.api.request.LoginRequest
import az.appa.mobile.data.api.request.VerifyRequest
import az.appa.mobile.data.api.response.VerifyResponse
import az.appa.mobile.data.utils.safePost
import az.appa.mobile.domain.repository.AuthRepository
import az.appa.mobile.domain.utils.NetworkPaths
import az.appa.mobile.domain.utils.Resource
import az.appa.mobile.utils.Constants.BASE_URL
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
) : AuthRepository {

    override suspend fun login(email: String): Flow<Resource<Unit>> {
        return httpClient.safePost(
            path = "$BASE_URL/${NetworkPaths.Auth.Login}",
            body = LoginRequest(email)
        )
    }

    override suspend fun verification(
        email: String,
        otpCode: String
    ): Flow<Resource<VerifyResponse>> {
        return httpClient.safePost(
            path = "$BASE_URL/${NetworkPaths.Auth.Verify}",
            body = VerifyRequest(email, otpCode),
        )
    }
}