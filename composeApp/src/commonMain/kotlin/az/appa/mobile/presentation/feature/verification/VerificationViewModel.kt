package az.appa.mobile.presentation.feature.verification

import az.appa.mobile.domain.repository.AuthRepository
import az.appa.mobile.domain.repository.SettingsRepository
import az.appa.mobile.domain.utils.Resource
import az.appa.mobile.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class VerificationViewModel(
    private val authRepository: AuthRepository,
    private val settingsRepository: SettingsRepository
) : BaseViewModel<VerificationContract.Event, VerificationContract.State, VerificationContract.Effect>() {

    override fun createInitialState() = VerificationContract.State()

    override fun handleEvent(event: VerificationContract.Event) {
        when (event) {
            is VerificationContract.Event.OnVerificationType -> {
                setState { copy(otp = event.value) }
            }

            is VerificationContract.Event.OnVerification -> {
                verify(event.email)
            }

            VerificationContract.Event.CleanState -> {
                setState { VerificationContract.State() }
            }

            VerificationContract.Event.NavigateBack -> {
                setState { VerificationContract.State() }
                setEffect { VerificationContract.Effect.NavigateBack }
            }

            VerificationContract.Event.CleanError -> setState { copy(error = null) }
        }
    }

    private fun verify(email: String) {
        viewModelScope.launch {
            authRepository.verification(email, currentState.otp)
                .onStart { setState { copy(isLoading = true) } }
                .onEach { result ->
                    when (result) {
                        is Resource.Error -> setState {
                            copy(otp = "", error = result.exception)
                        }

                        is Resource.Success -> {
                            settingsRepository.accessToken = result.data.token
                            setEffect { VerificationContract.Effect.OpenHomeScreen }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}