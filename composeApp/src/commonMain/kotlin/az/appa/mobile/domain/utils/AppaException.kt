package az.appa.mobile.domain.utils

sealed class AppaException {
    data object ServerDownException : AppaException()
    data object BadRequestException : AppaException()
    data object GeneralException : AppaException()
}