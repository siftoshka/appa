package az.appa.mobile.presentation.feature.boxmanager.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.btn_apply
import appa.composeapp.generated.resources.ic_plus
import appa.composeapp.generated.resources.text_add_new_app
import az.appa.mobile.theme.spacing
import az.appa.mobile.utils.BoxManagerState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BoxManagerPlus(boxManagerState: BoxManagerState, onPerformClick: () -> Unit) {
    AnimatedVisibility(boxManagerState != BoxManagerState.VIEW) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = MaterialTheme.spacing.default)
                .padding(vertical = MaterialTheme.spacing.medium)
                .clickable { onPerformClick() },
        ) {
            Row(
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledIconButton(
                    modifier = Modifier.size(32.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                    onClick = { onPerformClick() }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_plus),
                        contentDescription = stringResource(Res.string.btn_apply),
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
                Text(
                    text = stringResource(Res.string.text_add_new_app),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}