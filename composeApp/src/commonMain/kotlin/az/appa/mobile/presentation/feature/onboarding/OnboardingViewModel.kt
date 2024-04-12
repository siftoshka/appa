package az.appa.mobile.presentation.feature.onboarding

import az.appa.mobile.domain.repository.SettingsRepository
import az.appa.mobile.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val settingsRepository: SettingsRepository
) : BaseViewModel<OnboardingContract.Event, OnboardingContract.State, OnboardingContract.Effect>() {

    override fun createInitialState() = OnboardingContract.State

    override fun handleEvent(event: OnboardingContract.Event) {
        when (event) {
            OnboardingContract.Event.OnFinishOnboarding -> {
                saveOnboardingPref()
            }
        }
    }

    private fun saveOnboardingPref() {
        viewModelScope.launch {
            settingsRepository.isOnboardingComplete = false
            setEffect { OnboardingContract.Effect.OpenLoginScreen }
        }
    }
}