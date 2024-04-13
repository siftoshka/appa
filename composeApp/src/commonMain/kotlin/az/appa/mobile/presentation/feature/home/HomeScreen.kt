package az.appa.mobile.presentation.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import az.appa.mobile.domain.model.Box
import az.appa.mobile.domain.model.UniversalApp
import az.appa.mobile.presentation.feature.home.components.BoxItem
import az.appa.mobile.presentation.feature.home.components.HomeBar
import az.appa.mobile.theme.spacing
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
) {
    val viewModel = koinInject<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()

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
            HomeBar(
                onComposeClick = {},
                onSettingsClick = {}
            )
            val box = Box(
                id = 1, title = "Subway Surfers", subtitle = "New games every day",
                apps = listOf(UniversalApp(), UniversalApp(), UniversalApp(),
                    UniversalApp(), UniversalApp(), UniversalApp())
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        start = MaterialTheme.spacing.default,
                        end = MaterialTheme.spacing.default,
                        top = MaterialTheme.spacing.extraSmall,
                        bottom = MaterialTheme.spacing.default
                    ),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                ) {
                    items(listOf(box)) {
                        BoxItem(it)
                    }
                }
            }
        }
    }
}