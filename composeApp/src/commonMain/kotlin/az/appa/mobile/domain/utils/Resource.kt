package az.appa.mobile.domain.utils

sealed interface Resource<out D, out E: AppaException> {
    data class Success<out D, out E: AppaException>(val data: D) : Resource<D, E>
    data class Error<out D, out E: AppaException>(val exception: E) : Resource<D, E>
}