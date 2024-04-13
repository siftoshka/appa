package az.appa.mobile.presentation.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.app_name
import appa.composeapp.generated.resources.btn_settings
import appa.composeapp.generated.resources.btn_start
import appa.composeapp.generated.resources.ic_pen
import appa.composeapp.generated.resources.ic_settings
import az.appa.mobile.theme.spacing
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeBar(
    onComposeClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = MaterialTheme.spacing.default)
            .padding(end = MaterialTheme.spacing.small)
            .padding(vertical = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            FilledIconButton(
                modifier = Modifier.height(32.dp).width(46.dp),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                onClick = { onComposeClick() }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_pen),
                    contentDescription = stringResource(Res.string.btn_start),
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            IconButton(onClick = { onSettingsClick() }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_settings),
                    contentDescription = stringResource(Res.string.btn_settings),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}