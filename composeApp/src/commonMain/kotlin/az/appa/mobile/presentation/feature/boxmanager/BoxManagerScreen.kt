package az.appa.mobile.presentation.feature.boxmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import az.appa.mobile.domain.model.UniversalApp
import az.appa.mobile.presentation.common.AppItem
import az.appa.mobile.presentation.feature.boxmanager.components.BoxManagerBar
import az.appa.mobile.presentation.feature.boxmanager.components.BoxManagerInfo
import az.appa.mobile.presentation.feature.boxmanager.components.BoxManagerPlus
import az.appa.mobile.presentation.feature.boxmanager.components.BoxManagerPlusBottomBar
import az.appa.mobile.theme.spacing
import az.appa.mobile.utils.BoxManagerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
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
            BoxManagerInfo(
                boxManagerState = boxState.value,
                state = state,
                onTitleChange = { viewModel.setEvent(BoxManagerContract.Event.OnTitleType(it)) },
                onSubtitleChange = { viewModel.setEvent(BoxManagerContract.Event.OnSubtitleType(it)) }
            )
            BoxManagerPlus(boxState.value) {
                coroutineScope.launch {
                    openBottomSheet.value = true
                    sheetState.show()
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = MaterialTheme.spacing.default,
                    end = MaterialTheme.spacing.default,
                    top = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.default
                ),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            ) {
                items(listOf(UniversalApp(), UniversalApp())) {
                    AppItem(it)
                }
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