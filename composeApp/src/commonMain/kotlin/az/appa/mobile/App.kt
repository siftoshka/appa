package az.appa.mobile

import androidx.compose.runtime.Composable
import az.appa.mobile.di.dataModule
import az.appa.mobile.di.networkModule
import az.appa.mobile.di.viewModelModule
import az.appa.mobile.theme.AppTheme
import org.koin.compose.KoinApplication

@Composable
internal fun App() {
    KoinApplication(moduleList = { listOf(networkModule, dataModule, viewModelModule) }) {
        AppTheme {

        }
    }
}

internal expect fun openUrl(url: String?)

internal expect fun getPlatform(): String