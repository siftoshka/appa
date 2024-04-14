package az.appa.mobile.presentation.feature.boxmanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import az.appa.mobile.presentation.feature.boxmanager.components.BoxManagerBar
import az.appa.mobile.presentation.feature.boxmanager.components.BoxManagerPlus
import az.appa.mobile.presentation.feature.boxmanager.components.BoxManagerPlusBottomBar
import az.appa.mobile.utils.BoxManagerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxManagerScreen(
    boxManagerState: BoxManagerState,
    goBack: () -> Unit,
) {
    val viewModel = koinInject<BoxManagerViewModel>(parameters = { parametersOf("1") })
    val state by viewModel.uiState.collectAsState()

    val boxState = remember { mutableStateOf(boxManagerState) }
    val coroutineScope = rememberCoroutineScope()
    val openBottomSheet = rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

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
            BoxManagerBar(
                boxManagerState = boxState.value,
                onBackClick = { goBack() },
                onApplyClick = {}
            )
            BoxManagerBar(
                boxManagerState = boxState.value,
                title = state.title,
                subtitle = state.subtitle,
                onTitleChange = { viewModel.setEvent(BoxManagerContract.Event.OnTitleType(it)) },
                onSubtitleChange = { viewModel.setEvent(BoxManagerContract.Event.OnSubtitleType(it)) }
            )
            BoxManagerPlus(boxState.value) {
                coroutineScope.launch {
                    openBottomSheet.value = true
                    sheetState.show()
                }
            }
            BoxManagerPlusBottomBar(
                openBottomSheet = openBottomSheet,
                coroutineScope = coroutineScope,
                sheetState = sheetState,
                state = state,
                viewModel = viewModel
            ) {

            }
        }
    }
}