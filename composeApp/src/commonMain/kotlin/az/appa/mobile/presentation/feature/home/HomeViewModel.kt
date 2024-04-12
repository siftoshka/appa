package az.appa.mobile.presentation.feature.home

import az.appa.mobile.domain.repository.AuthRepository
import az.appa.mobile.presentation.base.BaseViewModel

class HomeViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    override fun createInitialState() = HomeContract.State()

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {

            else -> {}
        }
    }
}