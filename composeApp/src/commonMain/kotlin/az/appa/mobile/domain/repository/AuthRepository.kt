package az.appa.mobile.domain.repository

import az.appa.mobile.data.api.response.VerifyResponse
import az.appa.mobile.domain.utils.AppaException
import az.appa.mobile.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(email: String): Flow<Resource<Unit, AppaException>>

    suspend fun verification(email: String, otpCode: String): Flow<Resource<VerifyResponse, AppaException>>
}