package az.appa.mobile.presentation.feature.onboarding

import az.appa.mobile.presentation.base.UiEffect
import az.appa.mobile.presentation.base.UiEvent
import az.appa.mobile.presentation.base.UiState

object OnboardingContract {
    sealed class Event : UiEvent {
        data object OnFinishOnboarding : Event()
    }

    data object State : UiState

    sealed interface Effect : UiEffect {
        data object OpenLoginScreen : Effect
    }
}