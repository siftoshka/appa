package az.appa.mobile.presentation.feature.boxmanager.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.desc_box_manager
import appa.composeapp.generated.resources.title_box_manager
import az.appa.mobile.theme.spacing
import az.appa.mobile.utils.BoxManagerState
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxManagerBar(
    boxManagerState: BoxManagerState,
    title: String,
    subtitle: String,
    onTitleChange: (String) -> Unit,
    onSubtitleChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = MaterialTheme.spacing.default)) {
        Box {
            BasicTextField2(
                value = title,
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                ),
                onValueChange = { onTitleChange(it) },
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                lineLimits = TextFieldLineLimits.SingleLine,
                readOnly = boxManagerState == BoxManagerState.VIEW
            )
            if (title.isEmpty() && boxManagerState != BoxManagerState.VIEW) {
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
            BasicTextField2(
                value = subtitle,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                onValueChange = { onSubtitleChange(it) },
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                lineLimits = TextFieldLineLimits.MultiLine(maxHeightInLines = 3),
                readOnly = boxManagerState == BoxManagerState.VIEW
            )
            if (subtitle.isEmpty() && boxManagerState != BoxManagerState.VIEW) {
                Text(
                    text = stringResource(Res.string.desc_box_manager),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}