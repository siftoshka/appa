package az.appa.mobile.domain.repository

interface SettingsRepository {

    var accessToken: String
    var isOnboardingComplete: Boolean
    var isAuthorised: Boolean

    fun cleanStorage()
}

enum class SettingsKeys {
    ACCESS_TOKEN,
    ONBOARDING_COMPLETE,
    AUTHORISED;

    val key get() = this.name
}

