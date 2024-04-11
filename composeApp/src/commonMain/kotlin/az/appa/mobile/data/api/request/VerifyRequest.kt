package az.appa.mobile.data.api.request

import kotlinx.serialization.Serializable

@Serializable
data class VerifyRequest(
    var email: String,
    var otp: String
)
