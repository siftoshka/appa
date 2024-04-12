package az.appa.mobile.presentation.feature.login

import az.appa.mobile.presentation.base.UiEffect
import az.appa.mobile.presentation.base.UiEvent
import az.appa.mobile.presentation.base.UiState

object LoginContract {
    sealed class Event : UiEvent {
        data object OnLogin : Event()
        data object CleanState : Event()
        data object CleanError : Event()
        data class OnEmailType(val value: String) : Event()
        data class FocusState(val isFocused: Boolean) : Event()
    }

    data class State(
        val email: String = "",
        val emailError: String = "",
        val isLoading: Boolean = false,
        val isFocused: Boolean = false,
        val error: String = ""
    ) : UiState

    sealed interface Effect : UiEffect {
        data object OpenVerificationScreen : Effect
    }
}