package az.appa.mobile.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import appa.composeapp.generated.resources.Res
import appa.composeapp.generated.resources.Rubik_Black
import appa.composeapp.generated.resources.Rubik_BlackItalic
import appa.composeapp.generated.resources.Rubik_Bold
import appa.composeapp.generated.resources.Rubik_BoldItalic
import appa.composeapp.generated.resources.Rubik_ExtraBold
import appa.composeapp.generated.resources.Rubik_ExtraBoldItalic
import appa.composeapp.generated.resources.Rubik_Light
import appa.composeapp.generated.resources.Rubik_LightItalic
import appa.composeapp.generated.resources.Rubik_Medium
import appa.composeapp.generated.resources.Rubik_MediumItalic
import appa.composeapp.generated.resources.Rubik_Regular
import appa.composeapp.generated.resources.Rubik_SemiBold
import appa.composeapp.generated.resources.Rubik_SemiBoldItalic
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

private val MangoFonts
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    get() = FontFamily(
        Font(Res.font.Rubik_Regular),
        Font(Res.font.Rubik_Black, FontWeight.Black),
        Font(Res.font.Rubik_BlackItalic, FontWeight.Black, FontStyle.Italic),
        Font(Res.font.Rubik_Bold, FontWeight.Bold),
        Font(Res.font.Rubik_BoldItalic, FontWeight.Bold, FontStyle.Italic),
        Font(Res.font.Rubik_ExtraBold, FontWeight.ExtraBold),
        Font(Res.font.Rubik_ExtraBoldItalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(Res.font.Rubik_Light, FontWeight.Light),
        Font(Res.font.Rubik_LightItalic, FontWeight.Light, FontStyle.Italic),
        Font(Res.font.Rubik_Medium, FontWeight.Medium),
        Font(Res.font.Rubik_MediumItalic, FontWeight.Medium, FontStyle.Italic),
        Font(Res.font.Rubik_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Rubik_SemiBoldItalic, FontWeight.SemiBold, FontStyle.Italic),
    )

private val defaultTypography = Typography()

private val AppTypography: Typography
    @Composable
    get() = Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = MangoFonts),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = MangoFonts),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = MangoFonts),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = MangoFonts),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = MangoFonts),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = MangoFonts),
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = MangoFonts),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = MangoFonts),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = MangoFonts),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = MangoFonts),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = MangoFonts),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = MangoFonts),
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = MangoFonts),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = MangoFonts),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = MangoFonts)
    )

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(2.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }

@Composable
internal fun AppTheme(
    content: @Composable() () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    CompositionLocalProvider(LocalThemeIsDark provides isDarkState) {
        val isDark by isDarkState
        SystemAppearance(!isDark)
        MaterialTheme(
            colorScheme = if (isDark) DarkColorScheme else LightColorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = { Surface(content = content) }
        )
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)
