package az.appa.mobile.presentation.feature.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.app_desc
import appa.composeapp.generated.resources.app_name
import appa.composeapp.generated.resources.ic_onboarding_1
import appa.composeapp.generated.resources.text_email
import appa.composeapp.generated.resources.title_sign_log_in
import az.appa.mobile.presentation.common.DimmedLoading
import az.appa.mobile.presentation.common.FilledButton
import az.appa.mobile.presentation.common.SnackMessage
import az.appa.mobile.presentation.common.TextField
import az.appa.mobile.theme.spacing
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(
    onNavVerification: (String) -> Unit
) {
    val viewModel = koinInject<LoginViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.uiState.collectAsState()

    var inputEmail by remember(state.email) { mutableStateOf(state.email) }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is LoginContract.Effect.OpenVerificationScreen -> {
                    onNavVerification(state.email)
                }
            }
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize().systemBarsPadding().imePadding()
    ) {
        Column(verticalArrangement = Arrangement.Top) {
            AnimatedVisibility(!state.isFocused) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(Res.drawable.ic_onboarding_1),
                    contentDescription = stringResource(Res.string.app_name)
                )
            }
        }
        Column(verticalArrangement = Arrangement.Bottom) {
            Column(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.default)
                    .padding(bottom = MaterialTheme.spacing.default)
            ) {
                Text(
                    text = stringResource(Res.string.app_name),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(Res.string.app_desc),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.default)
                    .fillMaxWidth(), verticalAlignment = Alignment.Top
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth().onFocusChanged {
                        viewModel.setEvent(LoginContract.Event.FocusState(it.isFocused))
                    },
                    query = inputEmail,
                    error = state.emailError,
                    labelText = stringResource(Res.string.text_email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        viewModel.setEvent(LoginContract.Event.OnLogin)
                    })
                ) {
                    inputEmail = it
                    viewModel.setEvent(LoginContract.Event.OnEmailType(it))
                }
            }
            FilledButton(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.default)
                    .padding(bottom = MaterialTheme.spacing.default)
                    .fillMaxWidth(),
                enabled = !state.isLoading,
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.large,
                text = stringResource(Res.string.title_sign_log_in)
            ) { viewModel.setEvent(LoginContract.Event.OnLogin) }
        }
        SnackMessage(state.error) { viewModel.setEvent(LoginContract.Event.CleanError) }
        DimmedLoading(state.isLoading)
    }
}