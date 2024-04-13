package az.appa.mobile.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import az.appa.mobile.domain.model.UniversalApp
import az.appa.mobile.openUrl
import io.kamel.image.KamelImage

@Composable
fun AppIcon(app: UniversalApp) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(60.dp),
        onClick = { openUrl(app.link) }
    ) {
        KamelImage(
            resource = getAsyncImageRes(app.imagePath),
            contentDescription = app.title,
            modifier = Modifier.size(60.dp)
        )
    }
}