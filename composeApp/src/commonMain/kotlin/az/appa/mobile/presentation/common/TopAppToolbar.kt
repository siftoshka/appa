package az.appa.mobile.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import appa.composeapp.generated.resources.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun TopAppBar(
    title: String? = "",
    onBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title.orEmpty(),
                style = MaterialTheme.typography.labelLarge,
                fontSize = 16.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = stringResource(Res.string.btn_back),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String) {
    TopAppBar(title = { Text(text = title) })
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun TopAppBar(
    title: String,
    actionVisible: Boolean = false,
    actionTitle: String = "",
    actionIcon: Painter? = null,
    onActionClick: () -> Unit = {},
    onBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 16.sp,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = stringResource(Res.string.btn_back),
                    modifier = Modifier.size(16.dp)
                )
            }
        },
        actions = {
            if (actionIcon != null && actionVisible) {
                IconButton(onClick = onActionClick) {
                    Icon(
                        painter = actionIcon,
                        contentDescription = actionTitle,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    )
}