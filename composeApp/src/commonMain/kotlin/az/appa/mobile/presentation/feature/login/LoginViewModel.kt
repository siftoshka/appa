package az.appa.mobile.presentation.feature.login

import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.phone_empty_error
import az.appa.mobile.domain.repository.AuthRepository
import az.appa.mobile.domain.utils.Resource
import az.appa.mobile.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString

class LoginViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {

    override fun createInitialState() = LoginContract.State()

    override fun handleEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.OnEmailType -> {
                setState { copy(email = event.value) }
            }

            LoginContract.Event.OnLogin -> login()
            LoginContract.Event.CleanError -> {
                setState { copy(error = "") }
            }

            LoginContract.Event.CleanState -> {
                setState { LoginContract.State() }
            }

            is LoginContract.Event.FocusState -> {
                setState { copy(isFocused = event.isFocused) }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            if (!validateLogin()) {
                return@launch
            }
            authRepository.login(currentState.email)
                .onStart { setState { copy(isLoading = true) } }
                .onEach { result ->
                    setState { copy(isLoading = false) }
                    when (result) {
                        is Resource.Error -> {
                            setState { copy(error = result.message ?: "") }
                        }

                        is Resource.Success -> {
                            setEffect { LoginContract.Effect.OpenVerificationScreen }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    private suspend fun validateLogin(): Boolean {
        return when {
            currentState.email.isEmpty() -> {
                val emptyPhoneNumber = getString(Res.string.phone_empty_error)
                setState { copy(emailError = emptyPhoneNumber) }
                false
            }

            else -> {
                setState { copy(emailError = "") }
                true
            }
        }
    }
}