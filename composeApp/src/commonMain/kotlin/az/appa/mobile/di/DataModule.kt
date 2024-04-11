package az.appa.mobile.di

import az.appa.mobile.data.repository.AuthRepositoryImpl
import az.appa.mobile.data.repository.SettingsRepositoryImpl
import az.appa.mobile.domain.repository.AuthRepository
import az.appa.mobile.domain.repository.SettingsRepository
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val dataModule = module {
    single<SettingsRepository> {
        SettingsRepositoryImpl(settings = Settings())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}