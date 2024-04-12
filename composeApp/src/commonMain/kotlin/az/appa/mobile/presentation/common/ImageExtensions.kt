package az.appa.mobile.presentation.common

import androidx.compose.runtime.Composable
import io.kamel.core.utils.cacheControl
import io.kamel.image.asyncPainterResource
import io.ktor.client.request.header
import io.ktor.client.utils.CacheControl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job

@Composable
fun getAsyncImageRes(path: String) = asyncPainterResource(path) {
    coroutineContext = Job() + Dispatchers.IO
    requestBuilder {
        cacheControl(CacheControl.MAX_AGE)
    }
}