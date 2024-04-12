package az.appa.mobile.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import appa.composeapp.generated.resources.*
import az.appa.mobile.theme.spacing
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SnackMessage(
    value: String,
    onChangeListener: (() -> Unit)? = null
) {
    val scope = rememberCoroutineScope()

    AnimatedVisibility(value.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.default),
        ) {
            Box(contentAlignment = Alignment.TopCenter) {
                Card(
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall)
                ) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.error),
                        tonalElevation = 2.dp,
                        headlineContent = {
                            Text(
                                text = value,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onError,
                                fontWeight = FontWeight.Medium
                            )
                        },
                        leadingContent = {
                            Icon(
                                painter = painterResource(Res.drawable.ic_about),
                                contentDescription = value,
                                modifier = Modifier.size(20.dp),
                                tint = MaterialTheme.colorScheme.onError
                            )
                        }
                    )
                    LaunchedEffect(value.isNotBlank()) {
                        scope.launch {
                            delay(1000L)
                            onChangeListener?.let { it() }
                        }
                    }
                }
            }
        }
    }
}