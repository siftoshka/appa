package az.appa.mobile.utils

sealed class Route(val route: String) {
    data object OnboardingScreen : Route("onboarding_screen")

    data object LoginScreen : Route("login_screen")
    data object VerificationScreen : Route("verification_screen")

    data object HomeScreen : Route("home_screen")
    data object SettingsScreen : Route("settings_screen")

    data object BoxManagerScreen : Route("box_manager_screen")
}
