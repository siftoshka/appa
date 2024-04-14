package az.appa.mobile.presentation.feature.boxmanager.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.btn_apply
import appa.composeapp.generated.resources.btn_back
import appa.composeapp.generated.resources.ic_apply
import appa.composeapp.generated.resources.ic_arrow_back
import az.appa.mobile.theme.spacing
import az.appa.mobile.utils.BoxManagerState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BoxManagerBar(
    boxManagerState: BoxManagerState,
    onBackClick: () -> Unit,
    onApplyClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = MaterialTheme.spacing.small)
            .padding(end = MaterialTheme.spacing.medium)
            .padding(vertical = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_back),
                contentDescription = stringResource(Res.string.btn_back),
                modifier = Modifier.size(24.dp)
            )
        }
        AnimatedVisibility(boxManagerState != BoxManagerState.VIEW) {
            FilledIconButton(
                modifier = Modifier.height(32.dp).width(46.dp),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                onClick = { onApplyClick() }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_apply),
                    contentDescription = stringResource(Res.string.btn_apply),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}