package az.appa.mobile.data.api.response

import kotlinx.serialization.Serializable

@Serializable
data class VerifyResponse(
    var token: String
)