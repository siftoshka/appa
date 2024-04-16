package az.appa.mobile.presentation.feature.settings

import az.appa.mobile.presentation.base.BaseViewModel

class SettingsViewModel() :
    BaseViewModel<SettingsContract.Event, SettingsContract.State, SettingsContract.Effect>() {

    override fun createInitialState() = SettingsContract.State

    override fun handleEvent(event: SettingsContract.Event) {
        when (event) {

            else -> {}
        }
    }
}