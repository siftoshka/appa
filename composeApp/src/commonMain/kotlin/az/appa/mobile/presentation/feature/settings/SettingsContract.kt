package az.appa.mobile.presentation.feature.settings

import az.appa.mobile.presentation.base.UiEffect
import az.appa.mobile.presentation.base.UiEvent
import az.appa.mobile.presentation.base.UiState

object SettingsContract {
    sealed class Event : UiEvent {
    }

    data object State : UiState

    sealed interface Effect : UiEffect {
    }
}