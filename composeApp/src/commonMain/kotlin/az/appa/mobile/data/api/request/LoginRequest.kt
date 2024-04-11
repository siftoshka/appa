package az.appa.mobile.data.api.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest (
    var email: String
)