package az.appa.mobile.domain.utils

sealed class Resource<T>(val data: T? = null, val exception: AppaException? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(exception: AppaException) : Resource<T>(exception = exception)
}