package az.appa.mobile.data.repository

import az.appa.mobile.domain.repository.SettingsKeys
import az.appa.mobile.domain.repository.SettingsRepository
import com.russhwolf.settings.Settings

class SettingsRepositoryImpl(private val settings: Settings) : SettingsRepository {
    override var accessToken: String
        get() = settings.getString(SettingsKeys.ACCESS_TOKEN.key, "")
        set(value) {
            settings.putString(SettingsKeys.ACCESS_TOKEN.key, value)
        }

    override var isOnboardingComplete: Boolean
        get() = settings.getBoolean(SettingsKeys.ONBOARDING_COMPLETE.key, false)
        set(value) {
            settings.putBoolean(SettingsKeys.ONBOARDING_COMPLETE.key, value)
        }

    override var isAuthorised: Boolean
        get() = settings.getBoolean(SettingsKeys.AUTHORISED.key, false)
        set(value) {
            settings.putBoolean(SettingsKeys.AUTHORISED.key, value)
        }

    override fun cleanStorage() {
        settings.clear()
    }
}