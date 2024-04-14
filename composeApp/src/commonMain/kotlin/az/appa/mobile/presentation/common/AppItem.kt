package az.appa.mobile.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.btn_start
import appa.composeapp.generated.resources.ic_more
import az.appa.mobile.domain.model.UniversalApp
import az.appa.mobile.openUrl
import az.appa.mobile.theme.spacing
import io.kamel.image.KamelImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppItem(app: UniversalApp) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { openUrl(app.link) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(shape = MaterialTheme.shapes.large, modifier = Modifier.size(60.dp)) {
                KamelImage(
                    resource = getAsyncImageRes(app.imagePath),
                    contentDescription = app.title,
                    modifier = Modifier.size(60.dp)
                )
            }
            Column(Modifier.weight(1f).padding(horizontal = MaterialTheme.spacing.medium)) {
                Text(
                    text = app.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = app.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                )
            }
            Icon(
                painter = painterResource(Res.drawable.ic_more),
                contentDescription = stringResource(Res.string.btn_start),
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}