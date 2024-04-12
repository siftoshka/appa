package az.appa.mobile.presentation.feature.verification

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.btn_confirm
import appa.composeapp.generated.resources.text_desc_verification
import appa.composeapp.generated.resources.text_title_verification
import appa.composeapp.generated.resources.title_verification
import az.appa.mobile.presentation.common.DimmedLoading
import az.appa.mobile.presentation.common.FilledButton
import az.appa.mobile.presentation.common.SnackMessage
import az.appa.mobile.presentation.common.TopAppBar
import az.appa.mobile.presentation.feature.login.LoginContract
import az.appa.mobile.theme.spacing
import az.appa.mobile.utils.isOtpValid
import az.appa.mobile.utils.numberLength
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun VerificationScreen(
    email: String,
    goBack: () -> Unit,
    onNavHome: () -> Unit
) {
    val viewModel = koinInject<VerificationViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.uiState.collectAsState()

    val otpCount = 6
    var inputOtp by remember(state.otp) { mutableStateOf(state.otp) }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                VerificationContract.Effect.NavigateBack -> goBack()
                VerificationContract.Effect.OpenHomeScreen -> onNavHome()
            }
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize().systemBarsPadding()
    ) {
        Column {
            TopAppBar(title = stringResource(Res.string.title_verification)) {
                viewModel.setEvent(VerificationContract.Event.NavigateBack)
            }
            Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.default)) {
                Text(
                    text = stringResource(Res.string.text_title_verification),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                Text(
                    text = stringResource(Res.string.text_desc_verification, email),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.regular))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OtpView(
                        otpText = inputOtp,
                        otpCount = otpCount,
                        isError = false,
                        onOtpTextChange = {
                            if (it.numberLength() == otpCount) {
                                keyboardController?.hide()
                            }
                            inputOtp = it
                            viewModel.setEvent(VerificationContract.Event.OnVerificationType(it))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.default))
                AnimatedVisibility(state.otp.isOtpValid(otpCount) && !state.isLoading) {
                    FilledButton(
                        enabled = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.default)
                            .padding(bottom = MaterialTheme.spacing.default),
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        text = stringResource(Res.string.btn_confirm),
                        textColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        viewModel.setEvent(VerificationContract.Event.OnVerification(email))
                    }
                }
            }
        }
        SnackMessage(state.error) { viewModel.setEvent(VerificationContract.Event.CleanError) }
        DimmedLoading(state.isLoading)
    }
}