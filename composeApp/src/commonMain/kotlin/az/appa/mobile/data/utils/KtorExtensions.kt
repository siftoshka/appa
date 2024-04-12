@file:Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")

package az.appa.mobile.data.utils

import az.appa.mobile.data.api.response.ErrorBodyResponse
import az.appa.mobile.domain.utils.AppaException
import az.appa.mobile.domain.utils.AppaException.BadRequestException
import az.appa.mobile.domain.utils.AppaException.ServerDownException
import az.appa.mobile.domain.utils.Resource
import az.appa.mobile.getPlatform
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend inline fun <reified Response> HttpClient.safeGet(
    path: String, params: Pair<String, Any>? = null
): Flow<Resource<Response>> = flow {
    try {
        val response = get(path) {
            header(HttpHeaders.Authorization, "Bearer")
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(CustomHeaders.Platform, getPlatform())
            contentType(ContentType.Application.Json)
            header(HttpHeaders.CacheControl, "no-cache")
            params?.let { parameter(it.first, params.second) }
        }
        when (response.status.value) {
            in 200..299 -> emit(Resource.Success(response.body()))
            in 300..399 -> emit(Resource.Error(BadRequestException))
            in 400..499 -> emit(Resource.Error(response.errorBody()))
            in 500..599 -> emit(Resource.Error(ServerDownException))
        }
    } catch (e: Exception) {
        emit(Resource.Error(ServerDownException))
    }
}

suspend inline fun <reified Response> HttpClient.safePost(
    path: String, body: Any, shouldBeAuthorized: Boolean = false
): Flow<Resource<Response>> = flow {
    try {
        val response = post(path) {
            if (shouldBeAuthorized) {
                header(HttpHeaders.Authorization, "Bearer")
            }
            header(HttpHeaders.Accept, ContentType.Application.Json)
            header(CustomHeaders.Platform, getPlatform())
            contentType(ContentType.Application.Json)
            header(HttpHeaders.CacheControl, "no-cache")
            setBody(body)
        }
        when (response.status.value) {
            in 200..299 -> emit(Resource.Success(response.body()))
            in 300..399 -> emit(Resource.Error(BadRequestException))
            in 400..499 -> emit(Resource.Error(response.errorBody()))
            in 500..599 -> emit(Resource.Error(ServerDownException))
        }
    } catch (e: Exception) {
        emit(Resource.Error(ServerDownException))
    }
}

suspend inline fun <reified Response> HttpClient.safePut(
    path: String, body: Any
): Flow<Resource<Response>> = flow {
    try {
        val response = put(path) {
            header(HttpHeaders.Authorization, "Bearer")
            header(CustomHeaders.Platform, getPlatform())
            header(HttpHeaders.Accept, ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            header(HttpHeaders.CacheControl, "no-cache")
            setBody(body)
        }
        when (response.status.value) {
            in 200..299 -> emit(Resource.Success(response.body()))
            in 300..399 -> emit(Resource.Error(BadRequestException))
            in 400..499 -> emit(Resource.Error(response.errorBody()))
            in 500..599 -> emit(Resource.Error(ServerDownException))
        }
    } catch (e: Exception) {
        emit(Resource.Error(ServerDownException))
    }
}

suspend inline fun HttpResponse.errorBody(): AppaException =
    try {
        when (body<ErrorBodyResponse>().message) {
            else -> BadRequestException
        }
    } catch (e: Exception) {
        ServerDownException
    }