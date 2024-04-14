package az.appa.mobile.presentation.feature.boxmanager.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.desc_box_manager
import appa.composeapp.generated.resources.title_box_manager
import az.appa.mobile.presentation.feature.boxmanager.BoxManagerContract
import az.appa.mobile.theme.spacing
import az.appa.mobile.utils.BoxManagerState
import org.jetbrains.compose.resources.stringResource

@Composable
fun BoxManagerInfo(
    boxManagerState: BoxManagerState,
    state: BoxManagerContract.State,
    onTitleChange: (String) -> Unit,
    onSubtitleChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputTitle by remember(state.title) { mutableStateOf(state.title) }
    var inputSubtitle by remember(state.subtitle) { mutableStateOf(state.subtitle) }

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = MaterialTheme.spacing.default)) {
        Box {
            BasicTextField(
                value = inputTitle,
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                ),
                onValueChange = {
                    inputTitle = it
                    onTitleChange(it)
                },
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                singleLine = true,
                readOnly = boxManagerState == BoxManagerState.VIEW
            )
            if (inputTitle.isEmpty() && boxManagerState != BoxManagerState.VIEW) {
                Text(
                    text = stringResource(Res.string.title_box_manager),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Box {
            BasicTextField(
                value = inputSubtitle,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                onValueChange = {
                    inputSubtitle = it
                    onSubtitleChange(it)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                maxLines = 3,
                readOnly = boxManagerState == BoxManagerState.VIEW
            )
            if (inputSubtitle.isEmpty() && boxManagerState != BoxManagerState.VIEW) {
                Text(
                    text = stringResource(Res.string.desc_box_manager),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}