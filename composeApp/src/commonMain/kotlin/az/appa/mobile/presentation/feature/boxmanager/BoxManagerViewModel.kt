package az.appa.mobile.presentation.feature.boxmanager

import az.appa.mobile.domain.repository.AuthRepository
import az.appa.mobile.presentation.base.BaseViewModel

class BoxManagerViewModel(
    private val boxId: String,
    private val authRepository: AuthRepository
) : BaseViewModel<BoxManagerContract.Event, BoxManagerContract.State, BoxManagerContract.Effect>() {

    override fun createInitialState() = BoxManagerContract.State()

    override fun handleEvent(event: BoxManagerContract.Event) {
        when (event) {
            is BoxManagerContract.Event.OnSubtitleType -> setState { copy(subtitle = event.value) }
            is BoxManagerContract.Event.OnTitleType -> setState { copy(title = event.value) }
            is BoxManagerContract.Event.OnUrlType -> setState { copy(urlPath = event.value) }
        }
    }

    init {

    }
}