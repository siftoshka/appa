package az.appa.mobile.presentation.feature.home

import az.appa.mobile.presentation.base.UiEffect
import az.appa.mobile.presentation.base.UiEvent
import az.appa.mobile.presentation.base.UiState

object HomeContract {
    sealed class Event : UiEvent {

    }

    data class State(
        val email: String = "",
    ) : UiState

    sealed interface Effect : UiEffect {
    }
}