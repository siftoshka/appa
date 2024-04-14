package az.appa.mobile.presentation.feature.boxmanager.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.btn_add
import appa.composeapp.generated.resources.desc_add_new_app
import appa.composeapp.generated.resources.field_add_new_app
import appa.composeapp.generated.resources.text_add_new_app
import appa.composeapp.generated.resources.text_email
import az.appa.mobile.presentation.common.FilledButton
import az.appa.mobile.presentation.common.TextField
import az.appa.mobile.presentation.feature.boxmanager.BoxManagerContract
import az.appa.mobile.presentation.feature.boxmanager.BoxManagerViewModel
import az.appa.mobile.presentation.feature.login.LoginContract
import az.appa.mobile.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun BoxManagerPlusBottomBar(
    openBottomSheet: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    sheetState: SheetState,
    viewModel: BoxManagerViewModel,
    state: BoxManagerContract.State,
    onPerformClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputUrlPath by remember(state.urlPath) { mutableStateOf(state.urlPath) }

    AnimatedVisibility(openBottomSheet.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            shape = MaterialTheme.shapes.large,
            windowInsets = WindowInsets(0, 0, 0, 0),
            onDismissRequest = {
                coroutineScope.launch {
                    openBottomSheet.value = false
                    sheetState.hide()
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.default),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = stringResource(Res.string.text_add_new_app),
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Start
                    )
                    FilledButton(
                        enabled = inputUrlPath.isNotEmpty(),
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(Res.string.btn_add),
                        textColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        coroutineScope.launch {
                            openBottomSheet.value = false
                            sheetState.hide()
                        }
                        onPerformClick()
                    }
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    text = stringResource(Res.string.desc_add_new_app),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    query = inputUrlPath,
                    labelText = stringResource(Res.string.field_add_new_app),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                ) {
                    inputUrlPath = it
                    viewModel.setEvent(BoxManagerContract.Event.OnUrlType(it))
                }
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.extraLarge))
            }
        }
    }
}