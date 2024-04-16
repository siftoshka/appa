package az.appa.mobile.presentation.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.title_settings
import az.appa.mobile.presentation.common.TopAppBar
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun SettingsScreen(
    goBack: () -> Unit
) {
    val viewModel = koinInject<SettingsViewModel>()


    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {

                else -> {}
            }
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize().systemBarsPadding()
    ) {
        Column {
            TopAppBar(title = stringResource(Res.string.title_settings)) { goBack() }
        }
    }
}