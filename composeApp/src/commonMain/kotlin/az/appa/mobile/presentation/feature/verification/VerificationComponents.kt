package az.appa.mobile.presentation.feature.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OtpView(
    otpText: String = "",
    otpCount: Int = 4,
    isError: Boolean = false,
    onOtpTextChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }

    BasicTextField(
        modifier = Modifier.focusRequester(focusRequester),
        value = otpText,
        onValueChange = {
            if (it.length <= otpCount) {
                onOtpTextChange.invoke(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                repeat(otpCount) { index ->
                    Spacer(modifier = Modifier.width(4.dp))
                    CharView(
                        index = index,
                        text = otpText,
                        isError = isError
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        })
}


@Composable
private fun CharView(
    index: Int,
    text: String,
    isError: Boolean
) {
    val borderColor =
        if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    val modifier = Modifier
        .size(MaterialTheme.typography.headlineSmall.fontSize.value.dp * 2)
        .border(
            width = 1.dp,
            color = borderColor,
            shape = MaterialTheme.shapes.large
        )
        .padding(bottom = 4.dp)
        .background(Color.Transparent)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val char = when {
            index >= text.length -> ""
            else -> text[index].toString()
        }
        Text(
            text = char,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier.wrapContentHeight(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )
    }
}