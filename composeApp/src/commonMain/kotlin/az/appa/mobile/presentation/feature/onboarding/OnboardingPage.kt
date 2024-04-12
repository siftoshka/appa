package az.appa.mobile.presentation.feature.onboarding

import appa.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
sealed class OnboardingPage(
    val image: DrawableResource,
    val title: StringResource,
    val desc: StringResource
) {
    data object First : OnboardingPage(
        image = Res.drawable.ic_onboarding_1,
        title = Res.string.title_onboarding_1,
        desc = Res.string.desc_onboarding_1
    )
    data object Second : OnboardingPage(
        image = Res.drawable.ic_onboarding_2,
        title = Res.string.title_onboarding_2,
        desc = Res.string.desc_onboarding_2
    )
    data object Third : OnboardingPage(
        image = Res.drawable.ic_onboarding_3,
        title = Res.string.title_onboarding_3,
        desc = Res.string.desc_onboarding_3
    )
}