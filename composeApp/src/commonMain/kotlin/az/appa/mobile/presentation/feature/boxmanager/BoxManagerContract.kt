package az.appa.mobile.presentation.feature.boxmanager

import az.appa.mobile.presentation.base.UiEffect
import az.appa.mobile.presentation.base.UiEvent
import az.appa.mobile.presentation.base.UiState

object BoxManagerContract {
    sealed class Event : UiEvent {
        data class OnTitleType(val value: String) : Event()
        data class OnSubtitleType(val value: String) : Event()
        data class OnUrlType(val value: String) : Event()
    }

    data class State(
        val title: String = "",
        val subtitle: String = "",
        val urlPath: String = ""
    ) : UiState

    sealed interface Effect : UiEffect {
    }
}