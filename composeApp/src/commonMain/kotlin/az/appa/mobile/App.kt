package az.appa.mobile

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import az.appa.mobile.di.dataModule
import az.appa.mobile.di.networkModule
import az.appa.mobile.di.viewModelModule
import az.appa.mobile.domain.repository.SettingsRepository
import az.appa.mobile.presentation.base.pop
import az.appa.mobile.presentation.base.push
import az.appa.mobile.presentation.base.replace
import az.appa.mobile.presentation.feature.boxmanager.BoxManagerScreen
import az.appa.mobile.presentation.feature.home.HomeScreen
import az.appa.mobile.presentation.feature.login.LoginScreen
import az.appa.mobile.presentation.feature.onboarding.OnboardingScreen
import az.appa.mobile.presentation.feature.settings.SettingsScreen
import az.appa.mobile.presentation.feature.verification.VerificationScreen
import az.appa.mobile.theme.AppTheme
import az.appa.mobile.utils.BoxManagerState
import az.appa.mobile.utils.Route
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.SwipeProperties
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
internal fun App() {
    KoinApplication(moduleList = { listOf(networkModule, dataModule, viewModelModule) }) {
        AppTheme {
            PreComposeApp {
                val navigator = rememberNavigator()
                val settingsRepository = koinInject<SettingsRepository>()

                val initialRoute = when {
                    settingsRepository.isOnboardingComplete -> Route.LoginScreen.route
                    settingsRepository.isAuthorised -> Route.HomeScreen.route
                    else -> Route.OnboardingScreen.route
                }
                NavHost(
                    navigator = navigator,
                    initialRoute = Route.HomeScreen.route,
                    navTransition = DefaultNavTransition,
                    swipeProperties = SwipeProperties(spaceToSwipe = 100.dp)
                ) {
                    scene(Route.OnboardingScreen.route) {
                        OnboardingScreen(
                            onNavLogin = { navigator.push(Route.LoginScreen.route) }
                        )
                    }
                    scene(Route.LoginScreen.route) {
                        LoginScreen(
                            onNavVerification = { navigator.push(Route.VerificationScreen.route + "/$it") }
                        )
                    }
                    scene(Route.VerificationScreen.route + "/{email}") {
                        val email = it.path<String>("email").orEmpty()
                        VerificationScreen(
                            email = email,
                            goBack = { navigator.pop() },
                            onNavHome = { navigator.replace(Route.HomeScreen.route) }
                        )
                    }
                    scene(Route.BoxManagerScreen.route + "/{state}") {
                        val state = BoxManagerState.fromValue(it.path<String>("state").orEmpty())
                        BoxManagerScreen(
                            boxManagerState = state,
                            goBack = { navigator.pop() }
                        )
                    }
                    scene(Route.SettingsScreen.route) {
                        SettingsScreen(
                            goBack = { navigator.pop() }
                        )
                    }
                    scene(Route.HomeScreen.route) {
                        HomeScreen(
                            onNavBoxManager = { navigator.push(Route.BoxManagerScreen.route + "/$it") },
                            onNavSettings = { navigator.push(Route.SettingsScreen.route) }
                        )
                    }
                }
            }
        }
    }
}

private val DefaultNavTransition = NavTransition(
    createTransition = slideInHorizontally(tween(easing = LinearEasing)) { it },
    destroyTransition = slideOutHorizontally(tween(easing = LinearEasing)) { it },
    pauseTransition = slideOutHorizontally(tween(easing = LinearEasing)) { -it / 2 },
    resumeTransition = slideInHorizontally(tween(easing = LinearEasing)) { -it / 2 },
    exitTargetContentZIndex = 1f
)

internal expect fun openUrl(url: String?)

internal expect fun hapticFeedback()

internal expect fun getPlatform(): String

internal expect fun isAndroid(): Boolean