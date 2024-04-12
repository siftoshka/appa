package az.appa.mobile.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = TextUnit.Unspecified,
    onPerformClick: () -> Unit
) {
    OutlinedButton(
        onClick = { onPerformClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        shape = MaterialTheme.shapes.extraLarge,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = TextUnit.Unspecified,
    enabled: Boolean = false,
    shape: CornerBasedShape = MaterialTheme.shapes.extraLarge,
    onPerformClick: () -> Unit
) {
    val bgColor = if (enabled) backgroundColor else MaterialTheme.colorScheme.background
    val txColor = if (enabled) textColor else MaterialTheme.colorScheme.onBackground

    OutlinedButton(
        onClick = { onPerformClick() },
        modifier = modifier.height(48.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(bgColor),
        shape = shape,
        border = BorderStroke(1.dp, bgColor)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            color = txColor,
            textAlign = TextAlign.Center
        )
    }
}