package az.appa.mobile.data.api.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorBodyResponse(
    var timestamp: String? = "",
    var httpStatus: String? = "",
    var message: String,
)