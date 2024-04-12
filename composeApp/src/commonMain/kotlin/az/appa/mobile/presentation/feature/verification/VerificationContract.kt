package az.appa.mobile.presentation.feature.verification

import az.appa.mobile.presentation.base.UiEffect
import az.appa.mobile.presentation.base.UiEvent
import az.appa.mobile.presentation.base.UiState

object VerificationContract {
    sealed class Event : UiEvent {
        data class OnVerification(val email: String) : Event()
        data object CleanState : Event()
        data object CleanError : Event()
        data object NavigateBack : Event()
        data class OnVerificationType(val value: String) : Event()
    }

    data class State(
        val otp: String = "",
        val isLoading: Boolean = false,
        val isSuccess: Boolean = false,
        val error: String = ""
    ) : UiState

    sealed interface Effect : UiEffect {
        data object OpenHomeScreen : Effect
        data object NavigateBack : Effect
    }
}