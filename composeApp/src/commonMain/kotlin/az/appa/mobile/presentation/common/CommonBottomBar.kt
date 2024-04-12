package az.appa.mobile.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.btn_negative_delete
import appa.composeapp.generated.resources.btn_positive_delete
import az.appa.mobile.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun CommonBottomBar(
    openBottomSheet: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    title: String,
    desc: String,
    sheetState: SheetState,
    positiveText: String = stringResource(Res.string.btn_positive_delete),
    negativeText: String = stringResource(Res.string.btn_negative_delete),
    onPerformClick: () -> Unit
) {
    AnimatedVisibility(openBottomSheet.value) {
        ModalBottomSheet(
            sheetState = sheetState,
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
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                FilledButton(
                    enabled = true,
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    text = negativeText,
                    textColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    coroutineScope.launch {
                        openBottomSheet.value = false
                        sheetState.hide()
                    }
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                TextButton(
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        coroutineScope.launch {
                            openBottomSheet.value = false
                            sheetState.hide()
                            onPerformClick()
                        }
                    }
                ) {
                    Text(
                        text = positiveText,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
                Spacer(modifier = Modifier.padding(bottom = MaterialTheme.spacing.extraLarge))
            }
        }
    }
}