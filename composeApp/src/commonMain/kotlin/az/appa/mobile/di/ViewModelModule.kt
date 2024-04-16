package az.appa.mobile.di

import az.appa.mobile.presentation.feature.boxmanager.BoxManagerViewModel
import az.appa.mobile.presentation.feature.home.HomeViewModel
import az.appa.mobile.presentation.feature.login.LoginViewModel
import az.appa.mobile.presentation.feature.onboarding.OnboardingViewModel
import az.appa.mobile.presentation.feature.settings.SettingsViewModel
import az.appa.mobile.presentation.feature.verification.VerificationViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { OnboardingViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { VerificationViewModel(get(), get()) }
    factory { SettingsViewModel() }
    factory { params ->
        BoxManagerViewModel(params.get(), get())
    }
    single { HomeViewModel(get()) }
}