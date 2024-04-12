package az.appa.mobile.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import az.appa.mobile.utils.Constants
import io.github.alexzhirkevich.compottie.LottieAnimation
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.LottieConstants
import io.github.alexzhirkevich.compottie.rememberLottieComposition

@Composable
fun DimmedLoading(isLoading: Boolean) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(Constants.LOTTIE_LOADING))

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f))
                .wrapContentSize()
        ) {
            LottieAnimation(
                modifier = Modifier.height(240.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.Fit
            )
        }
    }
}