package az.appa.mobile.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.btn_continue
import appa.composeapp.generated.resources.btn_settings
import appa.composeapp.generated.resources.ic_apply
import appa.composeapp.generated.resources.ic_more
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoreIcon(onPerformClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(60.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        onClick = { onPerformClick() }
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_more),
            contentDescription = stringResource(Res.string.btn_continue),
            modifier = Modifier.size(60.dp).padding(12.dp)
        )
    }
}