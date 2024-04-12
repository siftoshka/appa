package az.appa.mobile.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    query: String,
    labelText: String,
    readOnly: Boolean = false,
    error: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = { onValueChange(it) },
        singleLine = singleLine,
        readOnly = readOnly,
        enabled = !readOnly,
        maxLines = maxLines,
        modifier = modifier,
        isError = error.isNotEmpty(),
        label = { Text(text = labelText) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = MaterialTheme.shapes.large,
        colors = colors,
        supportingText = {
            if (error.isNotEmpty()) {
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    )
}